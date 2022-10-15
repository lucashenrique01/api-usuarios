package com.sptech.usuarios.hash;

import com.sptech.usuarios.models.UsuarioNutri;
import com.sptech.usuarios.models.UsuarioPaciente;

public class App {
    public static void main(String[] args) {
        HashTable hashTable = new HashTable();
        UsuarioPaciente usuarioPaciente = new UsuarioPaciente();
        UsuarioPaciente usuarioPaciente2 = new UsuarioPaciente();
        UsuarioNutri usuarioNutri = new UsuarioNutri();
        String estado = "SP";
        String estado2 = "PR";
        String estado3 = "ma";
        String estado4 = "mt";
        String estado5 = "rr";

        hashTable.insere(estado, usuarioPaciente);
        hashTable.insere(estado2, usuarioPaciente2);
        hashTable.insere(estado3, usuarioNutri);
        hashTable.insere(estado, usuarioNutri);
        hashTable.insere(estado4, usuarioNutri);
        hashTable.insere(estado5, usuarioNutri);
        hashTable.insere(estado, usuarioNutri);
        //hashTable.exibe();
        hashTable.exibirPorRegiao();
        System.out.println(hashTable.countPorRegiao());

    }
}
