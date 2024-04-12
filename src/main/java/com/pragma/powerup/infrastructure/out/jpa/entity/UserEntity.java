package com.pragma.powerup.infrastructure.out.jpa.entity;


import com.pragma.powerup.domain.model.Role;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "user", uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;

    private String name;
    private String lastname;
    private String dni;
    private String number;
    private LocalDate birthDate;
    private String email;
    private String password;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_role", referencedColumnName = "idRole")
    private RoleEntity idRole;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(idRole.getName()));
    }

    @Override
    public String getUsername() {
        return email;
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
