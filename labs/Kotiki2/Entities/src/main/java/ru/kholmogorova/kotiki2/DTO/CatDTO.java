package ru.kholmogorova.kotiki2.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.kholmogorova.kotiki2.Entity.CatEntity;
import ru.kholmogorova.kotiki2.Entity.Color;

import java.sql.Date;
import java.time.LocalDate;

@NoArgsConstructor
@Data
public class CatDTO {
    public CatDTO(CatEntity catEntity) {
        id = catEntity.getId();
        name = catEntity.getName();
        birthDate = catEntity.getBirthDate();
        breed = catEntity.getBreed();
        color = catEntity.getColor();
    }

    private Long id;
    private String name;
    private LocalDate birthDate;
    private String breed;
    private Color color;
}
