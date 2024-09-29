package ru.kholmogorova.kotiki2.Repository;

import ru.kholmogorova.kotiki2.Entity.CatRelationshipsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatRelationshipsRepository extends JpaRepository<CatRelationshipsEntity, Long> {
}
