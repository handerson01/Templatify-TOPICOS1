package br.unitins.tp1.exception;

import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class NotFoundExceptionMapper extends AbstractProblemDetailsMapper
        implements ExceptionMapper<NotFoundException> {

    @Override
    public Response toResponse(NotFoundException exception) {
        return problem(
                "urn:templatify:problem:not-found",
                "Recurso não encontrado",
                Response.Status.NOT_FOUND.getStatusCode(),
                exception.getMessage(),
                null);
    }
}
