package pl.kukla.krzys.guru.spring5.reactiveexamples.repository;

import org.springframework.data.repository.CrudRepository;
import pl.kukla.krzys.guru.spring5.reactiveexamples.domain.Person;

/**
 * @author Krzysztof Kukla
 */
public interface PersonMongoRepository extends CrudRepository<Person, String> {
}
