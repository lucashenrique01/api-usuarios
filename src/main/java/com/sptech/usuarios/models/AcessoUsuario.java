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
    private String cadastro;

    @Column(name="c")
    private Integer c;

    @Column(name="ultimo_login")
    private String ultimoLogin;

    @Column(name="l")
    private Integer l;
}



//@Subselect("select uuid() as id, hs.* from view_banda_cadastrada_show_confirmado hs")
//@Table(name = "view_banda_cadastrada_show_confirmado")
//public class ViewBandaCadastradaShowConfirmado implements Serializable {
