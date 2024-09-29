package ru.kholmogorova.Kotiki.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Entity
@Table(name = "cats")
public class CatEntity {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    @Column(name = "birth_date")
    private Date birthDate;
    private String breed;
    private Color color;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private OwnerEntity owner;
}
