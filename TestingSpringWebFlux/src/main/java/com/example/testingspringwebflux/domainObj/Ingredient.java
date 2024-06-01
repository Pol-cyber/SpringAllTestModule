package com.example.testingspringwebflux.domainObj;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = "id")
public class Ingredient {

    @Id
    private Integer id;

    private @NonNull String slug;
    private @NonNull String name;
    private @NonNull Type type;
    public enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }
}
