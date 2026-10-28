package br.unitins.tp1;

import java.net.URI;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.hamcrest.Matchers.is;

@QuarkusTest
class TemplateNuvemAtivoTest {

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
    void omittedAtivoDefaultsToTrueWhenCreating() {
        io.restassured.response.Response response = given()
                .contentType("application/json")
                .body(templateJson(null))
                .when().post("/templates-nuvem")
                .then()
                .statusCode(201)
                .body("ativo", is(true))
                .extract().response();

        createdTemplateId = response.jsonPath().getLong("id");
        URI location = URI.create(response.getHeader("Location"));

        assertEquals("/templates-nuvem/" + createdTemplateId, location.getPath());
        given()
                .when().get(location)
                .then()
                .statusCode(200)
                .body("id", is(createdTemplateId.intValue()));
    }

    @Test
    void omittedAtivoPreservesExistingValueWhenUpdating() {
        String response = given()
                .contentType("application/json")
                .body(templateJson(false))
                .when().post("/templates-nuvem")
                .then()
                .statusCode(201)
                .extract().asString();

        createdTemplateId = io.restassured.path.json.JsonPath.from(response).getLong("id");

        given()
                .contentType("application/json")
                .body(templateJson(null))
                .when().put("/templates-nuvem/" + createdTemplateId)
                .then()
                .statusCode(200)
                .body("ativo", is(false));
    }

    private String templateJson(Boolean ativo) {
        String ativoField = ativo == null ? "" : "\"ativo\": " + ativo + ",";
        return """
                {
                  "nome": "Template de teste",
                  "descricao": "Teste do estado ativo",
                  "idCategoria": 1,
                  "idAutor": 1,
                  "idFormato": 1,
                  "idPrecificacao": 1,
                  %s
                  "urlAcesso": "https://example.com/template",
                  "plataforma": "Canva",
                  "requerContaPaga": false
                }
                """.formatted(ativoField);
    }
}
