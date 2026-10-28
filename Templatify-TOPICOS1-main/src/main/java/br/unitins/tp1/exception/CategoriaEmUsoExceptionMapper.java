package br.unitins.tp1.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class CategoriaEmUsoExceptionMapper extends AbstractProblemDetailsMapper
        implements ExceptionMapper<CategoriaEmUsoException> {

    @Override
    public Response toResponse(CategoriaEmUsoException exception) {
        return problem(
                "urn:templatify:problem:resource-in-use",
                "Conflito",
                Response.Status.CONFLICT.getStatusCode(),
                exception.getMessage(),
                null);
    }
}