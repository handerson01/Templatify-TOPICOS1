package br.unitins.tp1.resource;

import br.unitins.tp1.dto.CategoriaDTO;
import br.unitins.tp1.model.Categoria;
import br.unitins.tp1.service.CategoriaService;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoriaResourceTest {

    @Mock
    CategoriaService service;

    @InjectMocks
    CategoriaResource resource;

    @Test
    void criarRetornaCreatedComCategoriaCriada() {
        CategoriaDTO dto = new CategoriaDTO("Design", "Templates de design", true);
        Categoria categoria = new Categoria();
        when(service.salvar(dto)).thenReturn(categoria);

        Response response = resource.criar(dto);

        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
        assertSame(categoria, response.getEntity());
        verify(service).salvar(dto);
    }

    @Test
    void operacoesDeConsultaEAtualizacaoDelegamAoService() {
        CategoriaDTO dto = new CategoriaDTO("Design", "Templates de design", true);
        Categoria categoria = new Categoria();
        List<Categoria> categorias = List.of(categoria);
        when(service.listarTodos()).thenReturn(categorias);
        when(service.buscarPorId(3L)).thenReturn(categoria);
        when(service.atualizar(3L, dto)).thenReturn(categoria);
        when(service.inativar(3L)).thenReturn(categoria);

        assertSame(categorias, resource.listarTodos());
        assertSame(categoria, resource.buscarPorId(3L));
        assertSame(categoria, resource.atualizar(3L, dto));
        assertSame(categoria, resource.inativar(3L));

        verify(service).listarTodos();
        verify(service).buscarPorId(3L);
        verify(service).atualizar(3L, dto);
        verify(service).inativar(3L);
    }

    @Test
    void deletarRetornaNoContent() {
        Response response = resource.deletar(3L);

        assertEquals(Response.Status.NO_CONTENT.getStatusCode(), response.getStatus());
        verify(service).deletar(3L);
    }
}
