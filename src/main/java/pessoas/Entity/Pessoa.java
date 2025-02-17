package pessoas.Entity;

/* Spring Boot 2.7
import javax.persitence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
*/

/* Spring Boot 3.0
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
*/

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@Entity
@Table(name = "pessoa", schema = "public")
public class Pessoa {

    @Id
    @Column(name = "uuid", unique = true)
    /* Any of this could be used for generating UUID for the server side.
    When we use uuid from the device, we must comment this.
    */
//    @GeneratedValue(generator="system-uuid")
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @GenericGenerator(name="system-uuid", strategy = "uuid")
//    @GeneratedValue(generator = "UUID")

//    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @UuidGenerator

    private UUID uuid;
    private String nome;
    private String endereco;

    // Getters e Setters

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

}
