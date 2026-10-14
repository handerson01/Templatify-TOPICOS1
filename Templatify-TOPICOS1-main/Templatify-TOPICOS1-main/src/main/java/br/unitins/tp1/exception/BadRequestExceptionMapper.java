package br.unitins.tp1.exception;

import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class BadRequestExceptionMapper extends AbstractProblemDetailsMapper
        implements ExceptionMapper<BadRequestException> {

    @Override
    public Response toResponse(BadRequestException exception) {
        return problem(
                "urn:templatify:problem:bad-request",
                "Requisição inválida",
                Response.Status.BAD_REQUEST.getStatusCode(),
                "A requisição não pôde ser processada.",
                null);
    }
}
