package ru.kholmogorova.kotiki2.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.kholmogorova.kotiki2.DTO.CatDTO;
import ru.kholmogorova.kotiki2.Entity.OwnerEntity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
public class OwnerDTO {
    public OwnerDTO(OwnerEntity ownerEntity) {
        id = ownerEntity.getId();
        name = ownerEntity.getName();
        birthDate = ownerEntity.getBirthDate();
        ownerEntity.getCats().forEach(c -> cats.add(new CatDTO(c)));
    }

    private Long id;
    private String name;
    private Date birthDate;
    private List<CatDTO> cats = new ArrayList<>();
}
