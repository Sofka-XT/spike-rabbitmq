package grupoexito.reactive.repository.jpa.testdata;

import grupoexito.reactive.repository.jpa.AdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

@Repository
public class EntityRepositoryAdapter extends AdapterOperations<DomainEntity, DataEntity, String, DataRepository> {

    public EntityRepositoryAdapter(DataRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.mapBuilder(d, DomainEntity.DomainEntityBuilder.class).build());
    }
}
