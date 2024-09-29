package ru.kholmogorova.Kotiki.Controller;

import ru.kholmogorova.Kotiki.Entities.CatEntity;
import ru.kholmogorova.Kotiki.Entities.Color;
import ru.kholmogorova.Kotiki.Entities.OwnerEntity;
import ru.kholmogorova.Kotiki.Services.CatService;
import ru.kholmogorova.Kotiki.Services.OwnerService;

import java.util.ArrayList;
import java.util.List;

public class Application {
    private final OwnerService ownerService = new OwnerService();
    private final CatService catService = new CatService();
    public Cat CreateCat(String name, String birthDate, String breed, Color color) {
        return new Cat(name, birthDate, breed, color);
    }
    public Owner CreateOwner(String name, String birthDate) {
        Owner owner = new Owner(name, birthDate);
        int id = ownerService.AddOwner(name, birthDate);
        owner.setId(id);
        return owner;
    }

    public List<Cat> GetAllCats() {
        List<Cat> cats = new ArrayList<>();
        for (CatEntity cat : catService.GetAll()) {
            cats.add(new Cat(cat.getId(),
                    cat.getName(),
                    cat.getBreed(),
                    cat.getBirthDate().toString(),
                    cat.getColor(),
                    new Owner(cat.getOwner().getId(), cat.getOwner().getName(), cat.getOwner().getBirthDate().toString())));
        }
        return cats;
    }

    public List<Owner> GetAllOwners() {
        List<Owner> owners = new ArrayList<>();
        for (OwnerEntity owner : ownerService.GetAll()) {
            owners.add(new Owner(owner.getId(),
                    owner.getName(), owner.getBirthDate().toString()));
        }
        return owners;
    }
}
