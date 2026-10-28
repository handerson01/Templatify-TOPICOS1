package br.unitins.tp1;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;

@QuarkusTest
class TemplateNuvemUrlValidationTest {

    private Long createdTemplateId;

    @AfterEach
    void removeCreatedTemplate() {
        if (createdTemplateId != null) {
            given()
                    .when().delete("/templates-nuvem/" + createdTemplateId)
                    .then()
                    .statusCode(204);
        }
    }

    @Test
    void acceptsHttpsAccessUrl() {
        String response = given()
                .contentType("application/json")
                .body(templateJson("https://example.com/template"))
                .when().post("/templates-nuvem")
                .then()
                .statusCode(201)
                .extract().asString();

        createdTemplateId = io.restassured.path.json.JsonPath.from(response).getLong("id");
    }

    @Test
    void rejectsAccessUrlWithoutWebScheme() {
        given()
                .contentType("application/json")
                .body(templateJson("example.com/template"))
                .when().post("/templates-nuvem")
                .then()
                .statusCode(400)
                .contentType("application/problem+json")
                .body("violations.field", hasItem("urlAcesso"));
    }

    @Test
    void rejectsNonHttpAccessUrl() {
        given()
                .contentType("application/json")
                .body(templateJson("ftp://example.com/template"))
                .when().post("/templates-nuvem")
                .then()
                .statusCode(400)
                .contentType("application/problem+json")
                .body("violations.field", hasItem("urlAcesso"));
    }

    @Test
    void rejectsHttpUrlWithoutHost() {
        given()
                .contentType("application/json")
                .body(templateJson("https://"))
                .when().post("/templates-nuvem")
                .then()
                .statusCode(400)
                .contentType("application/problem+json")
                .body("violations.field", hasItem("urlAcesso"));
    }

    private String templateJson(String urlAcesso) {
        return """
                {
                  "nome": "Template de teste",
                  "descricao": "Validação de URL",
                  "idCategoria": 1,
                  "idAutor": 1,
                  "idFormato": 1,
                  "idPrecificacao": 1,
                  "ativo": true,
                  "urlAcesso": "%s",
                  "plataforma": "Canva",
                  "requerContaPaga": false
                }
                """.formatted(urlAcesso);
    }
}
