package pessoas.Service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import pessoas.Entity.Pessoa;
import pessoas.Repository.PessoaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@PropertySource("classpath:/application.properties")
@Service
public class PessoaServiceImpl implements PessoaService {
    private final Logger log = LoggerFactory.getLogger(PessoaServiceImpl.class);

    @Autowired
    private final PessoaRepository repository;

    public PessoaServiceImpl(PessoaRepository repository) {
        this.repository = repository;
    }

    public List<Pessoa> findAll() {
        return repository.findAll();
    }

    public Optional<Pessoa> findById(UUID id) {
        return repository.findById(id);
    }

    public Pessoa save(Pessoa pessoa) {
        return repository.save(pessoa);
    }

    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

    public Pessoa update(Pessoa pessoa) {
        return repository.save(pessoa);
    }
}
