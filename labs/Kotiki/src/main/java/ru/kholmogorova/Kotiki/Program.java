package ru.kholmogorova.Kotiki;

import ru.kholmogorova.Kotiki.Controller.Cat;
import ru.kholmogorova.Kotiki.Controller.Owner;
import ru.kholmogorova.Kotiki.DAO.CatDAO;
import ru.kholmogorova.Kotiki.DAO.OwnerDAO;
import ru.kholmogorova.Kotiki.Entities.Color;
import ru.kholmogorova.Kotiki.Entities.OwnerEntity;

import java.sql.Date;
import java.util.List;

public class Program {
    public static void main(String[] args) throws Exception {

        var CatD = new CatDAO();
        var OwnerD = new OwnerDAO();
        OwnerD.addOwner("Alyona", Date.valueOf("2004-05-05"));
        List<OwnerEntity> e = OwnerD.getAll();
        CatD.adoptCat("Misha", Date.valueOf("2004-03-01"), "Kvak", Color.Black, e.get(0));
        var cats = (OwnerD.getOwnersCats(e.get(0)));
        for (var i : cats) {
            System.out.println(i.getName());
        }
//        Cat misha = new Cat();
//        Owner alyona = new Owner();
//        alyona.FeedMyCats();
    }
}
