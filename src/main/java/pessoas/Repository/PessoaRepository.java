package pessoas.Repository;

import org.springframework.stereotype.Repository;
import pessoas.Entity.Pessoa;

import java.util.Optional;
import java.util.UUID;

@Repository

public interface PessoaRepository extends PessoaUUIDRepository<Pessoa, UUID> {

    Optional<Pessoa> findIdByUuid(UUID uuid);
}
