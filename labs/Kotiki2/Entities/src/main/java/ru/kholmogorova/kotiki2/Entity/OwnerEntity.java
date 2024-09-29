package ru.kholmogorova.kotiki2.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.kholmogorova.kotiki2.DTO.OwnerDTO;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Data
@Entity
@Table(name = "owners")
public class OwnerEntity implements UserDetails {
    public OwnerEntity(OwnerDTO ownerDTO) {
        id = ownerDTO.getId();
        name = ownerDTO.getName();
        birthDate = ownerDTO.getBirthDate();
        ownerDTO.getCats().forEach((c) -> cats.add(new CatEntity(c)));
    }

    @GeneratedValue
    @Id
    private Long id;
    private String name;
    @Column(name = "birth_date")
    private Date birthDate;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CatEntity> cats = new ArrayList<>();

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
