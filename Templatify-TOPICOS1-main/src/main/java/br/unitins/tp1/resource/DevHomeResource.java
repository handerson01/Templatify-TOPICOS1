package br.unitins.tp1.resource;

import java.net.URI;

import io.quarkus.arc.profile.IfBuildProfile;
import org.eclipse.microprofile.openapi.annotations.Operation;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

@IfBuildProfile("dev")
@Path("/")
public class DevHomeResource {

    @Context
    UriInfo uriInfo;

    @GET
    @Operation(hidden = true)
    public Response redirectToDevUi() {
        URI devUiUri = uriInfo.getBaseUriBuilder().path("q/dev/").build();
        return Response.seeOther(devUiUri).build();
    }
}
