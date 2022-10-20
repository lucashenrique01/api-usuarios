package com.sptech.usuarios.repositorys;

import com.sptech.usuarios.models.AcessoUsuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.transaction.Transactional;
import java.util.List;

@CrossOrigin(origins = "*")
@Repository
@Transactional
public interface ViewUsuarios extends PagingAndSortingRepository<AcessoUsuario,Long> {
    List<AcessoUsuario> findAll();
}

