package com.sptech.usuarios.hash;

import com.sptech.usuarios.models.Usuario;

public class Node {
    // Atributos
    private int info;
    private Node next;

    // Construtor
    public Node (int info) {
        this.info = info;
        next = null;
    }

    // Métodos

    public int getInfo() {
        return info;
    }

    public void setInfo(int info) {
        this.info = info;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
