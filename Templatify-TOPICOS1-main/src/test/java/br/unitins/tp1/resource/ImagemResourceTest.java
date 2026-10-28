package br.unitins.tp1.resource;

import br.unitins.tp1.dto.ImagemDTO;
import br.unitins.tp1.dto.ImagemResponseDTO;
import br.unitins.tp1.service.ImagemService;
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
class ImagemResourceTest {

    @Mock
    ImagemService service;

    @InjectMocks
    ImagemResource resource;

    @Test
    void createRetornaCreatedComImagemCriada() {
        ImagemDTO dto = new ImagemDTO("https://example.com/image.png", "Capa", 12L);
        ImagemResponseDTO imagem = new ImagemResponseDTO(
                5L, "https://example.com/image.png", "Capa", 12L);
        when(service.create(dto)).thenReturn(imagem);

        Response response = resource.create(dto);

        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
        assertSame(imagem, response.getEntity());
        verify(service).create(dto);
    }

    @Test
    void consultasEUpdateDelegamAoService() {
        ImagemDTO dto = new ImagemDTO("https://example.com/image.png", "Capa", 12L);
        ImagemResponseDTO imagem = new ImagemResponseDTO(
                5L, "https://example.com/image.png", "Capa", 12L);
        List<ImagemResponseDTO> imagens = List.of(imagem);
        when(service.findAll()).thenReturn(imagens);
        when(service.findById(5L)).thenReturn(imagem);
        when(service.findByTemplate(12L)).thenReturn(imagens);
        when(service.update(5L, dto)).thenReturn(imagem);

        assertSame(imagens, resource.findAll());
        assertSame(imagem, resource.findById(5L));
        assertSame(imagens, resource.findByTemplate(12L));
        assertSame(imagem, resource.update(5L, dto));

        verify(service).findAll();
        verify(service).findById(5L);
        verify(service).findByTemplate(12L);
        verify(service).update(5L, dto);
    }

    @Test
    void deleteRetornaNoContent() {
        Response response = resource.delete(5L);

        assertEquals(Response.Status.NO_CONTENT.getStatusCode(), response.getStatus());
        verify(service).delete(5L);
    }
}
