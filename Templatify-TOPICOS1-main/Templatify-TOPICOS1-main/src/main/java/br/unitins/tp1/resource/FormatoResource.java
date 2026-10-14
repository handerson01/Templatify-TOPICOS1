package br.unitins.tp1.resource;

import java.util.List;

import br.unitins.tp1.dto.FormatoDTO;
import br.unitins.tp1.model.Formato;
import br.unitins.tp1.service.FormatoService;
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
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/formatos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FormatoResource {

    @Inject FormatoService service;

    @POST
    public Response create(@Valid FormatoDTO dto) {
        return Response.status(Response.Status.CREATED).entity(service.create(dto)).build();
    }

    @GET
    public List<Formato> findAll() { return service.findAll(); }

    @GET
    @Path("/{id}")
    public Formato findById(@PathParam("id") Long id) { return service.findById(id); }

    @GET
    @Path("/search/{nome}")
    public List<Formato> findByNome(@PathParam("nome") String nome) { return service.findByNome(nome); }

    @PUT
    @Path("/{id}")
    public Formato update(@PathParam("id") Long id, @Valid FormatoDTO dto) {
        return service.update(id, dto);
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        service.delete(id);
        return Response.noContent().build();
    }
}