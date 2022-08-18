package com.sptech.usuarios.controllers;

import com.sptech.usuarios.models.UsuarioNutri;
import com.sptech.usuarios.repositorys.UsuarioNutricionistaRepositorio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {UsuarioNutriController.class})
class UsuarioNutriControllerTest {

    @Autowired
    UsuarioNutriController controller;

    @MockBean
    UsuarioNutricionistaRepositorio repository;

    @Test
    void getUsuarios() {
        UsuarioNutri nutri1 = mock(UsuarioNutri.class);
        UsuarioNutri nutri2 = mock(UsuarioNutri.class);
        UsuarioNutri nutri3 = mock(UsuarioNutri.class);

        when(repository.findAll()).thenReturn(new ArrayList<>());
        ResponseEntity<List<UsuarioNutri>> resposta = controller.getUsuarios();


        when(repository.save(nutri1)).thenReturn(new UsuarioNutri());

        //controller.cadastrarNutricionista(nutri1);
        //controller.cadastrarNutricionista(nutri2);
        //controller.cadastrarNutricionista(nutri3);

        //ResponseEntity<List<UsuarioNutri>> resposta2 = controller.getUsuarios();


        // verificando se o status da resposta é 204
        assertEquals(204, resposta.getStatusCodeValue());

        // verificando se a resposta não tem corpo (corpo == null)
        assertNull(resposta.getBody());

        //Verifica o status code correto 200 quando lista cheia
        //assertEquals(200, resposta2.getStatusCodeValue());
    }

    @Test
    void cadastrarNutricionista() {

        UsuarioNutri nutri1 = mock(UsuarioNutri.class);
        when(repository.save(nutri1)).thenReturn(new UsuarioNutri());

        UsuarioNutri nutri2 = null;

        assertEquals(400, controller.cadastrarNutricionista(nutri2).getStatusCodeValue());
        assertEquals(201, controller.cadastrarNutricionista(nutri1).getStatusCodeValue());
    }


}