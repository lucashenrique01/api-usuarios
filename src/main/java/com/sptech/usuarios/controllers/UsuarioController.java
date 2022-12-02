package com.sptech.usuarios.controllers;

import com.sptech.usuarios.models.Usuario;
import com.sptech.usuarios.models.UsuarioPaciente;
import com.sptech.usuarios.repositorys.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/geral")
public class UsuarioController {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @GetMapping("/foto/{id}")
    public ResponseEntity getFotoUsuario(@PathVariable int id){
        Usuario usuarioEncontrado = usuarioRepositorio.findById(id);
        if(Objects.isNull(usuarioEncontrado)){
            return ResponseEntity.status(404).build();
        }
        if(usuarioEncontrado.getFoto() == null) return ResponseEntity.status(204).build();
        return ResponseEntity.status(200).body(usuarioEncontrado.getFoto());
    }

}
