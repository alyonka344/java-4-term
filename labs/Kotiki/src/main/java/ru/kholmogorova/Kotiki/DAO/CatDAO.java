package ru.kholmogorova.Kotiki.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import ru.kholmogorova.Kotiki.Entities.CatEntity;
import ru.kholmogorova.Kotiki.Entities.Color;
import ru.kholmogorova.Kotiki.Entities.OwnerEntity;

import java.sql.Date;
import java.util.List;
import java.util.function.Consumer;

public class CatDAO {
    private final EntityManagerFactory emf = EntityManagerFactoryConfig.getEntityManagerFactory();

    public int adoptCat(String name, Date birthDate, String breed, Color color, OwnerEntity owner) {
        inTransaction(em -> {
            var cat = new CatEntity();
            cat.setName(name);
            cat.setBirthDate(birthDate);
            cat.setBreed(breed);
            cat.setColor(color);
            cat.setOwner(owner);
            em.persist(cat);
        });
        return emf.createEntityManager()
                .createQuery("select c.id from CatEntity c order by c.id desc limit 1", int.class)
                .getSingleResult();
    }

    public List<CatEntity> getAll() {
        return emf.createEntityManager()
                .createQuery("FROM CatEntity", CatEntity.class)
                .getResultList();
    }

    public CatEntity getCatById(int catId) {
        return emf.createEntityManager()
                .createQuery("from CatEntity where id = :catId", CatEntity.class)
                .setParameter("catId", catId)
                .getSingleResult();
    }

    private void inTransaction(Consumer<EntityManager> work) {
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            work.accept(entityManager);
            transaction.commit();
        }
        catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
        finally {
            entityManager.close();
        }
    }
}
