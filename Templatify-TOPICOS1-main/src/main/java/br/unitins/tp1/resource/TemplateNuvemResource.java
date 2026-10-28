package br.unitins.tp1.resource;

import java.net.URI;
import java.util.List;

import br.unitins.tp1.dto.TemplateNuvemDTO;
import br.unitins.tp1.dto.TemplateNuvemResponseDTO;
import br.unitins.tp1.service.TemplateNuvemService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

@Path("/templates-nuvem")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TemplateNuvemResource {

    @Inject TemplateNuvemService service;
    @Context UriInfo uriInfo;

    @POST
    public Response create(@Valid TemplateNuvemDTO dto) {
        TemplateNuvemResponseDTO created = service.create(dto);
        URI location = uriInfo.getAbsolutePathBuilder().path(created.id().toString()).build();
        return Response.created(location).entity(created).build();
    }

    @GET
    public List<TemplateNuvemResponseDTO> findAll() { return service.findAll(); }

    @GET
    @Path("/{id}")
    public TemplateNuvemResponseDTO findById(@PathParam("id") Long id) { return service.findById(id); }

    @GET
    @Path("/search/nome/{nome}")
    public List<TemplateNuvemResponseDTO> findByNome(@PathParam("nome") String nome) {
        return service.findByNome(nome);
    }

    @PUT
    @Path("/{id}")
    public TemplateNuvemResponseDTO update(@PathParam("id") Long id, @Valid TemplateNuvemDTO dto) {
        return service.update(id, dto);
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        service.delete(id);
        return Response.noContent().build();
    }
}