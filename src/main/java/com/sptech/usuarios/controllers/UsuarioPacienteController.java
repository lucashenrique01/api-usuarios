package com.sptech.usuarios.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sptech.usuarios.models.Usuario;
import com.sptech.usuarios.repositorys.UsuarioPacienteRepositorio;
import com.sptech.usuarios.models.UsuarioPaciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.annotation.Repeatable;
import java.util.Objects;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/pacientes")
public class UsuarioPacienteController {

    @Autowired
    private UsuarioPacienteRepositorio acoesCrud;

    @PostMapping
    public ResponseEntity cadastrarUsuario(@RequestBody String novoUsuario) throws JsonProcessingException {
        ObjectMapper obj = new ObjectMapper();
        UsuarioPaciente user = obj.readValue(novoUsuario, UsuarioPaciente.class);
        if(Objects.isNull(novoUsuario)){
           return ResponseEntity.status(400).build();
        }
        acoesCrud.save(user);
        return ResponseEntity.status(201).build();
    }

    @PostMapping ("/autenticar")
    public ResponseEntity autenticarUsuario(@RequestBody UsuarioPaciente usuarioPaciente){
        String email = usuarioPaciente.getEmailUsuario();
        String pass = usuarioPaciente.getSenhaUsuario();
        UsuarioPaciente usuarioAtual = acoesCrud.findByEmailUsuario(email);
        if(Objects.isNull(usuarioAtual)){
            return ResponseEntity.status(403).build(); //não achou o usuario
        }else {
            if(usuarioAtual.autenticar(email, pass)){
                usuarioAtual.setAutenticado(true);
                acoesCrud.save(usuarioAtual);
                return ResponseEntity.status(200).body(usuarioAtual);
            }
            return ResponseEntity.status(403).build(); //senha incorreta
        }
    }

    @GetMapping
    public ResponseEntity getUsuarios(){
        if(acoesCrud.findAll().isEmpty()){
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.status(200).body(acoesCrud.findAll());
        }
    }

    @DeleteMapping("{usuario}")
    public ResponseEntity logoffUsuario(@PathVariable String usuario){
        UsuarioPaciente usuarioAtual = acoesCrud.findByEmailUsuario(usuario);
        if(!Objects.isNull(usuarioAtual)){
            if(usuarioAtual.getEmailUsuario().equals(usuario) && usuarioAtual.getAutenticado()){
                usuarioAtual.setAutenticado(false);
                acoesCrud.save(usuarioAtual);
                return ResponseEntity.status(200).build();
            }
            else if (usuarioAtual.getEmailUsuario().equals(usuario) && !usuarioAtual.getAutenticado()){
                return ResponseEntity.status(403).build();
            }
        }
        return ResponseEntity.status(403).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity getUsuarios(@PathVariable int id){
        UsuarioPaciente usuarioEncontrado = acoesCrud.findById(id);
        if(Objects.isNull(usuarioEncontrado)){
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.status(200).body(usuarioEncontrado);
    }

    @PatchMapping
    public ResponseEntity alterarSenha(@PathVariable int id,
                                       @RequestBody UsuarioPaciente usuarioNutri){
        UsuarioPaciente usuarioEncontrado = acoesCrud.findById(id);
        if(Objects.isNull(usuarioEncontrado)){
            return ResponseEntity.status(404).build();
        }else {
            usuarioEncontrado.setSenhaUsuario(usuarioNutri.retornaSenha());
            acoesCrud.save(usuarioEncontrado);
        }
        return ResponseEntity.status(200).body(usuarioEncontrado);
    }

    @PatchMapping("/avalia/{idPaciente}/{nota}")
    public ResponseEntity avalia(@PathVariable int idPaciente,
                                 @PathVariable Double nota){
        UsuarioPaciente usuarioPaciente = acoesCrud.findById(idPaciente);
        if(Objects.isNull(usuarioPaciente)){
            return ResponseEntity.status(404).build();
        }
        usuarioPaciente.setAvaliacao(nota);
        usuarioPaciente.contadorAvalicacoes();
        acoesCrud.save(usuarioPaciente);
        return ResponseEntity.status(200).build();
    }
    @PatchMapping("/experiencia/{idPaciente}/")
    public ResponseEntity patchExperiencia(@PathVariable int idPaciente,
                                           @PathVariable Double valor){
        UsuarioPaciente usuarioPaciente = acoesCrud.findById(idPaciente);
        if(Objects.isNull(usuarioPaciente)){
            return ResponseEntity.status(404).build();
        }
        usuarioPaciente.setExperiencia(valor);
        usuarioPaciente.alocaCategoria();
        acoesCrud.save(usuarioPaciente);
        return ResponseEntity.status(200).build();
    }



}