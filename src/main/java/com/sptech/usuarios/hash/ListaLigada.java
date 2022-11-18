package com.sptech.usuarios.hash;

import com.sptech.usuarios.models.Usuario;

public class ListaLigada {
    private Node head;

    public ListaLigada() {
        head = new Node(0);
    }
    public void insereNode(int id) {
        Node novo = new Node(id);
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
