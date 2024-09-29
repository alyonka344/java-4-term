package ru.kholmogorova.Kotiki.Entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@ToString
@Data
@Entity
@Table(name = "cat_to_cat")
public class CatRelationshipsEntity {
    @Id
    @ManyToOne
    @JoinColumn(name = "id1")
    private CatEntity cat1;

    @Id
    @ManyToOne
    @JoinColumn(name = "id2")
    private CatEntity cat2;
}
