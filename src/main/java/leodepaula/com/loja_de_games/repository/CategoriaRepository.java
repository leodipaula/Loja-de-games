package leodepaula.com.loja_de_games.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import leodepaula.com.loja_de_games.model.Categoria;
import reactor.core.publisher.Flux;

public interface CategoriaRepository extends ReactiveCrudRepository<Categoria, Long> {
    Flux<Categoria> findByNomeContainingIgnoreCase(String nome);
}
