package leodepaula.com.loja_de_games.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import leodepaula.com.loja_de_games.dto.CategoriaDTO;
import leodepaula.com.loja_de_games.model.Categoria;
import leodepaula.com.loja_de_games.repository.CategoriaRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public Flux<CategoriaDTO> buscarTodasCategorias() {
        return categoriaRepository.findAll().map(this::toDTO);
    }

    public Mono<CategoriaDTO> buscarPorId(Long id) {
        return categoriaRepository.findById(id).map(this::toDTO)
                .switchIfEmpty(monoResponseStatusNotFoundException());
    }

    public Flux<CategoriaDTO> buscarPorNome(String nome) {
        return categoriaRepository.findByNomeContainingIgnoreCase(nome).map(this::toDTO)
                .switchIfEmpty(monoResponseStatusNotFoundException());
    }

    public Mono<Categoria> criarCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public Mono<Void> atualizarCategoria(Categoria categoria) {
        return buscarPorId(categoria.getId()) // garante que existe
                .flatMap(existing -> categoriaRepository.save(categoria)).then();
    }

    public Mono<Void> deletarCategoria(Long id) {
        return categoriaRepository.findById(id).switchIfEmpty(monoResponseStatusNotFoundException())
                .flatMap(existing -> categoriaRepository.deleteById(id));
    }

    private CategoriaDTO toDTO(Categoria categoria) {
        return new CategoriaDTO(categoria.getId(), categoria.getNome(), categoria.getDescricao());
    }

    public <T> Mono<T> monoResponseStatusNotFoundException() {
        return Mono.error(
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria não encontrada"));
    }
}
