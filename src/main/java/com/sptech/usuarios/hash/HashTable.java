package com.sptech.usuarios.hash;

import com.sptech.usuarios.models.Usuario;

import java.util.HashMap;
import java.util.Map;

public class HashTable {

    // Atributo
    private ListaLigada[] table;
    private String[] norte = {"AM", "RR", "AP", "PA", "TO", "RO", "AC"};
    private String[] nordeste = {"MA", "PI", "CE", "RN", "PE", "PB", "SE", "AL", "BA"};
    private String[] centro_oeste = {"MT", "MS", "GO"};
    private String[] sudeste = {"SP", "RJ", "ES", "MG"};
    private String[] sul = {"PR", "RS", "SC"};

    // Construtor
    public HashTable() {
        this.table = new ListaLigada[5];
        for (int i = 0; i < table.length; i++) {
            table[i] = new ListaLigada();
        }
    }

    // Métodos

    public Integer funcaoHash(String estado){
        for(String p : this.norte)
        {
            if(p.contains(estado.toUpperCase()))
                return 0;
        }
        for(String p : this.nordeste)
        {
            if(p.contains(estado.toUpperCase()))
                return 1;
        }
        for(String p : this.centro_oeste)
        {
            if(p.contains(estado.toUpperCase()))
                return 2;
        }
        for(String p : this.sudeste)
        {
            if(p.contains(estado.toUpperCase()))
                return 3;
        }
        for(String p : this.sul)
        {
            if(p.contains(estado.toUpperCase()))
                return 4;
        }
        return null;
    }

    public void insere(String estado, int id) {
        this.table[funcaoHash(estado)].insereNode(id);
    }

    public Map<String, Integer> countPorRegiao(){
        Map<String, Integer> regigoes = new HashMap<String, Integer>();
        regigoes.put("Norte", this.table[0].getTamanho());
        regigoes.put("Nordeste", this.table[1].getTamanho());
        regigoes.put("Centro_oeste", this.table[2].getTamanho());
        regigoes.put("Sudeste", this.table[3].getTamanho());
        regigoes.put("Sul", this.table[4].getTamanho());
        return regigoes;
    }
    public void exibirPorRegiao(){
        System.out.println("Norte: ");
        this.table[0].exibe();
        System.out.println("Nordeste: ");
        this.table[1].exibe();
        System.out.println("Centro-oeste: ");
        this.table[2].exibe();
        System.out.println("Sudeste: ");
        this.table[3].exibe();
        System.out.println("Sul: ");
        this.table[4].exibe();
    }
}
