//package grupoexito.reactive.repository.jpa;
//
//import grupoexito.reactive.repository.jpa.testdata.DomainEntity;
//import grupoexito.reactive.repository.jpa.testdata.EntityRepositoryAdapter;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;
//import reactor.test.StepVerifier;
//
//import java.util.Date;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class AdapterOperationsTest {
//
//    @Autowired
//    private EntityRepositoryAdapter adapter;
//
//    private final DomainEntity entity = DomainEntity.builder().id("01").name("My Name").size(42L).birthDate(new Date()).build();
//    private final DomainEntity entity2 = DomainEntity.builder().id("02").name("My Name").size(43L).birthDate(new Date()).build();
//    private final DomainEntity entity3 = DomainEntity.builder().id("03").name("My Name").size(43L).birthDate(new Date()).build();
//    private final DomainEntity entity4 = DomainEntity.builder().id("04").name("My Name").size(44L).birthDate(new Date()).build();
//
//    @Before
//    public void init() {
//        adapter.saveAllEntities(Flux.just(entity, entity2, entity3, entity4)).blockLast();
//    }
//
//    @Test
//    public void shouldSaveAndFind() {
//        final Mono<DomainEntity> saved = adapter.save(entity);
//        StepVerifier.create(saved).expectNext(entity).verifyComplete();
//        StepVerifier.create(adapter.findById("01")).expectNext(entity).verifyComplete();
//    }
//
//
//    @Test
//    public void findByExample() {
//        final Flux<DomainEntity> byExample = adapter.findByExample(DomainEntity.builder().size(43L).build());
//        StepVerifier.create(byExample.map(DomainEntity::getId).collectList())
//            .assertNext(list -> assertThat(list).containsOnly(entity2.getId(), entity3.getId()))
//            .verifyComplete();
//
//    }
//}