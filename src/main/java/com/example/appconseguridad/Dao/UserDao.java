package com.example.appconseguridad.Dao;

import com.example.appconseguridad.models.UserEntity;

public interface UserDao {
    void createUser(UserEntity user);

    void deleteUser(Long id);

    void editUser(UserEntity user);

    UserEntity getUserByUsername(String username);
}
