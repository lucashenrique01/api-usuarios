package com.sptech.usuarios.controllers;

import com.sptech.usuarios.hash.HashTable;
import com.sptech.usuarios.models.Endereco;
import com.sptech.usuarios.repositorys.EnderecoRepositorio;
import com.sptech.usuarios.services.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/enderecos")
public class EnderecoController {
    @Autowired
    private EnderecoService enderecoService;
    HashTable hashTable = new HashTable();


    @PostMapping("/pacientes/{idUsuario}")
    public ResponseEntity postEnderecoPaciente(@PathVariable Integer idUsuario,
                                               @RequestBody @Valid Endereco endereco){
        enderecoService.postPaci(idUsuario,endereco);
        return ResponseEntity.status(201).build();
    }

    @PostMapping("/nutricionistas/{idUsuario}")
    public ResponseEntity postEnderecoNutri(@PathVariable Integer idUsuario,
                                       @RequestBody @Valid Endereco endereco){
        enderecoService.postNutri(idUsuario, endereco);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/count")
    public ResponseEntity countUserPorRegiao(){
        return ResponseEntity.status(200).body(enderecoService.countPorRegiao());
    }
}
