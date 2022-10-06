package com.sptech.usuarios.dto;

import javax.validation.constraints.NotNull;

public class UsuarioPaciAtualizaFicha {
    @NotNull
    private String dataNascimento;
    @NotNull
    private String emailUsuario;
    @NotNull
    private String telefoneUsuario;

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public String getTelefoneUsuario() {
        return telefoneUsuario;
    }

    public void setTelefoneUsuario(String telefoneUsuario) {
        this.telefoneUsuario = telefoneUsuario;
    }
}
