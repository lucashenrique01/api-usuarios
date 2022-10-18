package com.sptech.usuarios.services;

import com.sptech.usuarios.hash.HashTable;
import com.sptech.usuarios.models.Endereco;
import com.sptech.usuarios.models.UsuarioNutri;
import com.sptech.usuarios.models.UsuarioPaciente;
import com.sptech.usuarios.repositorys.EnderecoRepositorio;
import com.sptech.usuarios.repositorys.UsuarioNutricionistaRepositorio;
import com.sptech.usuarios.repositorys.UsuarioPacienteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Component
public class EnderecoService {

    @Autowired
    private EnderecoRepositorio enderecoRepositorio;

    @Autowired
    private UsuarioNutricionistaRepositorio nutriRep;
    @Autowired
    private UsuarioPacienteRepositorio paciRep;


    public void postNutri(Integer idUsuario, Endereco endereco){
        Optional<UsuarioNutri> user = nutriRep.findById(idUsuario);
        if(user.isPresent()){
            enderecoRepositorio.save(endereco);
            user.get().setEndereco(endereco);
            nutriRep.save(user.get());
        }
    }

    public void postPaci(Integer idUsuario, Endereco endereco){
        Optional<UsuarioPaciente> user = paciRep.findById(idUsuario);
        if(user.isPresent()){
            enderecoRepositorio.save(endereco);
            user.get().setEndereco(endereco);
            paciRep.save(user.get());
        }
    }
    public Map<String, Integer> countPorRegiao(){
        HashTable hashTable = new HashTable();
        List<UsuarioPaciente> user = paciRep.findAll();
        for(int i = 0; i < user.size(); i++){
            if(!Objects.isNull(user.get(i).getEndereco())){
                hashTable.insere(user.get(i).getEndereco().getEstado(), user.get(i).getEndereco().getIdEndereco());
            }

        }
        List<UsuarioNutri> userNutri = nutriRep.findAll();
        for(int i = 0; i < userNutri.size(); i++){
            if(!Objects.isNull(user.get(i).getEndereco())) {
                hashTable.insere(userNutri.get(i).getEndereco().getEstado(), userNutri.get(i).getEndereco().getIdEndereco());
            }
        }
        return hashTable.countPorRegiao();
    }
}
