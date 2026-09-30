package br.unitins.tp1.resource;


import java.util.List;
import br.unitins.tp1.model.Template;

import br.unitins.tp1.service.TemplateService;
import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
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
    public List<Template> listar() {
        return service.findAll();
    }

    @GET 
    @Path("/{id}")
    public Template buscarPorId(@PathParam("id") Long id) {
        return service.findById(id);
    }

    @GET 
    @Path ("/nome/{nome}")
    public List<Template> buscarPornome(@PathParam("nome") String nome){
        return service.findByNome(nome);
    }

    @GET 
    @Path ("/descricao/{descricao}")
    public List<Template> buscarPorDescricao(@PathParam("descricao") String descricao){
        return service.findByDescricao(descricao);

    }

    @GET 
    @Path ("/categoria/{categoria}")
    public List<Template> buscarPorCategoria(@PathParam("categoria") String categoria) {
        return service.findByCategoria(categoria);
    }

    @GET 
    @Path("/formato/{formato}")
    public List<Template> buscarPorFormato(@PathParam ("formato") String formato) {
        return service.findByFormato(formato);
    }

    @GET 
    @Path("/autor/{autor}")
    public List<Template> buscarPorAutor(@PathParam ("autor") String autor){
        return service.findByAutor(autor);
    }

    @GET 
    @Path ("/preco/{preco}")
    public List<Template> buscarPorPreco(@PathParam ("preco") Double preco) {
        return service.findByPreco(preco);
    }

    @GET 
    @Path ("/imagemUrl/{imagemUrl}")
    public List<Template> buscarPorImagemUrl(@PathParam ("imagemUrl") String imagemUrl) {
        return service.findByImagemUrl(imagemUrl);
    }

    @POST 
    public Template inserir(Template template) {
        return service.create(template);
    }

    @PUT 
    @Path("/{id}")
    public void atualizar(@PathParam ("id") Long id, Template template){
        service.update(id, template);
    }

    @DELETE
    @Path ("/{id}")
    public void excluir(@PathParam ("id") Long id ){
        service.delete(id);
    }


}
