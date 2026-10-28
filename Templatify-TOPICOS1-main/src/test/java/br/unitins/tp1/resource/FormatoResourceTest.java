package br.unitins.tp1.resource;

import br.unitins.tp1.dto.FormatoDTO;
import br.unitins.tp1.model.Formato;
import br.unitins.tp1.service.FormatoService;
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
class FormatoResourceTest {

    @Mock
    FormatoService service;

    @InjectMocks
    FormatoResource resource;

    @Test
    void createRetornaCreatedComFormatoCriado() {
        FormatoDTO dto = new FormatoDTO("Imagem", "PNG");
        Formato formato = new Formato();
        when(service.create(dto)).thenReturn(formato);

        Response response = resource.create(dto);

        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
        assertSame(formato, response.getEntity());
        verify(service).create(dto);
    }

    @Test
    void consultasEUpdateDelegamAoService() {
        FormatoDTO dto = new FormatoDTO("Imagem", "PNG");
        Formato formato = new Formato();
        List<Formato> formatos = List.of(formato);
        when(service.findAll()).thenReturn(formatos);
        when(service.findById(3L)).thenReturn(formato);
        when(service.findByNome("Imagem")).thenReturn(formatos);
        when(service.update(3L, dto)).thenReturn(formato);

        assertSame(formatos, resource.findAll());
        assertSame(formato, resource.findById(3L));
        assertSame(formatos, resource.findByNome("Imagem"));
        assertSame(formato, resource.update(3L, dto));

        verify(service).findAll();
        verify(service).findById(3L);
        verify(service).findByNome("Imagem");
        verify(service).update(3L, dto);
    }

    @Test
    void deleteRetornaNoContent() {
        Response response = resource.delete(3L);

        assertEquals(Response.Status.NO_CONTENT.getStatusCode(), response.getStatus());
        verify(service).delete(3L);
    }
}
