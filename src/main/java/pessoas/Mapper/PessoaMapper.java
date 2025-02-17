package pessoas.Mapper;

import org.springframework.stereotype.Component;
import pessoas.dto.PessoaDTO;
import pessoas.Entity.Pessoa;

import java.util.UUID;
@Component
public class PessoaMapper {
    public static Pessoa dtoToEntity (PessoaDTO pessoaDTO){
        Pessoa pessoa = new Pessoa();
        return pessoa;
    }

    public static PessoaDTO entityToDTO (Pessoa pessoa){
        PessoaDTO pessoaDTO;
        pessoaDTO = new PessoaDTO(pessoa.getUuid().toString(), pessoa.getNome(), pessoa.getEndereco());
//        pessoaDTO.setNome("nombre");
        return pessoaDTO;
    }
}
