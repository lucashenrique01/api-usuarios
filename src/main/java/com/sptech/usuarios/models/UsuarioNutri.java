package com.sptech.usuarios.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class UsuarioNutri extends Usuario{
    @Column(name="especialidade")
    @NotBlank
    private String especialidade;
    @Column(name="crn")
    @NotBlank
    @Size(min = 7, max =7, message = "inseira um crn válido")
    private String crn;
    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }
    public void setCrn(String crn) {
        this.crn = crn;
    }
    public String getEspecialidade() {
        return especialidade;
    }
    public String getCrn() {
        return crn;
    }
}
