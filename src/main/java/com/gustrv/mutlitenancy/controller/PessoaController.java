package com.gustrv.mutlitenancy.controller;

import com.gustrv.mutlitenancy.model.Pessoa;
import com.gustrv.mutlitenancy.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("pessoa")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @PostMapping
    public ResponseEntity<Void> criarPessoa(@RequestBody Pessoa pessoa){
        pessoaService.savePessoa(pessoa,"cagepa");
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
