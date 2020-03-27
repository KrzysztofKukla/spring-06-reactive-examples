package pl.kukla.krzys.guru.spring5.reactiveexamples.bootstrap;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import pl.kukla.krzys.guru.spring5.reactiveexamples.domain.Person;
import pl.kukla.krzys.guru.spring5.reactiveexamples.repository.PersonMongoRepository;
import pl.kukla.krzys.guru.spring5.reactiveexamples.repository.PersonReactiveMongoRepository;
import reactor.core.publisher.Mono;

import java.util.Arrays;

/**
 * @author Krzysztof Kukla
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class PersonBootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private final PersonMongoRepository personMongoRepository;
    private final PersonReactiveMongoRepository personReactiveMongoRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if (personMongoRepository.count() == 0) {
            populatePerson();
        }
    }

    private void populatePerson() {
        Mono<Long> monoCount = personReactiveMongoRepository.count();
        Person person1 = Person.builder().firstName("first firstName").lastName("first lastName").build();
        Person person2 = Person.builder().firstName("Second firstName").lastName("Second lastName").build();
        Person person3 = Person.builder().firstName("Third firstName").lastName("Third lastName").build();
        Person person4 = Person.builder().firstName("Fourth firstName").lastName("Fourth lastName").build();

        personMongoRepository.saveAll(Arrays.asList(person1, person2, person3, person4));

        log.debug("Count-> {}", monoCount.block());
    }

}
