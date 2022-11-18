package com.sptech.usuarios.repositorys;

import com.sptech.usuarios.models.Endereco;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EnderecoRepositorio extends CrudRepository<Endereco, Integer> {

    List<Endereco> findAll();
}
