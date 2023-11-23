package com.example.appconseguridad.controllers;

import com.example.appconseguridad.DTO.UserDTO;
import com.example.appconseguridad.Dao.UserDao;
import com.example.appconseguridad.models.RoleEntity;
import com.example.appconseguridad.models.UserEntity;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class PrincipalController {
    @Autowired
    UserDao userDao;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("hello")
    public String hello(){
        return "Hello world not secure";
    }

    @GetMapping("helloSecured")
    public String helloSecured(){
        return "Hello world secured";
    }

    @PostMapping("createuser")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDTO userDTO){
        RoleEntity roleEntity = userDTO.getRoles();

        UserEntity userEntity = UserEntity.builder()
                .name(userDTO.getName())
                .username(userDTO.getUsername())
                .email(userDTO.getEmail())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .roles((List<RoleEntity>) roleEntity)
                .build();

        userDao.createUser(userEntity);
        return ResponseEntity.ok(userEntity);
    }
    //
    @DeleteMapping("deleteuser")
    public String deleteUser(@RequestParam Long id){
        userDao.deleteUser(id);
        return "Se ha borrado el usuario con exito";
    }
}
