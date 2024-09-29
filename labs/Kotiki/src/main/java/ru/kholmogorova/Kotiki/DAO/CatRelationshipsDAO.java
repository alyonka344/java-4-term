package ru.kholmogorova.Kotiki.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import ru.kholmogorova.Kotiki.Entities.CatEntity;
import ru.kholmogorova.Kotiki.Entities.CatRelationshipsEntity;

import java.util.List;
import java.util.function.Consumer;

public class CatRelationshipsDAO {
    private final EntityManagerFactory emf = EntityManagerFactoryConfig.getEntityManagerFactory();
    public void addRelationship(CatEntity cat1, CatEntity cat2) {
        inTransaction(em -> {
            var catRs1 = new CatRelationshipsEntity();
            catRs1.setCat1(cat1);
            catRs1.setCat2(cat2);
            var catRs2 = new CatRelationshipsEntity();
            catRs2.setCat1(cat2);
            catRs2.setCat2(cat1);
            em.persist(catRs1);
            em.persist(catRs2);
        });
    }

    public List<CatRelationshipsEntity> getAll() {
        return emf.createEntityManager()
                .createQuery("FROM CatRelationshipsEntity", CatRelationshipsEntity.class)
                .getResultList();
    }

    public List<CatEntity> getCatFriends(CatEntity cat) {
        return emf.createEntityManager()
                .createQuery("select c.cat1 from CatRelationshipsEntity c where c.cat1 = :cat", CatEntity.class)
                .setParameter("cat", cat)
                .getResultList();
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
