package pl.kukla.krzys.guru.spring5.reactiveexamples.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Krzysztof Kukla
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document // indicate object as mongo document
public class Person {

    @Id
    private String id;
    private String firstName;
    private String lastName;

}
