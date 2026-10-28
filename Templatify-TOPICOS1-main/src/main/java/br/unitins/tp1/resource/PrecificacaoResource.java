package br.unitins.tp1.resource;

import java.util.List;

import br.unitins.tp1.dto.PrecificacaoDTO;
import br.unitins.tp1.model.Precificacao;
import br.unitins.tp1.service.PrecificacaoService;
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

@Path("/precificacoes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PrecificacaoResource {

    @Inject PrecificacaoService service;

    @POST
    public Response create(@Valid PrecificacaoDTO dto) {
        return Response.status(Response.Status.CREATED).entity(service.create(dto)).build();
    }

    @GET
    public List<Precificacao> findAll() { return service.findAll(); }

    @GET
    @Path("/{id}")
    public Precificacao findById(@PathParam("id") Long id) { return service.findById(id); }

    @PUT
    @Path("/{id}")
    public Precificacao update(@PathParam("id") Long id, @Valid PrecificacaoDTO dto) {
        return service.update(id, dto);
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        service.delete(id);
        return Response.noContent().build();
    }
}