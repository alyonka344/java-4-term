package ru.kholmogorova.Kotiki.Services;

import ru.kholmogorova.Kotiki.DAO.CatDAO;
import ru.kholmogorova.Kotiki.DAO.CatRelationshipsDAO;
import ru.kholmogorova.Kotiki.Entities.CatEntity;

import java.util.ArrayList;
import java.util.List;

public class CatService {
    private CatDAO catDAO;
    private CatRelationshipsDAO catRelationshipsDAO;

    public void AddRelationship(int catId1, int catId2) {
        catRelationshipsDAO.addRelationship(catDAO.getCatById(catId1), catDAO.getCatById(catId2));
    }

    public List<String> GetFriendList(int catId) {
        List<String> cats = new ArrayList<>();
        for (CatEntity catFriend : catRelationshipsDAO.getCatFriends(catDAO.getCatById(catId))) {
            cats.add(catFriend.getName());
        }
        return cats;
    }

    public List<CatEntity> GetAll() {
        return catDAO.getAll();
    }
}
