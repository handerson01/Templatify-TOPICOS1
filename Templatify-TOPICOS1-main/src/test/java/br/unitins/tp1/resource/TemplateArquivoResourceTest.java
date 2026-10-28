package br.unitins.tp1.resource;

import br.unitins.tp1.dto.TemplateArquivoDTO;
import br.unitins.tp1.dto.TemplateArquivoResponseDTO;
import br.unitins.tp1.service.TemplateArquivoService;
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
class TemplateArquivoResourceTest {

    @Mock
    TemplateArquivoService service;

    @InjectMocks
    TemplateArquivoResource resource;

    @Test
    void findAllFindByIdAndFindByNomeReturnOkWithServiceResults() {
        TemplateArquivoResponseDTO template = new TemplateArquivoResponseDTO(
                7L, "Template", "Descrição", 1L, 2L, 3L, 4L,
                true, "https://example.com/template.zip", 1024L, "checksum");
        List<TemplateArquivoResponseDTO> templates = List.of(template);
        when(service.findAll()).thenReturn(templates);
        when(service.findById(7L)).thenReturn(template);
        when(service.findByNome("Template")).thenReturn(templates);

        Response allResponse = resource.findAll();
        Response idResponse = resource.findById(7L);
        Response nameResponse = resource.findByNome("Template");

        assertEquals(Response.Status.OK.getStatusCode(), allResponse.getStatus());
        assertSame(templates, allResponse.getEntity());
        assertEquals(Response.Status.OK.getStatusCode(), idResponse.getStatus());
        assertSame(template, idResponse.getEntity());
        assertEquals(Response.Status.OK.getStatusCode(), nameResponse.getStatus());
        assertSame(templates, nameResponse.getEntity());
        verify(service).findAll();
        verify(service).findById(7L);
        verify(service).findByNome("Template");
    }

    @Test
    void createRetornaCreatedEUpdateRetornaOk() {
        TemplateArquivoDTO dto = org.mockito.Mockito.mock(TemplateArquivoDTO.class);
        TemplateArquivoResponseDTO template = new TemplateArquivoResponseDTO(
                7L, "Template", "Descrição", 1L, 2L, 3L, 4L,
                true, "https://example.com/template.zip", 1024L, "checksum");
        when(service.create(dto)).thenReturn(template);
        when(service.update(7L, dto)).thenReturn(template);

        Response createResponse = resource.create(dto);
        Response updateResponse = resource.update(7L, dto);

        assertEquals(Response.Status.CREATED.getStatusCode(), createResponse.getStatus());
        assertSame(template, createResponse.getEntity());
        assertEquals(Response.Status.OK.getStatusCode(), updateResponse.getStatus());
        assertSame(template, updateResponse.getEntity());
        verify(service).create(dto);
        verify(service).update(7L, dto);
    }

    @Test
    void deleteRetornaNoContent() {
        Response response = resource.delete(7L);

        assertEquals(Response.Status.NO_CONTENT.getStatusCode(), response.getStatus());
        verify(service).delete(7L);
    }
}
