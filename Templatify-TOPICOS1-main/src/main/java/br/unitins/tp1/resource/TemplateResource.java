package br.unitins.tp1.resource;


import java.math.BigDecimal;
import java.util.List;
import br.unitins.tp1.dto.TemplateResponseDTO;

import br.unitins.tp1.service.TemplateService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.core.MediaType;

@Path("/templates")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TemplateResource {
    @Inject 
    TemplateService service;
    
    @GET 
    @Operation(hidden = true)
    public List<TemplateResponseDTO> listar() {
        return service.findAll();
    }

    @GET 
    @Operation(hidden = true)
    @Path("/{id}")
    public TemplateResponseDTO buscarPorId(@PathParam("id") Long id) {
        return service.findById(id);
    }

    @GET 
    @Operation(hidden = true)
    @Path ("/nome/{nome}")
    public List<TemplateResponseDTO> buscarPornome(@PathParam("nome") String nome){
        return service.findByNome(nome);
    }

    @GET 
    @Operation(hidden = true)
    @Path ("/descricao/{descricao}")
    public List<TemplateResponseDTO> buscarPorDescricao(@PathParam("descricao") String descricao){
        return service.findByDescricao(descricao);

    }

    @GET 
    @Operation(hidden = true)
    @Path ("/categoria/{categoria}")
    public List<TemplateResponseDTO> buscarPorCategoria(@PathParam("categoria") String categoria) {
        return service.findByCategoria(categoria);
    }

    @GET 
    @Operation(hidden = true)
    @Path("/formato/{formato}")
    public List<TemplateResponseDTO> buscarPorFormato(@PathParam ("formato") String formato) {
        return service.findByFormato(formato);
    }

    @GET 
    @Operation(hidden = true)
    @Path("/autor/{autor}")
    public List<TemplateResponseDTO> buscarPorAutor(@PathParam ("autor") String autor){
        return service.findByAutor(autor);
    }

    @GET 
    @Operation(hidden = true)
    @Path ("/preco/{preco}")
    public List<TemplateResponseDTO> buscarPorPreco(@PathParam ("preco") BigDecimal preco) {
        return service.findByPreco(preco);
    }

    @GET 
    @Operation(hidden = true)
    @Path ("/imagemUrl/{imagemUrl}")
    public List<TemplateResponseDTO> buscarPorImagemUrl(@PathParam ("imagemUrl") String imagemUrl) {
        return service.findByImagemUrl(imagemUrl);
    }
}
