package com.sptech.usuarios.controllers;


import com.sptech.usuarios.dto.UsuarioPaciAtualizaFicha;
import com.sptech.usuarios.repositorys.UsuarioPacienteRepositorio;
import com.sptech.usuarios.models.UsuarioPaciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/pacientes")
public class UsuarioPacienteController {

    @Autowired
    private UsuarioPacienteRepositorio acoesCrud;

    @PostMapping
    public ResponseEntity cadastrarUsuario(@RequestBody @Valid UsuarioPaciente novoUsuario) {
        if(Objects.isNull(novoUsuario)){
            return ResponseEntity.status(400).build();
        }
        novoUsuario.setAutenticado(false);
        acoesCrud.save(novoUsuario);
        return ResponseEntity.status(201).build();
    }

    @PostMapping ("/autenticar/{usuario}/{senha}")
    public ResponseEntity autenticarUsuario(@PathVariable String usuario, @PathVariable String senha){

        UsuarioPaciente usuarioAtual = acoesCrud.findByEmailUsuario(usuario);
        if(Objects.isNull(usuarioAtual)){
            return ResponseEntity.status(403).build(); //não achou o usuario
        }else {
            if(usuarioAtual.autenticar(usuario, senha)){
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

    @GetMapping("/contagem")
    public ResponseEntity contagemUsuariosPacientes(){
        if(acoesCrud.findAll().isEmpty()){
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.status(200).body(acoesCrud.findAll().size());
        }
    }

    @DeleteMapping("/{usuario}")
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

    @PostMapping("/atualiza-ficha/{idPaciente}")
    public ResponseEntity patchDados(@PathVariable int idPaciente,
                                     @RequestBody @Valid UsuarioPaciAtualizaFicha usuarioPaciAtualizaFicha){
        UsuarioPaciente usuarioPaciente = acoesCrud.findById(idPaciente);
        if(Objects.isNull(usuarioPaciente)){
            return ResponseEntity.status(404).build();
        }
        usuarioPaciente.setEmailUsuario(usuarioPaciAtualizaFicha.getEmailUsuario());
        usuarioPaciente.setTelefoneUsuario(usuarioPaciAtualizaFicha.getTelefoneUsuario());
        usuarioPaciente.setDataNascimento(usuarioPaciAtualizaFicha.getDataNascimento());
        usuarioPaciente.setNomeUsuario(usuarioPaciAtualizaFicha.getNomeUsuario());
        acoesCrud.save(usuarioPaciente);
        return ResponseEntity.status(200).body(usuarioPaciente);
    }

    @PatchMapping("/foto/{codigo}/{novaFoto}")
    public ResponseEntity patchFoto(@PathVariable int codigo,
                                    @PathVariable String novaFoto) {
        if (!acoesCrud.existsById(codigo)) {
            return ResponseEntity.status(404).build();
        }
        UsuarioPaciente user = acoesCrud.findById(codigo);
        user.setFoto(codigo+novaFoto);
        acoesCrud.save(user);
        return ResponseEntity.status(200).build();
    }


}