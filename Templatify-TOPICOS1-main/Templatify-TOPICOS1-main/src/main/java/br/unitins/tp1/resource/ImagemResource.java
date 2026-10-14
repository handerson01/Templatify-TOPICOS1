package br.unitins.tp1.resource;

import java.util.List;

import br.unitins.tp1.dto.ImagemDTO;
import br.unitins.tp1.dto.ImagemResponseDTO;
import br.unitins.tp1.service.ImagemService;
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

@Path("/imagens")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ImagemResource {

    @Inject ImagemService service;

    @POST
    public Response create(@Valid ImagemDTO dto) {
        return Response.status(Response.Status.CREATED).entity(service.create(dto)).build();
    }

    @GET
    public List<ImagemResponseDTO> findAll() { return service.findAll(); }

    @GET
    @Path("/{id}")
    public ImagemResponseDTO findById(@PathParam("id") Long id) { return service.findById(id); }

    @GET
    @Path("/por-template/{idTemplate}")
    public List<ImagemResponseDTO> findByTemplate(@PathParam("idTemplate") Long idTemplate) {
        return service.findByTemplate(idTemplate);
    }

    @PUT
    @Path("/{id}")
    public ImagemResponseDTO update(@PathParam("id") Long id, @Valid ImagemDTO dto) {
        return service.update(id, dto);
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        service.delete(id);
        return Response.noContent().build();
    }
}