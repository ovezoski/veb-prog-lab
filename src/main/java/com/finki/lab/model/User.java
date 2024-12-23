package com.finki.lab.model;

import com.finki.lab.model.Enumerations.Role;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Data
@Entity
@NoArgsConstructor
@Table(name = "music_users")
public class User implements UserDetails {

    @Id
    private String username;

    private String password;

    private String name;

    private String surname;

//    @OneToMany()

    private Boolean isAccountNonExpired;
    private Boolean isAccountNonLocked;
    private Boolean isCredentialsNonExpired;
    private Boolean isEnabled;


    @Enumerated(value = EnumType.STRING)
    private Role role;

    public User(String username, String password, String name, String surname, Role role){
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
    }

    @Override
    public Collection<?extends GrantedAuthority> getAuthorities(){
        return Collections.singletonList(role);
    }


    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }




}
