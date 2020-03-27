package pl.kukla.krzys.guru.spring5.reactiveexamples.use;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.kukla.krzys.guru.spring5.reactiveexamples.domain.Person;
import pl.kukla.krzys.guru.spring5.reactiveexamples.model.PersonDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.concurrent.CountDownLatch;

/**
 * @author Krzysztof Kukla
 */
@Slf4j
class ReactiveExampleTest {

    //in reactive programming we are dealing with future objects and passing those objects further
    // and when something asks for that data then ig gonna trigger all that logic int our code
    Person person1;
    Person person2;
    Person person3;
    Person person4;

    @BeforeEach
    void setUp() {
        person1 = Person.builder().firstName("first firstName").lastName("first lastName").build();
        person2 = Person.builder().firstName("Second firstName").lastName("Second lastName").build();
        person3 = Person.builder().firstName("Third firstName").lastName("Third lastName").build();
        person4 = Person.builder().firstName("Fourth firstName").lastName("Fourth lastName").build();
    }

    @Test
    void monoTest() throws Exception {
        //creating Mono publisher for 0 or 1 element in data stream
        Mono<Person> personMono1 = Mono.just(person1);

        //get Person from Mono publisher
        //block says 'run this now'
        Person personFromMono = personMono1.block();

        log.info("Last name for person {} ", personFromMono);
    }

    @Test
    void monoTransform() throws Exception {
        Mono<Person> personMono2 = Mono.just(person2);

        //it will be execute in the future until we don't call 'block' method on this Mono object
        Mono<PersonDto> personDtoMono2 = personMono2.map(person -> {
            return PersonDto.builder()
                .firstName(person.getFirstName())
                .lastName(person.getFirstName())
                .build();
        });
        PersonDto personDto2 = personDtoMono2.block();

        log.info("PersonDto2-> {}", personDto2);
    }

    @Test
    void monoFilter() throws Exception {
        Mono<Person> personMono3 = Mono.just(person3);

        Mono<Person> afterFiltered = personMono3.filter(person -> person.getFirstName().contains("third"));
        Person person3 = afterFiltered.block();

        log.info("Person-> {}", person3);
    }

    @Test
    void fluxTest() throws Exception {
        //create Flux - publisher for 0 or many elements
        Flux<Person> personFlux = Flux.just(person1, person2, person3, person4);

        personFlux.subscribe(person -> log.info("Person-> {}", person));

    }

    @Test
    void fluxFilterTest() throws Exception {
        Flux<Person> fluxPerson = Flux.just(person1, person2, person3, person4);

        fluxPerson.filter(person -> person.getLastName().contains("Second"))
            .subscribe(p -> log.info("Person-> {}", p));
    }

    @Test
    void fluxDelayOutputTest() throws Exception {
        Flux<Person> fluxPerson = Flux.just(person1, person2, person3, person4);

        //this is running in background, so we didn't wait for this output
        //so the test is terminated before Duration expired ( one second )
        fluxPerson.delayElements(Duration.ofSeconds(1))
            .subscribe(person -> log.info("person-> {}", person));

    }

    @Test
    void fluxDelayTest() throws Exception {
        CountDownLatch countDownLatch = new CountDownLatch(1);

        Flux<Person> fluxPerson = Flux.just(person1, person2, person3, person4);

        //on complete that stream we gonna count down latch
        fluxPerson.delayElements(Duration.ofSeconds(1))
            .doOnComplete(() -> countDownLatch.countDown())
            .subscribe(person -> log.info("person-> {}", person));

        //it tells the test to wait until countdown is complete
        countDownLatch.await();
    }

    @Test
    void fluxFilterDelayTest() throws Exception {
        CountDownLatch countDownLatch = new CountDownLatch(1);

        Flux<Person> fluxPerson = Flux.just(person1, person2, person3, person4);

        fluxPerson.delayElements(Duration.ofSeconds(1))
            .filter(p -> p.getFirstName().contains("lastName"))
            .doOnComplete(() -> countDownLatch.countDown())
            .subscribe(pe -> log.info("per-> {}", pe));

        countDownLatch.await();

    }

}