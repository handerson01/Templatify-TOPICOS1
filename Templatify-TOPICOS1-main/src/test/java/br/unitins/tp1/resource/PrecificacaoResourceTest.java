package br.unitins.tp1.resource;

import br.unitins.tp1.dto.PrecificacaoDTO;
import br.unitins.tp1.model.Precificacao;
import br.unitins.tp1.service.PrecificacaoService;
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
class PrecificacaoResourceTest {

    @Mock
    PrecificacaoService service;

    @InjectMocks
    PrecificacaoResource resource;

    @Test
    void createRetornaCreatedComPrecificacaoCriada() {
        PrecificacaoDTO dto = org.mockito.Mockito.mock(PrecificacaoDTO.class);
        Precificacao precificacao = new Precificacao();
        when(service.create(dto)).thenReturn(precificacao);

        Response response = resource.create(dto);

        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
        assertSame(precificacao, response.getEntity());
        verify(service).create(dto);
    }

    @Test
    void consultasEUpdateDelegamAoService() {
        PrecificacaoDTO dto = org.mockito.Mockito.mock(PrecificacaoDTO.class);
        Precificacao precificacao = new Precificacao();
        List<Precificacao> precificacoes = List.of(precificacao);
        when(service.findAll()).thenReturn(precificacoes);
        when(service.findById(4L)).thenReturn(precificacao);
        when(service.update(4L, dto)).thenReturn(precificacao);

        assertSame(precificacoes, resource.findAll());
        assertSame(precificacao, resource.findById(4L));
        assertSame(precificacao, resource.update(4L, dto));

        verify(service).findAll();
        verify(service).findById(4L);
        verify(service).update(4L, dto);
    }

    @Test
    void deleteRetornaNoContent() {
        Response response = resource.delete(4L);

        assertEquals(Response.Status.NO_CONTENT.getStatusCode(), response.getStatus());
        verify(service).delete(4L);
    }
}
