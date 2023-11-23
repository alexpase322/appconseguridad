package com.example.appconseguridad.utils;

import com.example.appconseguridad.Dao.UserDao;
import com.example.appconseguridad.models.RoleEntity;
import com.example.appconseguridad.models.UserEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;


@Service
public class UserDetailServiceImplement implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userDao.getUserByUsername(username);
        if(userEntity==null){
            return (UserDetails) new UsernameNotFoundException("El usuario "+ username+ " no existe");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (RoleEntity roleEntity : userEntity.getRoles()) {
            String role = roleEntity.getRole();
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
        }
        return new User(userEntity.getUsername(), userEntity.getPassword(), true, true, true, true,
                authorities);
    }
}
