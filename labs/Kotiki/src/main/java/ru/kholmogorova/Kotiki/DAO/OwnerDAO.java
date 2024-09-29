package ru.kholmogorova.Kotiki.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import ru.kholmogorova.Kotiki.Entities.CatEntity;
import ru.kholmogorova.Kotiki.Entities.OwnerEntity;

import java.sql.Date;
import java.util.List;
import java.util.function.Consumer;

public class OwnerDAO {
    private final EntityManagerFactory emf = EntityManagerFactoryConfig.getEntityManagerFactory();

    public int addOwner(String name, Date birthDate) {
        inTransaction(em -> {
            var owner = new OwnerEntity();
            owner.setName(name);
            owner.setBirthDate(birthDate);
            em.persist(owner);
        });
        return emf.createEntityManager()
                .createQuery("select o.id from OwnerEntity o order by o.id desc limit 1", int.class)
                .getSingleResult();
    }

    public List<CatEntity> getOwnersCats(OwnerEntity owner) {
        return emf.createEntityManager()
                .createQuery("FROM CatEntity c where c.owner = :owner", CatEntity.class)
                .setParameter("owner", owner)
                .getResultList();
    }

    public List<OwnerEntity> getAll() {
        return emf.createEntityManager()
                .createQuery("FROM OwnerEntity", OwnerEntity.class)
                .getResultList();
    }

    public OwnerEntity getOwnerById(int ownerId) {
        return emf.createEntityManager()
                .createQuery("from OwnerEntity where OwnerEntity.id = :ownerId", OwnerEntity.class)
                .setParameter("ownerId", ownerId)
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
