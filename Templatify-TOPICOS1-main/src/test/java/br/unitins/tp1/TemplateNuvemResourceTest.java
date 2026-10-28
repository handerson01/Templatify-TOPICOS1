package br.unitins.tp1;

import java.net.URI;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
class TemplateNuvemResourceTest {

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
    void createListSearchAndFindById() {
        String name = "CloudResourceCrud";
        io.restassured.response.Response response = given()
                .contentType("application/json")
                .body(templateJson(name, true))
                .when().post("/templates-nuvem")
                .then()
                .statusCode(201)
                .body("id", notNullValue())
                .body("nome", equalTo(name))
                .body("valorBase", equalTo(29.90f))
                .body("moeda", equalTo("BRL"))
                .body("ativo", is(true))
                .extract().response();

        createdTemplateId = response.jsonPath().getLong("id");
        URI location = URI.create(response.getHeader("Location"));
        assertEquals("/templates-nuvem/" + createdTemplateId, location.getPath());

        given()
                .when().get("/templates-nuvem")
                .then()
                .statusCode(200)
                .body("id", hasItem(createdTemplateId.intValue()));

        given()
                .when().get("/templates-nuvem/search/nome/" + name)
                .then()
                .statusCode(200)
                .body("id", hasItem(createdTemplateId.intValue()));

        given()
                .when().get(location)
                .then()
                .statusCode(200)
                .body("id", equalTo(createdTemplateId.intValue()))
                .body("nome", equalTo(name));
    }

    @Test
    void updateChangesTemplateFields() {
        createdTemplateId = createTemplate("CloudResourceBefore", false);

        given()
                .contentType("application/json")
                .body(templateJson("CloudResourceAfter", true))
                .when().put("/templates-nuvem/" + createdTemplateId)
                .then()
                .statusCode(200)
                .body("id", equalTo(createdTemplateId.intValue()))
                .body("nome", equalTo("CloudResourceAfter"))
                .body("valorBase", equalTo(29.90f))
                .body("moeda", equalTo("BRL"))
                .body("ativo", is(true))
                .body("plataforma", equalTo("Canva"));
    }

    @Test
    void deleteRemovesTemplateAndSubsequentReadReturnsProblemDetails() {
        createdTemplateId = createTemplate("CloudResourceDelete", true);
        Long deletedId = createdTemplateId;

        given()
                .when().delete("/templates-nuvem/" + deletedId)
                .then()
                .statusCode(204);
        createdTemplateId = null;

        given()
                .when().get("/templates-nuvem/" + deletedId)
                .then()
                .statusCode(404)
                .contentType("application/problem+json")
                .body("type", equalTo("urn:templatify:problem:not-found"))
                .body("instance", equalTo("/templates-nuvem/" + deletedId));
    }

    @Test
    void invalidRequestReturnsValidationProblemDetails() {
        given()
                .contentType("application/json")
                .body("{}")
                .when().post("/templates-nuvem")
                .then()
                .statusCode(400)
                .contentType("application/problem+json")
                .body("type", equalTo("urn:templatify:problem:validation-error"))
                .body("violations.field", hasItem("nome"));
    }

    @Test
    void nonexistentCategoryReturnsNotFoundProblemDetails() {
        given()
                .contentType("application/json")
                .body(templateJsonWithCategory("CloudResourceMissingCategory", 99999999L))
                .when().post("/templates-nuvem")
                .then()
                .statusCode(404)
                .contentType("application/problem+json")
                .body("type", equalTo("urn:templatify:problem:not-found"))
                .body("detail", equalTo("Categoria informada não existe."))
                .body("instance", equalTo("/templates-nuvem"));
    }

    private Long createTemplate(String name, boolean ativo) {
        String response = given()
                .contentType("application/json")
                .body(templateJson(name, ativo))
                .when().post("/templates-nuvem")
                .then()
                .statusCode(201)
                .extract().asString();
        return io.restassured.path.json.JsonPath.from(response).getLong("id");
    }

    private String templateJson(String name, boolean ativo) {
        return templateJsonWithCategory(name, 1L, ativo);
    }

    private String templateJsonWithCategory(String name, long categoryId) {
        return templateJsonWithCategory(name, categoryId, true);
    }

    private String templateJsonWithCategory(String name, long categoryId, boolean ativo) {
        return """
                {
                  "nome": "%s",
                  "descricao": "Teste do resource de template na nuvem",
                  "idCategoria": %d,
                  "idAutor": 1,
                  "idFormato": 1,
                  "idPrecificacao": 1,
                  "ativo": %s,
                  "urlAcesso": "https://example.com/template",
                  "plataforma": "Canva",
                  "requerContaPaga": false
                }
                """.formatted(name, categoryId, ativo);
    }
}
