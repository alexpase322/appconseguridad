package com.example.appconseguridad.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "Users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long userId;

    @NotBlank
    private String name;

    @Email
    @NotBlank
    @Size(max = 80)
    private String email;

    @NotBlank
    @Column(name = "user_name")
    private String username;

    @NotBlank
    private String password;

    @ManyToOne(targetEntity = AgencyEntity.class, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "agency")
    private AgencyEntity agencia;

    @ManyToMany(fetch = FetchType.EAGER, targetEntity = RoleEntity.class, cascade = CascadeType.PERSIST)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<RoleEntity> roles;
}
