package com.sptech.usuarios.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Immutable

@Subselect("select uuid() as id, hs.* from tempoAcesso hs")
@Table(name = "tempoAcesso")
public class AcessoUsuario {
    @Id
    private String id;


    @Column(name="cadastro")
    private Date cadastro;

    @Column(name="dias_cadastrado")
    private Integer c;

    @Column(name="ultimo_login")
    private Date ultimoLogin;

    @Column(name="dias_sem_login")
    private Integer l;

    public String getId() {
        return id;
    }

    public Date getCadastro() {
        return cadastro;
    }

    public Integer getC() {
        return c;
    }

    public Date getUltimoLogin() {
        return ultimoLogin;
    }

    public Integer getL() {
        return l;
    }
}

//@Subselect("select uuid() as id, hs.* from view_banda_cadastrada_show_confirmado hs")
//@Table(name = "view_banda_cadastrada_show_confirmado")
//public class ViewBandaCadastradaShowConfirmado implements Serializable {
