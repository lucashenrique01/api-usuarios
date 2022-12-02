package com.sptech.usuarios.models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;


@Entity
public class UsuarioPaciente extends Usuario{

    @ManyToOne
    private Dieta dieta;

    public Dieta getDieta() {
        return dieta;
    }

    public void setDieta(Dieta dieta) {
        this.dieta = dieta;
    }
}
