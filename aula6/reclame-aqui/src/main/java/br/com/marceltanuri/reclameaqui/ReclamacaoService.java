
package br.com.marceltanuri.reclameaqui;

import br.com.marceltanuri.reclameaqui.infrasctructure.client.TitleGeneratorClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;


@ApplicationScoped
public class ReclamacaoService {

    private final List<Reclamacao> reclamacoes = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong(0);

    @Inject
    @RestClient
    TitleGeneratorClient titleGeneratorClient;

    public List<Reclamacao> listar(String filtro, int pagina, int tamanhoPagina) {
        return reclamacoes.stream()
                .filter(r -> filtro == null || filtro.isEmpty() ||
                        (r.getTitulo() != null && r.getTitulo().toLowerCase().contains(filtro.toLowerCase())) ||
                        (r.getDescricao() != null && r.getDescricao().toLowerCase().contains(filtro.toLowerCase())))
                .skip((long) pagina * tamanhoPagina)
                .limit(tamanhoPagina)
                .collect(Collectors.toList());
    }

    public Reclamacao buscar(Long id) {
        return reclamacoes.stream()
                .filter(r -> r.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void criar(Reclamacao reclamacao) {
        if (reclamacao.getTitulo() == null || reclamacao.getTitulo().isBlank()) {
            List<String> response = titleGeneratorClient.generate("all-meat", 1, 1);
            if (response != null && !response.isEmpty()) {
                reclamacao.setTitulo(response.get(0));
            }
        }

        reclamacao.setId(counter.incrementAndGet());
        reclamacoes.add(reclamacao);
    }

    public Reclamacao atualizar(Long id, Reclamacao reclamacao) {
        Reclamacao existente = buscar(id);
        if (existente != null) {
            existente.setTitulo(reclamacao.getTitulo());
            existente.setDescricao(reclamacao.getDescricao());
            existente.setAutor(reclamacao.getAutor());
        }
        return existente;
    }

    public void deletar(Long id) {
        reclamacoes.removeIf(r -> r.getId().equals(id));
    }
}
