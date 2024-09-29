package ru.kholmogorova.kotiki2.Repository;

import ru.kholmogorova.kotiki2.Entity.OwnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OwnerRepository extends JpaRepository<OwnerEntity, Long> {
    Optional<OwnerEntity> findByUsername(String username);

    boolean existsByUsername(String username);
}
