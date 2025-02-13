package pessoas.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
//@Repository
public interface PessoaUUIDRepository<Pessoa, UUID> extends JpaRepository <Pessoa, UUID> {

    List<Pessoa> findAll();

    Optional<Pessoa> findByUuid(UUID uuid);

    default Optional<Pessoa>  findRequiredByUuid(UUID uuid) {
        return findByUuid( uuid );
    }
}



