package com.sptech.usuarios.controllers;

import com.sptech.usuarios.models.Usuario;
import com.sptech.usuarios.models.UsuarioPaciente;
import org.springframework.http.ResponseEntity;

public interface AcoesUsuarios {

    ResponseEntity autenticarUsuario(String usuario,String senha);
    ResponseEntity getUsuarios();
    ResponseEntity logoffUsuario(String usuario);
}
