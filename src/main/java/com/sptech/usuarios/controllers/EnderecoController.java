package com.sptech.usuarios.controllers;

import com.sptech.usuarios.models.Endereco;
import com.sptech.usuarios.repositorys.EnderecoRepositorio;
import com.sptech.usuarios.services.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoRepositorio enderecoRepositorio;

    @Autowired
    private EnderecoService enderecoService;

    @PostMapping("/pacientes/{idUsuario}")
    public ResponseEntity postEnderecoPaciente(@PathVariable Integer idUsuario,
                                               @RequestBody Endereco endereco){
        enderecoService.postPaci(idUsuario,endereco);
        return ResponseEntity.status(201).build();
    }

    @PostMapping("/nutri/{idUsuario}/{cep}/{complemento}/{numero}")
    public ResponseEntity postEnderecoNutri(@PathVariable Integer idUsuario,
                                       @PathVariable String cep,
                                       @PathVariable String complemento,
                                       @PathVariable int numero){
        enderecoService.postNutri(idUsuario,cep, complemento, numero);
        return ResponseEntity.status(201).build();
    }
}
