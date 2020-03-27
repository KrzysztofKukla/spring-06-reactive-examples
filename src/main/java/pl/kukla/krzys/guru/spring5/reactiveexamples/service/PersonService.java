package pl.kukla.krzys.guru.spring5.reactiveexamples.service;

import pl.kukla.krzys.guru.spring5.reactiveexamples.domain.Person;

import java.util.Set;

/**
 * @author Krzysztof Kukla
 */
public interface PersonService {
    Set<Person> findAll();

}
