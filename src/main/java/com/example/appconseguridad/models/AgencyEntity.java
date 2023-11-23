package com.example.appconseguridad.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "agencia")
public class AgencyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "agencia_id")
    private Long agenciaId;

    @NotBlank
    private String name;

    @NotBlank
    @Column(name = "phone_number")
    private String phoneNumber;

    @NotBlank
    @Column(name = "direccion")
    private String address;

    @OneToMany(targetEntity = UserEntity.class, fetch = FetchType.EAGER, mappedBy = "agencia")
    @JsonIgnore
    private List<UserEntity> users;
}

