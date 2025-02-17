package pessoas.Service;


import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pessoas.Entity.Pessoa;
import pessoas.Excetions.NaoDadosFailedException;
import pessoas.Excetions.PessoaNaoEncontradaFailedException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@PropertySource("classpath:/application.properties")
@Service
public interface PessoaValidateService {

    public ResponseEntity throwNoData() throws NaoDadosFailedException;
    public ResponseEntity throwNoContent() throws PessoaNaoEncontradaFailedException;
}
