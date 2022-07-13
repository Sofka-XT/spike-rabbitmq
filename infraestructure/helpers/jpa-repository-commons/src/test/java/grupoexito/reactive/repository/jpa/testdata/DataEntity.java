package grupoexito.reactive.repository.jpa.testdata;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
class DataEntity {
    @Id
    private String id;
    private String name;
    private Date birthDate;
    private Long size;
}
