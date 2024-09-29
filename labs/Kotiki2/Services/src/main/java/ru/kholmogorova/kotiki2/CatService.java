package ru.kholmogorova.kotiki2;

import jakarta.transaction.Transactional;
import ru.kholmogorova.kotiki2.Entity.CatEntity;
import ru.kholmogorova.kotiki2.Repository.CatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CatService {
    CatRepository catRepository;

    @Autowired
    public CatService(CatRepository catRepository) {
        this.catRepository = catRepository;
    }

    @Transactional
    public void save(CatEntity catEntity) {
        catRepository.save(catEntity);
    }
    @Transactional
    public List<CatEntity> getAll() {
        return catRepository.findAll();
    }
    @Transactional
    public void delete(CatEntity catEntity) {
        catRepository.delete(catEntity);
    }
    @Transactional
    public CatEntity getById(Long Id) {
        System.out.println("hello");
        if (catRepository.findById(Id).isPresent()) {
            return catRepository.findById(Id).get();
        }
        return null;
    }
}
