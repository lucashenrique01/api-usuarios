package com.sptech.usuarios.services;

import com.sptech.usuarios.models.Endereco;
import com.sptech.usuarios.models.Usuario;
import com.sptech.usuarios.models.UsuarioNutri;
import com.sptech.usuarios.models.UsuarioPaciente;
import com.sptech.usuarios.repositorys.EnderecoRepositorio;
import com.sptech.usuarios.repositorys.UsuarioNutricionistaRepositorio;
import com.sptech.usuarios.repositorys.UsuarioPacienteRepositorio;
import com.sptech.usuarios.rest.viacep.CepResposta;
import com.sptech.usuarios.rest.viacep.ClienteViaCep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class EnderecoService {

    @Autowired
    private EnderecoRepositorio enderecoRepositorio;

    @Autowired
    private UsuarioNutricionistaRepositorio nutriRep;
    @Autowired
    private UsuarioPacienteRepositorio paciRep;

    @Autowired
    private ClienteViaCep clienteViaCep;

    public void postNutri(Integer idUsuario, String cep, String complemento, int numero){
        Optional<UsuarioNutri> user = nutriRep.findById(idUsuario);
        if(user.isPresent()){
            CepResposta cepEncontrado = clienteViaCep.getCep(cep);
            Endereco novoEndereco = new Endereco();
            novoEndereco.setBairro(cepEncontrado.getBairro());
            novoEndereco.setCidade(cepEncontrado.getLocalidade());
            novoEndereco.setLogradouro(cepEncontrado.getLogradouro());
            novoEndereco.setEstado(cepEncontrado.getUf());
            novoEndereco.setComplemento(complemento);
            novoEndereco.setNumero(numero);
            enderecoRepositorio.save(novoEndereco);
            user.get().setEndereco(novoEndereco);
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
}
