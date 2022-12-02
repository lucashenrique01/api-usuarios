package com.sptech.usuarios.controllers;


import com.sptech.usuarios.models.Dieta;
import com.sptech.usuarios.models.UsuarioPaciente;
import com.sptech.usuarios.repositorys.DietaRepositorio;
import com.sptech.usuarios.repositorys.UsuarioPacienteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/dieta")
public class DietaController {

    @Autowired
    private DietaRepositorio dietaRepositorio;
    @Autowired
    private UsuarioPacienteRepositorio acoesCrud;


    @GetMapping
    public ResponseEntity getDieta(){
        List<Dieta> dietas = dietaRepositorio.findAll();
        if(dietas.isEmpty()) return ResponseEntity.status(204).build();
        return ResponseEntity.status(200).body(dietas);
    }

    @GetMapping("/{id}")
    public ResponseEntity getDietaUsuario(@PathVariable int id){
        if(!acoesCrud.existsById(id)) return ResponseEntity.status(404).build();

        return ResponseEntity.status(200).body(acoesCrud.findById(id).getDieta() == null ?
                "" : acoesCrud.findById(id).getDieta());
    }

    @PostMapping("/{id}")
    public ResponseEntity postDietaUsuario(@PathVariable int id,
                                           @RequestBody Dieta dieta){
        if(!acoesCrud.existsById(id)) return ResponseEntity.status(404).build();

        UsuarioPaciente user = acoesCrud.findById(id);
        user.setDieta(dieta);
        acoesCrud.save(user);
        return ResponseEntity.status(201).build();
    }


}
