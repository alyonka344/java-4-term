package ru.kholmogorova.kotiki2;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.kholmogorova.kotiki2.Entity.OwnerEntity;
import ru.kholmogorova.kotiki2.Entity.Role;
import ru.kholmogorova.kotiki2.Repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OwnerService {
    OwnerRepository ownerRepository;

    @Autowired
    public OwnerService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Transactional
    public void save(OwnerEntity ownerEntity) {
        ownerRepository.save(ownerEntity);
    }
    @Transactional
    public List<OwnerEntity> getAll() {
        return ownerRepository.findAll();
    }
    @Transactional
    public void delete(OwnerEntity ownerEntity) {
        ownerRepository.delete(ownerEntity);
    }
    @Transactional
    public OwnerEntity getById(Long id) {
        return ownerRepository.findById(id).orElse(null);
    }

    @Transactional


    public void create(OwnerEntity user) {
        if (ownerRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Пользователь с таким именем уже существует");
        }

        ownerRepository.save(user);
    }

    public OwnerEntity getByUsername(String username) {
        return ownerRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
    }

    public UserDetailsService userDetailsService() {
        return this::getByUsername;
    }

    public OwnerEntity getCurrentUser() {
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByUsername(username);
    }

}
