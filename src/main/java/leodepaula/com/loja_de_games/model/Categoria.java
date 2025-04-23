package leodepaula.com.loja_de_games.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("tb_categorias")
public class Categoria {
    @Id
    private Long id;

    @NotNull(message = "O nome é obrigatório!")
    @NotBlank(message = "O nome não pode estar vazio!")
    @Size(min = 3, max = 100)
    private String nome;

    @NotNull(message = "A descrição é obrigatória!")
    @NotBlank(message = "A descrição não pode estar vazia!")
    @Size(min = 10, max = 255)
    private String descricao;
}
