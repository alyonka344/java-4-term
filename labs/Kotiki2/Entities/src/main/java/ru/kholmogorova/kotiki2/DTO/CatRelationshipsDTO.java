package ru.kholmogorova.kotiki2.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.kholmogorova.kotiki2.DTO.CatDTO;
import ru.kholmogorova.kotiki2.Entity.CatRelationshipsEntity;

@NoArgsConstructor
@Data
public class CatRelationshipsDTO {
    public CatRelationshipsDTO(CatRelationshipsEntity catRelationshipsEntity) {
        id = catRelationshipsEntity.getId();
        cat1 = new CatDTO(catRelationshipsEntity.getCat1());
        cat2 = new CatDTO(catRelationshipsEntity.getCat2());
    }
    private Long id;
    private CatDTO cat1;
    private CatDTO cat2;
}
