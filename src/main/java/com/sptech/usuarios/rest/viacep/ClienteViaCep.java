package com.sptech.usuarios.rest.viacep;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "viacep", url = "https://viacep.com.br/ws/")
public interface ClienteViaCep {

    // https://viacep.com.br/ws/04301000/json/
    @GetMapping("{cep}/json")
    CepResposta getCep(@PathVariable String cep);

}
