package leodepaula.com.loja_de_games.controller;

import jakarta.validation.Valid;
import leodepaula.com.loja_de_games.dto.CategoriaDTO;
import leodepaula.com.loja_de_games.model.Categoria;
import leodepaula.com.loja_de_games.service.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/categorias")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaService categoriaService;

    @GetMapping
    public Flux<CategoriaDTO> buscarTodas() {
        return categoriaService.buscarTodasCategorias();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<CategoriaDTO>> buscarPorId(@PathVariable Long id) {
        return categoriaService.buscarPorId(id).map(ResponseEntity::ok);
    }

    @GetMapping("/nome/{nome}")
    public Flux<CategoriaDTO> buscarPorNome(@PathVariable String nome) {
        return categoriaService.buscarPorNome(nome);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Categoria> criar(@Valid @RequestBody Categoria categoria) {
        return categoriaService.criarCategoria(categoria);
    }

    @PutMapping
    public Mono<ResponseEntity<Void>> atualizar(@Valid @RequestBody Categoria categoria) {
        return categoriaService.atualizarCategoria(categoria)
                .thenReturn(ResponseEntity.noContent().build());
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deletar(@PathVariable Long id) {
        return categoriaService.deletarCategoria(id).thenReturn(ResponseEntity.noContent().build());
    }
}
