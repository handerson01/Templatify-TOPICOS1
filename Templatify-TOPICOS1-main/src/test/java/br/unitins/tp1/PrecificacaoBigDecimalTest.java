package br.unitins.tp1;

import java.math.BigDecimal;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
class PrecificacaoBigDecimalTest {

    private Long createdPricingId;

    @AfterEach
    void removeCreatedPricing() {
        if (createdPricingId != null) {
            given()
                    .when().delete("/precificacoes/" + createdPricingId)
                    .then()
                    .statusCode(204);
        }
    }

    @Test
    void createsPricingWithoutLosingDecimalPrecision() {
        String response = given()
                .contentType("application/json")
                .body("""
                        {
                          "valorBase": 19.90,
                          "moeda": "BRL",
                          "percentualDesconto": 12.50
                        }
                        """)
                .when().post("/precificacoes")
                .then()
                .statusCode(201)
                .extract().asString();

        io.restassured.path.json.JsonPath json = io.restassured.path.json.JsonPath.from(response);
        createdPricingId = json.getLong("id");
        BigDecimal value = new BigDecimal(json.getString("valorBase"));
        BigDecimal discount = new BigDecimal(json.getString("percentualDesconto"));

        assertEquals(0, new BigDecimal("19.90").compareTo(value));
        assertEquals(0, new BigDecimal("12.50").compareTo(discount));
    }

    @Test
    void rejectsPriceWithMoreThanTwoFractionDigits() {
        given()
                .contentType("application/json")
                .body("""
                        {
                          "valorBase": 19.999,
                          "moeda": "BRL",
                          "percentualDesconto": 12.50
                        }
                        """)
                .when().post("/precificacoes")
                .then()
                .statusCode(400)
                .contentType("application/problem+json");
    }

    @Test
    void rejectsDiscountWithMoreThanTwoFractionDigits() {
        given()
                .contentType("application/json")
                .body("""
                        {
                          "valorBase": 19.90,
                          "moeda": "BRL",
                          "percentualDesconto": 12.501
                        }
                        """)
                .when().post("/precificacoes")
                .then()
                .statusCode(400)
                .contentType("application/problem+json");
    }

    @Test
    void priceSearchAcceptsDecimalPathParameter() {
        given()
                .when().get("/templates/preco/19.90")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }
}
