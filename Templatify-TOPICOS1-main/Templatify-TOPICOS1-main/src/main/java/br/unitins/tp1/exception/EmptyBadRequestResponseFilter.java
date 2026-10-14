package br.unitins.tp1.exception;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.Provider;

@Provider
public class EmptyBadRequestResponseFilter implements ContainerResponseFilter {

    @Context
    UriInfo uriInfo;

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) {
        if (responseContext.getStatus() == 400 && responseContext.getEntity() == null) {
            responseContext.setEntity(new ProblemDetails(
                    "urn:templatify:problem:bad-request",
                    "Requisição inválida",
                    400,
                    "A requisição não pôde ser processada.",
                    uriInfo.getRequestUri().getPath(),
                    null));
            responseContext.getHeaders().putSingle(
                    HttpHeaders.CONTENT_TYPE,
                    MediaType.valueOf("application/problem+json"));
        }
    }
}
