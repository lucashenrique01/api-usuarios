package com.sptech.usuarios.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalTime;
import java.util.Date;

@Entity
public class UsuarioNutri extends Usuario{
    @Column(name="especialidade")
    @NotBlank
    private String especialidade;
    @Column(name="crn")
    @NotBlank
    @Size(min = 7, max =7, message = "inseira um crn válido")
    private String crn;

    private LocalTime horaInicio;

    private LocalTime horaFim;

    private LocalTime duracaoConsulta;

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(LocalTime horaFim) {
        this.horaFim = horaFim;
    }

    public LocalTime getDuracaoConsulta() {
        return duracaoConsulta;
    }

    public void setDuracaoConsulta(LocalTime duracaoConsulta) {
        this.duracaoConsulta = duracaoConsulta;
    }

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
