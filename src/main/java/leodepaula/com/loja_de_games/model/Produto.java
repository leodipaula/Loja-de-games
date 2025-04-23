package leodepaula.com.loja_de_games.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("tb_produtos")
public class Produto {

    @Id
    private Long id;

    @NotBlank(message = "O nome não pode estar vazio!")
    @NotNull(message = "O nome é obrigatório!")
    @Size(min = 3, max = 100)
    private String nome;

    @NotNull(message = "O preço do produto é obrigatório!")
    @PositiveOrZero(message = "O preço do produto precisa ser maior que zero!")
    private BigDecimal preco;

    @NotNull(message = "A descrição é obrigatória!")
    @NotBlank(message = "A descrição não pode estar vazia!")
    @Size(min = 10, max = 255)
    private String descricao;

    @NotNull(message = "A marca é obrigatória!")
    @NotBlank(message = "A marca não pode estar vazia!")
    @Size(min = 3, max = 100)
    private String marca;

    @NotNull(message = "A quantidade do produto é obrigatória!")
    @PositiveOrZero(message = "A quantidade precisa ser maior que zero!")
    private Integer quantidade;

    @Column("data_criacao")
    private LocalDate dataCriacao;

    @Column("categoria_id")
    private Long categoriaId;
}
