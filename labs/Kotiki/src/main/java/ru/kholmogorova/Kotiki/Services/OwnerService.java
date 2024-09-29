package ru.kholmogorova.Kotiki.Services;

import ru.kholmogorova.Kotiki.Controller.Owner;
import ru.kholmogorova.Kotiki.DAO.CatDAO;
import ru.kholmogorova.Kotiki.DAO.OwnerDAO;
import ru.kholmogorova.Kotiki.Entities.CatEntity;
import ru.kholmogorova.Kotiki.Entities.Color;
import ru.kholmogorova.Kotiki.Entities.OwnerEntity;

import java.sql.Date;
import java.util.List;

public class OwnerService {
    private final OwnerDAO ownerDAO = new OwnerDAO();
    private final CatDAO catDAO = new CatDAO();

    public void GetAllCats(int ownerId) {
        List<CatEntity> catsEntities = ownerDAO.getOwnersCats(ownerDAO.getOwnerById(ownerId));
    }

    public int AddCat(int ownerId, String catName, String catBirthDate, String catBreed, Color catColor) {
        return catDAO.adoptCat(catName, Date.valueOf(catBirthDate), catBreed, catColor, ownerDAO.getOwnerById(ownerId));
    }

    public int AddOwner(String ownerName, String ownerBirthDate) {
        return ownerDAO.addOwner(ownerName, Date.valueOf(ownerBirthDate));
    }

    public List<OwnerEntity> GetAll() {
        return ownerDAO.getAll();
    }
}
