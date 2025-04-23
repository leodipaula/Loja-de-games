package leodepaula.com.loja_de_games.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ProdutoComCategoriaDTO(Long id, String nome, BigDecimal preco, String descricao,
        String marca, Integer quantidade, LocalDate dataCriacao, Long categoriaId,
        String categoriaNome, String categoriaDescricao) {
}


