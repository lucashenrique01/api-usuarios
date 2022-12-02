package com.sptech.usuarios.repositorys;


import com.sptech.usuarios.models.Dieta;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DietaRepositorio extends CrudRepository<Dieta, Integer> {

    List<Dieta> findAll();

}
