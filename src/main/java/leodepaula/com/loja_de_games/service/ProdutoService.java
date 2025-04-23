package leodepaula.com.loja_de_games.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import leodepaula.com.loja_de_games.dto.ProdutoComCategoriaDTO;
import leodepaula.com.loja_de_games.model.Produto;
import leodepaula.com.loja_de_games.repository.CategoriaRepository;
import leodepaula.com.loja_de_games.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;

    public Mono<ProdutoComCategoriaDTO> buscarProdutoComCategoriaById(Long idProduto) {
        return findById(idProduto).flatMap(this::mapToProdutoComCategoriaDTO);
    }

    public Flux<ProdutoComCategoriaDTO> buscarTodosProdutosComCategoria() {
        return produtoRepository.findAll().flatMap(this::mapToProdutoComCategoriaDTO);
    }

    public Flux<ProdutoComCategoriaDTO> buscarProdutoByNome(String nome) {
        return produtoRepository.findByNomeContainingIgnoringCase(nome)
                .flatMap(this::mapToProdutoComCategoriaDTO)
                .switchIfEmpty(monoResponseStatusNotFoundException());
    }

    public Mono<Produto> save(Produto produto) {
        return produtoRepository.save(produto);
    }

    public Mono<Void> update(ProdutoComCategoriaDTO dto) {
        return findById(dto.id())
                .then(categoriaRepository.findById(dto.categoriaId())
                        .switchIfEmpty(Mono.error(new ResponseStatusException(
                                HttpStatus.BAD_REQUEST, "Categoria não encontrada"))))
                .then(produtoRepository.save(
                        new Produto(dto.id(), dto.nome(), dto.preco(), dto.marca(), dto.descricao(),
                                dto.quantidade(), dto.dataCriacao(), dto.categoriaId())))
                .then();
    }


    public Mono<Produto> findById(Long id) {
        return produtoRepository.findById(id).switchIfEmpty(monoResponseStatusNotFoundException());
    }

    public Mono<Void> delete(Long id) {
        return findById(id).flatMap(produtoRepository::delete);
    }

    private Mono<ProdutoComCategoriaDTO> mapToProdutoComCategoriaDTO(Produto produto) {
        return categoriaRepository.findById(produto.getCategoriaId())
                .map(categoria -> new ProdutoComCategoriaDTO(produto.getId(), produto.getNome(),
                        produto.getPreco(), produto.getDescricao(), produto.getMarca(),
                        produto.getQuantidade(), produto.getDataCriacao(), categoria.getId(),
                        categoria.getNome(), categoria.getDescricao()));
    }

    public <T> Mono<T> monoResponseStatusNotFoundException() {
        return Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não existe"));
    }
}
