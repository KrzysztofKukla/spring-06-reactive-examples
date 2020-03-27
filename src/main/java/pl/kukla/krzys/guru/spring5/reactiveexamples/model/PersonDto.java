package pl.kukla.krzys.guru.spring5.reactiveexamples.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Krzysztof Kukla
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonDto {

    private String firstName;
    private String lastName;
}
