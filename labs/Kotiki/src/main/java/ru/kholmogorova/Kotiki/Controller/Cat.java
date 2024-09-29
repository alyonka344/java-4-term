package ru.kholmogorova.Kotiki.Controller;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kholmogorova.Kotiki.Entities.Color;
import ru.kholmogorova.Kotiki.Services.CatService;

import java.util.List;

@NoArgsConstructor
@Data
public class Cat {
    private int id;
    private String name;
    private String birthDate;
    private String breed;
    private Color color;
    private Owner owner;

    public Cat(String name, String birthDate, String breed, Color color) {
        this.name = name;
        this.birthDate = birthDate;
        this.breed = breed;
        this.color = color;
    }

    public Cat(int id, String name, String birthDate, String breed, Color color, Owner owner) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.breed = breed;
        this.color = color;
        this.owner = owner;
    }

    private CatService catService;

    public void MakeFriends(Cat otherCat) {
        catService.AddRelationship(this.getId(), otherCat.getId());
    }

    public void GetFriendList() {
        List<String> friends = catService.GetFriendList(this.getId());
        System.out.println(friends);
    }

    public String Eat() {
        System.out.println(name + ": Om-nom-nom meow");
        return name + ": Om-nom-nom meow";
    }
}
