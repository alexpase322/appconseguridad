package com.example.appconseguridad.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("prueba")
public class TestRolesController {

    @GetMapping("accesoadmin")
    @PreAuthorize("hasRole('ADMIN')")
    public String AccessAdmin(){
        return "accediste por admin";
    }

    @GetMapping("accesouser")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public String AccessUser(){
        return "accediste por user";
    }

    @GetMapping("accesoinvitado")
    @PreAuthorize("hasRole('INVITED')")
    public String AccessInvitado(){
        return "accediste por invitado";
    }
}
