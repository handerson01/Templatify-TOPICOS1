package br.unitins.tp1.resource;

import br.unitins.tp1.dto.TemplateResponseDTO;
import br.unitins.tp1.service.TemplateService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TemplateResourceTest {

    @Mock
    TemplateService service;

    @InjectMocks
    TemplateResource resource;

    @Test
    void consultasDelegamAoServiceERetornamResultados() {
        TemplateResponseDTO template = new TemplateResponseDTO(
                6L, "Template", "Descrição", true, 1L, 2L, 3L, 4L);
        List<TemplateResponseDTO> templates = List.of(template);
        BigDecimal preco = new BigDecimal("19.90");
        when(service.findAll()).thenReturn(templates);
        when(service.findById(6L)).thenReturn(template);
        when(service.findByNome("nome")).thenReturn(templates);
        when(service.findByDescricao("descricao")).thenReturn(templates);
        when(service.findByCategoria("categoria")).thenReturn(templates);
        when(service.findByFormato("formato")).thenReturn(templates);
        when(service.findByAutor("autor")).thenReturn(templates);
        when(service.findByPreco(preco)).thenReturn(templates);
        when(service.findByImagemUrl("imagem")).thenReturn(templates);

        assertSame(templates, resource.listar());
        assertSame(template, resource.buscarPorId(6L));
        assertSame(templates, resource.buscarPornome("nome"));
        assertSame(templates, resource.buscarPorDescricao("descricao"));
        assertSame(templates, resource.buscarPorCategoria("categoria"));
        assertSame(templates, resource.buscarPorFormato("formato"));
        assertSame(templates, resource.buscarPorAutor("autor"));
        assertSame(templates, resource.buscarPorPreco(preco));
        assertSame(templates, resource.buscarPorImagemUrl("imagem"));

        verify(service).findAll();
        verify(service).findById(6L);
        verify(service).findByNome("nome");
        verify(service).findByDescricao("descricao");
        verify(service).findByCategoria("categoria");
        verify(service).findByFormato("formato");
        verify(service).findByAutor("autor");
        verify(service).findByPreco(preco);
        verify(service).findByImagemUrl("imagem");
    }
}
