package br.unitins.tp1.exception;

import java.lang.reflect.Proxy;
import java.net.URI;

import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

@QuarkusTest
class ProblemDetailsExceptionMapperTest {

    @Test
    void validationErrorsUseProblemDetailsAndListInvalidFields() {
        given()
                .contentType("application/json")
                .body("{}")
                .when().post("/templates-arquivo")
                .then()
                .statusCode(400)
                .contentType("application/problem+json")
                .body("type", equalTo("urn:templatify:problem:validation-error"))
                .body("title", equalTo("Erro de validação"))
                .body("status", equalTo(400))
                .body("detail", equalTo("Um ou mais campos da requisição são inválidos."))
                .body("instance", equalTo("/templates-arquivo"))
                .body("violations.field", hasItem("nome"));
    }

    @Test
    void malformedJsonUsesProblemDetails() {
        given()
                .contentType("application/json")
                .body("{")
                .when().post("/templates-arquivo")
                .then()
                .statusCode(400)
                .contentType("application/problem+json")
                .body("type", equalTo("urn:templatify:problem:bad-request"))
                .body("title", equalTo("Requisição inválida"))
                .body("status", equalTo(400))
                .body("instance", equalTo("/templates-arquivo"));
    }

    @Test
    void badRequestExceptionsUseProblemDetails() {
        BadRequestExceptionMapper mapper = new BadRequestExceptionMapper();
        mapper.uriInfo = uriInfo("/templates-arquivo");

        Response response = mapper.toResponse(new BadRequestException());

        assertProblem(response, 400, "urn:templatify:problem:bad-request", "/templates-arquivo");
        assertEquals("Requisição inválida", problem(response).title());
    }

    @Test
    void notFoundExceptionsUseProblemDetails() {
        EntidadeNaoEncontradaExceptionMapper mapper = new EntidadeNaoEncontradaExceptionMapper();
        mapper.uriInfo = uriInfo("/templates-arquivo/42");

        Response response = mapper.toResponse(new EntidadeNaoEncontradaException("Template não encontrado."));

        assertProblem(response, 404, "urn:templatify:problem:not-found", "/templates-arquivo/42");
        assertEquals("Recurso não encontrado", problem(response).title());
        assertEquals("Template não encontrado.", problem(response).detail());
    }

    @Test
    void jaxRsNotFoundExceptionsUseProblemDetails() {
        NotFoundExceptionMapper mapper = new NotFoundExceptionMapper();
        mapper.uriInfo = uriInfo("/templates-nuvem/42");

        Response response = mapper.toResponse(new NotFoundException("Template na nuvem não encontrado."));

        assertProblem(response, 404, "urn:templatify:problem:not-found", "/templates-nuvem/42");
        assertEquals("Recurso não encontrado", problem(response).title());
        assertEquals("Template na nuvem não encontrado.", problem(response).detail());
    }

    @Test
    void missingCloudTemplateUsesProblemDetails() {
        given()
                .when().get("/templates-nuvem/999999")
                .then()
                .statusCode(404)
                .contentType("application/problem+json")
                .body("type", equalTo("urn:templatify:problem:not-found"))
                .body("title", equalTo("Recurso não encontrado"))
                .body("detail", equalTo("Template na nuvem não encontrado."))
                .body("instance", equalTo("/templates-nuvem/999999"));
    }

    @Test
    void categoryInUseExceptionsUseConflictProblemDetails() {
        CategoriaEmUsoExceptionMapper mapper = new CategoriaEmUsoExceptionMapper();
        mapper.uriInfo = uriInfo("/categorias/3");

        Response response = mapper.toResponse(new CategoriaEmUsoException(
                "Categoria vinculada a templates não pode ser excluída."));

        assertProblem(response, 409, "urn:templatify:problem:resource-in-use", "/categorias/3");
        assertEquals("Conflito", problem(response).title());
        assertEquals("Categoria vinculada a templates não pode ser excluída.", problem(response).detail());
    }

    private UriInfo uriInfo(String path) {
        return (UriInfo) Proxy.newProxyInstance(
                UriInfo.class.getClassLoader(),
                new Class<?>[] { UriInfo.class },
                (proxy, method, args) -> method.getName().equals("getRequestUri")
                        ? URI.create(path)
                        : null);
    }

    private void assertProblem(Response response, int status, String type, String instance) {
        assertEquals(status, response.getStatus());
        assertEquals(MediaType.valueOf("application/problem+json"), response.getMediaType());

        ProblemDetails problem = problem(response);
        assertEquals(type, problem.type());
        assertEquals(status, problem.status());
        assertEquals(instance, problem.instance());
    }

    private ProblemDetails problem(Response response) {
        return assertInstanceOf(ProblemDetails.class, response.getEntity());
    }
}
