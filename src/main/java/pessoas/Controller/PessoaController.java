package pessoas.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pessoas.Entity.Pessoa;
import pessoas.Excetions.Messages;
import pessoas.Excetions.NaoDadosFailedException;
import pessoas.Mapper.PessoaMapper;
import pessoas.Service.PessoaService;
import pessoas.dto.PessoaDTO;
import pessoas.Excetions.PessoaNaoEncontradaFailedException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {
    private final Logger log = LoggerFactory.getLogger(PessoaController.class);
    private final PessoaService service;

    public PessoaController(PessoaService service) {
        this.service = service;
    }

    @GetMapping(value = {"","/"})
    public ResponseEntity<?> findAll() throws NaoDadosFailedException {
        List<Pessoa> p;
        p = service.findAll();
        log.info(p.toString());

        //validate
//        if (p == null || p.isEmpty()) return ResponseEntity.noContent().build();
        if (p != null && !p.isEmpty() ) return ResponseEntity.ok(p);

        String s = Messages.NAO_TEM_DADOS.toString();
        System.out.println(s);
        throw  new NaoDadosFailedException(s);
//        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") UUID uuid) throws PessoaNaoEncontradaFailedException {

        Optional<Pessoa> pessoa = service.findById(uuid);

//        Optional<PessoaDTO> pessoaDTO = Optional.of(PessoaMapper.entityToDTO(pessoa.orElse(null)));
        Optional<PessoaDTO> pessoaDTO = pessoa.map(p1->  PessoaMapper.entityToDTO(p1) );
//        return pessoaDTO.map(ResponseEntity::ok)
//                .orElseGet(
//                        () -> ResponseEntity.status(HttpStatus.NOT_FOUND).build()
//                );


        if (pessoaDTO != null && !pessoaDTO.isEmpty() ) {return ResponseEntity.ok(pessoaDTO);}
        else {
            String s = Messages.PESSOA_NAO_ENCONTRADA.toString();
            System.out.println(s);
            throw  new PessoaNaoEncontradaFailedException(s);
        }

//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa não encontrada");


    }

    @PostMapping(value = {"","/"})
    public Pessoa create(@RequestBody Pessoa pessoa) {
        System.out.println(pessoa.toString());
        return service.save(pessoa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PessoaDTO> update(@PathVariable UUID uuid, @RequestBody Pessoa pessoa) {
        pessoa.setUuid(uuid);
        return ResponseEntity.ok(PessoaMapper.entityToDTO(service.update(pessoa)));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PessoaDTO> patch(@PathVariable UUID id, @RequestBody PessoaDTO pessoaDTO) {
        Optional<Pessoa> existingPessoa = service.findById(id);
        if (existingPessoa.isPresent()) {
            Pessoa updatedPessoa = existingPessoa.get();
            if (pessoaDTO.getNome() != null) updatedPessoa.setNome(pessoaDTO.getNome());
            if (pessoaDTO.getEndereco() != null) updatedPessoa.setEndereco(pessoaDTO.getEndereco());
            return ResponseEntity.ok(PessoaMapper.entityToDTO(service.update(updatedPessoa)));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
