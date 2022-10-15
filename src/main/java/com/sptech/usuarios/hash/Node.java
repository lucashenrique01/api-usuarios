package com.sptech.usuarios.hash;

import com.sptech.usuarios.models.Usuario;

public class Node {
    // Atributos
    private Usuario info;
    private Node next;

    // Construtor
    public Node (Usuario info) {
        this.info = info;
        next = null;
    }

    // Métodos

    public Usuario getInfo() {
        return info;
    }

    public void setInfo(Usuario info) {
        this.info = info;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
