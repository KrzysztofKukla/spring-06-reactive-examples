package pl.kukla.krzys.guru.spring5.reactiveexamples.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import pl.kukla.krzys.guru.spring5.reactiveexamples.domain.Person;

/**
 * @author Krzysztof Kukla
 */
public interface PersonReactiveMongoRepository extends ReactiveMongoRepository<Person, String> {
}
