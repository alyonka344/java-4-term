package ru.kholmogorova.kotiki2.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.kholmogorova.kotiki2.DTO.CatDTO;
import ru.kholmogorova.kotiki2.DTO.CatRelationshipsDTO;

@NoArgsConstructor
@ToString
@Data
@Entity
@Table(name = "cat_to_cat")
public class CatRelationshipsEntity {
    public CatRelationshipsEntity(CatRelationshipsDTO catRelationshipsDTO) {
        id = catRelationshipsDTO.getId();
        cat1 = new CatEntity(catRelationshipsDTO.getCat1());
        cat2 = new CatEntity(catRelationshipsDTO.getCat2());
    }

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id1")
    private CatEntity cat1;

    @ManyToOne
    @JoinColumn(name = "id2")
    private CatEntity cat2;
}
