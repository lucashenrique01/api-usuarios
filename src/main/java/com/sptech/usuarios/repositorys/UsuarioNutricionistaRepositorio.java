package com.sptech.usuarios.repositorys;

import com.sptech.usuarios.models.UsuarioNutri;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UsuarioNutricionistaRepositorio extends CrudRepository<UsuarioNutri, Integer> {
    List<UsuarioNutri> findAll();

    UsuarioNutri findById(int id);

    UsuarioNutri findByEmailUsuario(String emailUsuario);

    <UserMod extends UsuarioNutri>UserMod save(UserMod usuarioNutricionista);
}
