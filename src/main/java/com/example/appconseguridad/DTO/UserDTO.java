package com.example.appconseguridad.DTO;

import com.example.appconseguridad.models.RoleEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String name;
    private String email;
    private String username;
    private String password;
    private RoleEntity roles;
}
