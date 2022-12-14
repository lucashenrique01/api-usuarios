package com.sptech.usuarios.controllers;

import com.sptech.usuarios.dto.HorariosDto;
import com.sptech.usuarios.dto.UsuarioPaciAtualizaFicha;
import com.sptech.usuarios.models.UsuarioNutri;
import com.sptech.usuarios.repositorys.UsuarioNutricionistaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/nutricionistas")
public class UsuarioNutriController implements AcoesUsuarios {

    @Autowired
    private UsuarioNutricionistaRepositorio acoesCrud;

    @GetMapping
    public ResponseEntity getUsuarios(){
        if(acoesCrud.findAll().isEmpty()){
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(acoesCrud.findAll());
    }

    @GetMapping("/contagem")
    public ResponseEntity contagemUsuariosNutri(){
        if(acoesCrud.findAll().isEmpty()){
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(acoesCrud.findAll().size());
    }



    //metodos nutricionista
    @PostMapping
    public ResponseEntity   cadastrarNutricionista(@RequestBody @Valid UsuarioNutri novoUsuario){
        if(Objects.isNull(novoUsuario)){
            return ResponseEntity.status(400).build();
        }
        novoUsuario.setAutenticado(false);
        acoesCrud.save(novoUsuario);
        return ResponseEntity.status(201).build();
    }

    @PostMapping("/autenticar/{usuario}/{senha}")
    public ResponseEntity autenticarUsuario(@PathVariable String usuario, @PathVariable String senha){
        UsuarioNutri usuarioAtual = acoesCrud.findByEmailUsuario(usuario);
        if(Objects.isNull(usuarioAtual)){
            return ResponseEntity.status(403).build();
        }else {
            if(usuarioAtual.autenticar(usuario, senha)){
                usuarioAtual.setAutenticado(true);
                acoesCrud.save(usuarioAtual);
                return ResponseEntity.status(200).body(usuarioAtual);
            }
            return ResponseEntity.status(403).build();
        }
    }


    @DeleteMapping("{usuario}")
    public ResponseEntity logoffUsuario(@PathVariable String usuario){
        UsuarioNutri usuarioAtual = acoesCrud.findByEmailUsuario(usuario);
        if(!Objects.isNull(usuarioAtual)){
            if(usuarioAtual.getEmailUsuario().equals(usuario) && usuarioAtual.getAutenticado()){
                usuarioAtual.setAutenticado(false);
                acoesCrud.save(usuarioAtual);
                return ResponseEntity.status(200).build();
            }
            else if (usuarioAtual.getEmailUsuario().equals(usuario) && !usuarioAtual.getAutenticado()){
                ResponseEntity.status(403).build(); //Usu??rio N??O est?? autenticado
            }
        }
        return ResponseEntity.status(403).build(); //Usuario n??o encontrado
    }


    @GetMapping("/{id}")
    public ResponseEntity getUsuarios(@PathVariable int id){
        UsuarioNutri usuarioEncontrado = acoesCrud.findById(id);
        if(Objects.isNull(usuarioEncontrado)){
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.status(200).body(usuarioEncontrado);
    }

    //Alterar a senha
    @PatchMapping
    public ResponseEntity alterarSenha(@PathVariable int id,
                                       @RequestBody UsuarioNutri usuarioNutri){
        UsuarioNutri usuarioEncontrado = acoesCrud.findById(id);
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
        UsuarioNutri usuarioNutri = acoesCrud.findById(idPaciente);
        if(Objects.isNull(usuarioNutri)){
            return ResponseEntity.status(404).build();
        }
        usuarioNutri.setAvaliacao(nota);
        usuarioNutri.contadorAvalicacoes();
        acoesCrud.save(usuarioNutri);
        return ResponseEntity.status(200).build();
    }

    @PostMapping("/atualiza-ficha/{idPaciente}")
    public ResponseEntity patchDados(@PathVariable int idPaciente,
                                     @RequestBody @Valid UsuarioPaciAtualizaFicha usuarioPaciAtualizaFicha){
        UsuarioNutri usuarioNutri = acoesCrud.findById(idPaciente);
        if(Objects.isNull(usuarioNutri)){
            return ResponseEntity.status(404).build();
        }
        usuarioNutri.setEmailUsuario(usuarioPaciAtualizaFicha.getEmailUsuario());
        usuarioNutri.setTelefoneUsuario(usuarioPaciAtualizaFicha.getTelefoneUsuario());
        usuarioNutri.setDataNascimento(usuarioPaciAtualizaFicha.getDataNascimento());
        usuarioNutri.setNomeUsuario(usuarioPaciAtualizaFicha.getNomeUsuario());
        acoesCrud.save(usuarioNutri);
        return ResponseEntity.status(200).body(usuarioNutri);
    }

    @PatchMapping("/foto/{codigo}/{novaFoto}")
    public ResponseEntity patchFoto(@PathVariable int codigo,
                                    @PathVariable String novaFoto) {
        if (!acoesCrud.existsById(codigo)) {
            return ResponseEntity.status(404).build();
        }
        UsuarioNutri user = acoesCrud.findById(codigo);
        user.setFoto(codigo+novaFoto);
        acoesCrud.save(user);
        return ResponseEntity.status(200).build();
    }
    @GetMapping("/horas/{idNutri}")
    public ResponseEntity getHorasConsulta(@PathVariable int idNutri){
        UsuarioNutri usuarioNutri = acoesCrud.findById(idNutri);
        if(Objects.isNull(usuarioNutri)){
            return ResponseEntity.status(404).build();
        }

        Map<String, LocalTime> horarios = new HashMap<>();
        horarios.put("inicio", usuarioNutri.getHoraInicio());
        horarios.put("fim", usuarioNutri.getHoraFim());
        horarios.put("duracao", usuarioNutri.getDuracaoConsulta());
        return ResponseEntity.status(200).body(horarios);
    }
    @PatchMapping("/horas/{idNutri}")
    public ResponseEntity patchHorarios(@PathVariable int idNutri,
                                        @RequestBody HorariosDto horarios){
        UsuarioNutri usuarioNutri = acoesCrud.findById(idNutri);
        if(Objects.isNull(usuarioNutri)){
            return ResponseEntity.status(404).build();
        }
        LocalTime horaInicio = LocalTime.of(horarios.getHoraInicio(), horarios.getMinInicio());
        LocalTime horaFim = LocalTime.of(horarios.getHoraFim(), horarios.getMinFim());
        LocalTime duracao = LocalTime.of(horarios.getHoraDuracaoConsulta(), horarios.getMinDuracaoConsulta());
        usuarioNutri.setHoraInicio(horaInicio);
        usuarioNutri.setHoraFim(horaFim);
        usuarioNutri.setDuracaoConsulta(duracao);
        acoesCrud.save(usuarioNutri);
        return ResponseEntity.status(200).build();
    }




}
