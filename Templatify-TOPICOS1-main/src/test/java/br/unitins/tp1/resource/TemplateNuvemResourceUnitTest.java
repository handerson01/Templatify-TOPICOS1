package br.unitins.tp1.resource;

import br.unitins.tp1.dto.TemplateNuvemDTO;
import br.unitins.tp1.dto.TemplateNuvemResponseDTO;
import br.unitins.tp1.service.TemplateNuvemService;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import jakarta.ws.rs.core.UriInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.net.URI;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TemplateNuvemResourceUnitTest {

    @Mock
    TemplateNuvemService service;

    @Mock
    UriInfo uriInfo;

    @Mock
    UriBuilder uriBuilder;

    @InjectMocks
    TemplateNuvemResource resource;

    @Test
    void createRetornaCreatedComLocationERepresentacao() {
        TemplateNuvemDTO dto = org.mockito.Mockito.mock(TemplateNuvemDTO.class);
        TemplateNuvemResponseDTO template = template();
        URI location = URI.create("http://localhost/templates-nuvem/41");
        when(service.create(dto)).thenReturn(template);
        when(uriInfo.getAbsolutePathBuilder()).thenReturn(uriBuilder);
        when(uriBuilder.path("41")).thenReturn(uriBuilder);
        when(uriBuilder.build()).thenReturn(location);

        Response response = resource.create(dto);

        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
        assertEquals(location, response.getLocation());
        assertSame(template, response.getEntity());
        verify(service).create(dto);
    }

    @Test
    void consultasEUpdateDelegamAoService() {
        TemplateNuvemDTO dto = org.mockito.Mockito.mock(TemplateNuvemDTO.class);
        TemplateNuvemResponseDTO template = template();
        List<TemplateNuvemResponseDTO> templates = List.of(template);
        when(service.findAll()).thenReturn(templates);
        when(service.findById(41L)).thenReturn(template);
        when(service.findByNome("Cloud")).thenReturn(templates);
        when(service.update(41L, dto)).thenReturn(template);

        assertSame(templates, resource.findAll());
        assertSame(template, resource.findById(41L));
        assertSame(templates, resource.findByNome("Cloud"));
        assertSame(template, resource.update(41L, dto));

        verify(service).findAll();
        verify(service).findById(41L);
        verify(service).findByNome("Cloud");
        verify(service).update(41L, dto);
    }

    @Test
    void deleteRetornaNoContent() {
        Response response = resource.delete(41L);

        assertEquals(Response.Status.NO_CONTENT.getStatusCode(), response.getStatus());
        verify(service).delete(41L);
    }

    private TemplateNuvemResponseDTO template() {
        return new TemplateNuvemResponseDTO(
                41L, "Cloud", "Descrição", 1L, 2L, 3L, 4L,
                null, "BRL", true, "https://example.com", "Canva", false);
    }
}
