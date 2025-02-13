package pessoas.Repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.commons.annotation.Testable;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pessoas.Entity.Pessoa;
import pessoas.Service.PessoaService;

import java.util.UUID;
import java.sql.*;

import org.h2.jdbcx.JdbcConnectionPool;

@SpringBootTest
class PessoaRepositoryTest {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private PessoaService pessoaService;

    //    @InjectMocks
//    Service
    UUID u0;
    Pessoa p;

    @BeforeEach
    void setUp() {
    }

    @Test
    void repositoryTest() {

        System.out.println(pessoaRepository.findAll());
        var p2 = new Pessoa();
        p2.setUuid(UUID.randomUUID());
        p2.setNome("anonimo");
        p2.setEndereco("d");
        pessoaRepository.save(p2);

        System.out.println(pessoaRepository.findAll());

    }


    //    @Test
    void h2() {
        JdbcConnectionPool cp = JdbcConnectionPool.create(
                "jdbc:h2:mem:testdb", "sa", "password");
        Connection conn = null;
        try {
            conn = cp.getConnection();
            Assertions.assertTrue(conn.getCatalog().contains("TESTDB"));
            System.out.println(conn.getCatalog());
            System.out.println(conn.getClientInfo());
            System.out.println(conn.getSchema());
            conn.createStatement().execute("Select * from dual;");
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        cp.dispose();
    }
}