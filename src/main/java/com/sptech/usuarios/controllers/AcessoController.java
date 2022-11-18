package com.sptech.usuarios.controllers;


import com.sptech.usuarios.repositorys.ViewUsuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/acessos")
public class AcessoController {
    @Autowired
    private ViewUsuarios viewUsuarios;

//
    @GetMapping
    public ResponseEntity getAcessos() {
        if (viewUsuarios.findAll().isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(viewUsuarios.findAll());
    }

    @GetMapping("/inativos")
    public ResponseEntity getInativos() {
        if (viewUsuarios.findAll().isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(viewUsuarios.findAll());
    }

}