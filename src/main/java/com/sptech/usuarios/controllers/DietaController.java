package com.sptech.usuarios.controllers;


import com.sptech.usuarios.models.Dieta;
import com.sptech.usuarios.models.UsuarioPaciente;
import com.sptech.usuarios.repositorys.DietaRepositorio;
import com.sptech.usuarios.repositorys.UsuarioNutricionistaRepositorio;
import com.sptech.usuarios.repositorys.UsuarioPacienteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/dietas")
public class DietaController {

    @Autowired
    private DietaRepositorio dietaRepositorio;
    @Autowired
    private UsuarioPacienteRepositorio acoesCrud;
    @Autowired
    private UsuarioNutricionistaRepositorio usuarioNutricionistaRepositorio;


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

    @PostMapping("/nova-dieta/{fkAutor}")
    public ResponseEntity postDietaUsuario(@PathVariable int fkAutor,
                                           @RequestBody Dieta dieta){
        if(!usuarioNutricionistaRepositorio.existsById(fkAutor)) return ResponseEntity.status(404).build();
        dietaRepositorio.save(dieta);
        return ResponseEntity.status(201).build();
    }

    @PostMapping("/paciente/{id}/dieta/{idDieta}")
    public ResponseEntity postDietaUsuario(@PathVariable int id,
                                           @PathVariable int idDieta){
        if((!acoesCrud.existsById(id)) || (!dietaRepositorio.existsById(idDieta)))
            return ResponseEntity.status(404).build();

        UsuarioPaciente user = acoesCrud.findById(id);
        Optional<Dieta> dietaSelecionada = dietaRepositorio.findById(idDieta);
        user.setDieta(dietaSelecionada.get());
        acoesCrud.save(user);
        return ResponseEntity.status(201).build();
    }


}
