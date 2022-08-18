package com.sptech.usuarios.repositorys;

import com.sptech.usuarios.models.UsuarioPaciente;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UsuarioPacienteRepositorio extends CrudRepository<UsuarioPaciente, Integer> {

    List<UsuarioPaciente>findAll();

    UsuarioPaciente findById(int id);

    UsuarioPaciente findByEmailUsuario(String emailUsuario);

    void delete(UsuarioPaciente u);

    <UserMod extends UsuarioPaciente>UserMod save(UserMod usuarioPaciente);

}
