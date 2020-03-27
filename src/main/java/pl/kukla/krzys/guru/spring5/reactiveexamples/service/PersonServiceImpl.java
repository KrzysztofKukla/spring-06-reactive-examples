package pl.kukla.krzys.guru.spring5.reactiveexamples.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kukla.krzys.guru.spring5.reactiveexamples.domain.Person;
import pl.kukla.krzys.guru.spring5.reactiveexamples.repository.PersonReactiveMongoRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Krzysztof Kukla
 */
@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {
    private final PersonReactiveMongoRepository personReactiveMongoRepository;

    @Override
    public Set<Person> findAll() {
//        Flux<Person> all = personMongoRepository.findAll();

//        TODO
        return new HashSet<>();
    }

}
