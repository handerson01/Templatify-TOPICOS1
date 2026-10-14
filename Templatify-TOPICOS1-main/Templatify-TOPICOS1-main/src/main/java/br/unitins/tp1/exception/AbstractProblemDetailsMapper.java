package br.unitins.tp1.exception;

import java.util.List;

import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

abstract class AbstractProblemDetailsMapper {

    @Context
    UriInfo uriInfo;

    protected Response problem(String type, String title, int status, String detail,
            List<ProblemDetails.Violation> violations) {
        ProblemDetails problem = new ProblemDetails(
                type,
                title,
                status,
                detail,
                uriInfo.getRequestUri().getPath(),
                violations);

        return Response.status(status)
                .type("application/problem+json")
                .entity(problem)
                .build();
    }
}
