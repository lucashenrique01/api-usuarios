package com.sptech.usuarios.repositorys;

import com.sptech.usuarios.models.AcessoUsuario;
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

//@CrossOrigin(origins = "*")
//@Repository
//@Transactional
//public interface RepositorioViewBandaCadastradaShowConfirmado extends PagingAndSortingRepository<ViewBandaCadastradaShowConfirmado, Integer> {
//
//    List<ViewBandaCadastradaShowConfirmado> findAll();
//
//}
