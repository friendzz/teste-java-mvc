package pessoas.Excetions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PessoaNaoEncontrada {
    String erro = "Pessoa não encontrada";
}
