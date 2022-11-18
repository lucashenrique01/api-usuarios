package com.sptech.usuarios.dto;

import java.time.LocalTime;

public class HorariosDto {
    private int horaInicio;
    private int minInicio;

    private int horaFim;
    private int minFim;

    private int horaDuracaoConsulta;
    private int minDuracaoConsulta;

    public int getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(int horaInicio) {
        this.horaInicio = horaInicio;
    }

    public int getMinInicio() {
        return minInicio;
    }

    public void setMinInicio(int minInicio) {
        this.minInicio = minInicio;
    }

    public int getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(int horaFim) {
        this.horaFim = horaFim;
    }

    public int getMinFim() {
        return minFim;
    }

    public void setMinFim(int minFim) {
        this.minFim = minFim;
    }

    public int getHoraDuracaoConsulta() {
        return horaDuracaoConsulta;
    }

    public void setHoraDuracaoConsulta(int horaDuracaoConsulta) {
        this.horaDuracaoConsulta = horaDuracaoConsulta;
    }

    public int getMinDuracaoConsulta() {
        return minDuracaoConsulta;
    }

    public void setMinDuracaoConsulta(int minDuracaoConsulta) {
        this.minDuracaoConsulta = minDuracaoConsulta;
    }
}
