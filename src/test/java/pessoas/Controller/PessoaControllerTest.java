package pessoas.Controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pessoas.Entity.Pessoa;
import pessoas.Mapper.PessoaMapper;
import pessoas.Service.PessoaService;
import pessoas.dto.PessoaDTO;

import java.util.*;


@SpringBootTest
@WebMvcTest(PessoaController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PessoaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private PessoaService service;

    @InjectMocks
    private PessoaController controller;

    private ObjectMapper objectMapper;

    private Pessoa pessoa;
    private PessoaDTO pessoaDTO;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        pessoa = new Pessoa();
        pessoa.setNome("Jose");
        pessoa.setEndereco("Rua nessa, 123");
        pessoaDTO = PessoaMapper.entityToDTO(pessoa);
    }

    @Test
    void testFindAll() throws Exception {
        List<Pessoa> pessoas = Arrays.asList(pessoa);
        when(service.findAll()).thenReturn(pessoas);

        mockMvc.perform(get("/pessoas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value(pessoa.getNome()))
                .andExpect(jsonPath("$[0].endereco").value(pessoa.getEndereco()));
    }

    @Test
    @Order(1)
    void testFindAllNotFound() throws Exception {
        when(service.findAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/pessoas"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testFindById() throws Exception {
        when(service.findById(any(UUID.class))).thenReturn(Optional.of(pessoa));

        mockMvc.perform(get("/pessoas/{id}", pessoa.getUuid()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value(pessoa.getNome()))
                .andExpect(jsonPath("$.endereco").value(pessoa.getEndereco()));
    }

    @Test
    void testFindByIdNotFound() throws Exception {
        when(service.findById(any(UUID.class))).thenReturn(Optional.empty());

        mockMvc.perform(get("/pessoas/{id}", UUID.randomUUID()))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreate() throws Exception {
        when(service.save(any(Pessoa.class))).thenReturn(pessoa);

        mockMvc.perform(post("/pessoas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pessoa)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value(pessoa.getNome()))
                .andExpect(jsonPath("$.endereco").value(pessoa.getEndereco()));
    }

    @Test
    void testUpdate() throws Exception {
        when(service.update(any(Pessoa.class))).thenReturn(pessoa);

        mockMvc.perform(put("/pessoas/{id}", pessoa.getUuid())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pessoa)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value(pessoa.getNome()))
                .andExpect(jsonPath("$.endereco").value(pessoa.getEndereco()));
    }

    @Test
    void testPatch() throws Exception {
        when(service.findById(any(UUID.class))).thenReturn(Optional.of(pessoa));
        when(service.update(any(Pessoa.class))).thenReturn(pessoa);

        pessoaDTO.setNome("Novo Nome");

        mockMvc.perform(patch("/pessoas/{id}", pessoa.getUuid())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pessoaDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Novo Nome"))
                .andExpect(jsonPath("$.endereco").value(pessoa.getEndereco()));
    }

    @Test
    void testDelete() throws Exception {
        doNothing().when(service).deleteById(any(UUID.class));

        mockMvc.perform(delete("/pessoas/{id}", pessoa.getUuid()))
                .andExpect(status().isNoContent());
    }
}