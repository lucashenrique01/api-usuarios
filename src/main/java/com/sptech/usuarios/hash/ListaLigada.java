package com.sptech.usuarios.hash;

import com.sptech.usuarios.models.Usuario;

public class ListaLigada {
    private Node head;

    public ListaLigada() {
        head = new Node(null);
    }
    public void insereNode(Usuario user) {
        Node novo = new Node(user);
        novo.setNext(head.getNext());
        head.setNext(novo);
    }

    public void exibe()
    {
        Node atual = head.getNext();
        while (atual != null) {
            System.out.print(atual.getInfo() + "\t");
            atual = atual.getNext();
        }
        System.out.println();
    }

    public int getTamanho() {
        Node atual = head.getNext();
        int tam = 0;
        while (atual != null) {
            tam++;
            atual = atual.getNext();
        }
        return tam;
    }

}
