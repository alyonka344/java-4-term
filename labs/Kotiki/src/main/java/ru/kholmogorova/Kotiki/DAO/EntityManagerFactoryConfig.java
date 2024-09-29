package ru.kholmogorova.Kotiki.DAO;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerFactoryConfig {
    private static EntityManagerFactory entityManagerFactory;
    private EntityManagerFactoryConfig() {}

    public static EntityManagerFactory getEntityManagerFactory() {
        if (entityManagerFactory == null)
            entityManagerFactory = Persistence.createEntityManagerFactory("my-database");
        return entityManagerFactory;
    }
}
