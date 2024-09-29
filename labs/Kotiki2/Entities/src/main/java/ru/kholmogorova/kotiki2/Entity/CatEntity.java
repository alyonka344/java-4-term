package ru.kholmogorova.kotiki2.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.kholmogorova.kotiki2.DTO.CatDTO;
import ru.kholmogorova.kotiki2.DTO.OwnerDTO;

import java.sql.Date;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Entity
@Table(name = "cats")
public class CatEntity {
    public CatEntity(CatDTO catDTO) {
        id = catDTO.getId();
        name = catDTO.getName();
        birthDate = catDTO.getBirthDate();
        breed = catDTO.getBreed();
        color = catDTO.getColor();
    }

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @Column(name = "birth_date")
    private LocalDate birthDate;
    private String breed;
    private Color color;
}
