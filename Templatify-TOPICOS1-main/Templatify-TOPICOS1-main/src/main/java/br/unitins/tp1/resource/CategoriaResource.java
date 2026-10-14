package br.unitins.tp1.resource;

import br.unitins.tp1.dto.CategoriaDTO;
import br.unitins.tp1.model.Categoria;
import br.unitins.tp1.service.CategoriaService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/categorias")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CategoriaResource {

    @Inject
    CategoriaService service;

    @POST
    public Response criar(CategoriaDTO dto) {
        Categoria categoria = service.salvar(dto);
        return Response.status(Response.Status.CREATED).entity(categoria).build();
    }

    @GET
    public List<Categoria> listarTodos() {
        return service.listarTodos();
    }

    @GET
    @Path("/{id}")
    public Categoria buscarPorId(@PathParam("id") Long id) {
        return service.buscarPorId(id);
    }

    @PUT
    @Path("/{id}")
    public Categoria atualizar(@PathParam("id") Long id, CategoriaDTO dto) {
        return service.atualizar(id, dto);
    }

    @PUT
    @Path("/{id}/inativar")
    public Categoria inativar(@PathParam("id") Long id) {
        return service.inativar(id);
    }

    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") Long id) {
        service.deletar(id);
        return Response.noContent().build();
    }
}