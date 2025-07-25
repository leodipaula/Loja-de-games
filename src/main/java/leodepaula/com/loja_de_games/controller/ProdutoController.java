package leodepaula.com.loja_de_games.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import leodepaula.com.loja_de_games.dto.ProdutoComCategoriaDTO;
import leodepaula.com.loja_de_games.model.Produto;
import leodepaula.com.loja_de_games.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;

    @GetMapping
    public Flux<ProdutoComCategoriaDTO> getTodosProdutos() {
        return produtoService.buscarTodosProdutosComCategoria();
    }

    @GetMapping("/{id}")
    public Mono<ProdutoComCategoriaDTO> getProdutoById(@Valid @PathVariable Long id) {
        return produtoService.buscarProdutoComCategoriaById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Produto> cadastrarProduto(@Valid @RequestBody Produto produto) {
        return produtoService.save(produto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deletarProduto(@PathVariable Long id) {
        return produtoService.delete(id);
    }

    @GetMapping("/nome/{nome}")
    public Flux<ProdutoComCategoriaDTO> getProdutoByNome(@PathVariable String nome) {
        return produtoService.buscarProdutoByNome(nome);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> atualizar(@Valid @RequestBody ProdutoComCategoriaDTO produto) {
        return produtoService.update(produto);
    }

}
