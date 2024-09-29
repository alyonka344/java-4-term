package ru.kholmogorova.Kotiki.Controller;

import lombok.Data;
import ru.kholmogorova.Kotiki.Services.OwnerService;

import java.util.List;

@Data
public class Owner {
    private int id;
    private String name;
    private String birthDate;
    private OwnerService ownerService;
    private List<Cat> cats;

    public Owner(String name, String birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }

    public Owner(int id, String name, String birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }

    public void AdoptCat(Cat cat) {
        int id = ownerService.AddCat(this.getId(), cat.getName(), cat.getBirthDate(), cat.getBreed(), cat.getColor());
        cat.setId(id);
        cat.setOwner(this);
        cats.add(cat);
    }
    public void FeedMyCats() {
        for (Cat cat : cats) {
            cat.Eat();
        }
    }
}
