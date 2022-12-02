package com.sptech.usuarios.repositorys;

import com.sptech.usuarios.models.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepositorio extends CrudRepository<Usuario, Integer> {

    Usuario findById(int id);
}
