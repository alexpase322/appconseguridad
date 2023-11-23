package com.example.appconseguridad.Dao;

import com.example.appconseguridad.models.UserEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Transactional
public class UserDaoImplement implements UserDao{

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void createUser(UserEntity user){
        entityManager.merge(user);
    }

    @Override
    public void deleteUser(Long id){
        String query = "FROM UserEntity WHERE id=:user_id";
        List<UserEntity> lista = entityManager.createQuery(query)
                        .setParameter("user_id", id)
                                .getResultList();
        entityManager.remove(lista.get(0));
    }

    @Override
    public void editUser(UserEntity user){
        String query = "FROM UserEntity WHERE userId=:user_id";
        List<UserEntity> lista = entityManager.createQuery(query)
                .setParameter("user_id", user.getUserId())
                .getResultList();
        lista.get(0).setUsername(user.getUsername());
        lista.get(0).setName(user.getName());
        lista.get(0).setEmail(user.getEmail());
        entityManager.merge(lista.get(0));
    }

    @Override
    public UserEntity getUserByUsername(String username){
        String query = "FROM UserEntity WHERE username=:user_name";
        List<UserEntity> lista = entityManager.createQuery(query).setParameter("user_name", username).getResultList();
        if(lista.isEmpty()){
            return null;
        }
        return lista.get(0);
    }
}
