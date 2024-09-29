package ru.kholmogorova.kotiki2;

import jakarta.transaction.Transactional;
import ru.kholmogorova.kotiki2.Entity.CatRelationshipsEntity;
import ru.kholmogorova.kotiki2.Repository.CatRelationshipsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatRelationshipsService {
    CatRelationshipsRepository catRelationshipsRepository;

    @Autowired
    public CatRelationshipsService(CatRelationshipsRepository catRelationshipsRepository) {
        this.catRelationshipsRepository = catRelationshipsRepository;
    }

    @Transactional
    public void save(CatRelationshipsEntity catRelationshipsEntity) {
        catRelationshipsRepository.save(catRelationshipsEntity);
    }

    @Transactional
    public void delete(CatRelationshipsEntity catRelationshipsEntity) {
        catRelationshipsRepository.delete(catRelationshipsEntity);
    }

    @Transactional
    public List<CatRelationshipsEntity> getAll() {
        return catRelationshipsRepository.findAll();
    }
}
