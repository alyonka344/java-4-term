package ru.kholmogorova.Kotiki.Entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;
import java.util.List;

@NoArgsConstructor
@ToString
@Data
@Entity
@Table(name = "owners")
public class OwnerEntity {
    @GeneratedValue
    @Id
    private int id;
    private String name;
    @Column(name = "birth_date")
    private Date birthDate;
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CatEntity> cats;
}
