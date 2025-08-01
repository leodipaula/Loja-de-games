package leodepaula.com.loja_de_games.repository;

import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import leodepaula.com.loja_de_games.model.Produto;
import reactor.core.publisher.Flux;

public interface ProdutoRepository extends ReactiveCrudRepository<Produto, Long> {
    Flux<Produto> findByNomeContainingIgnoringCase(@Param("nome") String nome);
}
