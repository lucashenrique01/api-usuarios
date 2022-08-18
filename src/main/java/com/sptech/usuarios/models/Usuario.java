package com.sptech.usuarios.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Objects;

@Entity
@Table(name = "tb_usuarios")
@DiscriminatorColumn(name="tipoConta")
public abstract class Usuario{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank
   @Size(min = 4, max = 30)
    //@JsonProperty("nomeUsuario")
    private String nomeUsuario;
   @Pattern(regexp = "(\\(?\\d{2}\\)?\\s)?(\\d{4,5}\\-\\d{4})"
           , message = "Informe um telefone válido com ou sem DDD")
    //@JsonProperty("telefoneUsuario")
    private String telefoneUsuario;
    @Email
    //@JsonProperty("emailUsuario")
    private String emailUsuario;
    @NotBlank
    @Size(min = 4, max = 15)
    //@JsonProperty("senhaUsuario")
    private String senhaUsuario;
    @NotNull
    @PositiveOrZero
    //@JsonProperty("avaliacao")
    private Double avaliacao;
    @NotNull
    //@JsonProperty("autenticado")
    private Boolean autenticado;
    @ManyToOne
    //@JsonProperty("endereco")
    private Endereco endereco;
    @Transient
    private int nAvaliacoes;
    private String foto;

    public String getSenhaUsuario() {
        return senhaUsuario;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public void contadorAvalicacoes(){
        this.nAvaliacoes++;
    }

    public int getnAvaliacoes() {
        return nAvaliacoes;
    }

    public void setnAvaliacoes(int nAvaliacoes) {
        this.nAvaliacoes = nAvaliacoes;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public boolean autenticar(String login, String senha) {
        autenticado = login.equals(this.emailUsuario) && senha.equals(this.senhaUsuario);
        return autenticado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getTelefoneUsuario() {
        return telefoneUsuario;
    }

    public void setTelefoneUsuario(String telefoneUsuario) {
        this.telefoneUsuario = telefoneUsuario;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }


    public String retornaSenha(){
        return this.senhaUsuario;
    }

    public void setSenhaUsuario(String senhaUsuario) {
        this.senhaUsuario = senhaUsuario;
    }

    public Boolean getAutenticado() {
        return autenticado;
    }

    public void setAutenticado(Boolean autenticado) {
        this.autenticado = autenticado;
    }

    public Double getAvaliacao() {
        return this.avaliacao;
    }

    public void setAvaliacao(Double avaliacao) {
        if(Objects.isNull(this.avaliacao)){
            this.avaliacao = avaliacao;
        }
        this.avaliacao += avaliacao;
    }

    public void calculaAvaliacao(){
       setAvaliacao((getAvaliacao()/this.nAvaliacoes));
    }
}
