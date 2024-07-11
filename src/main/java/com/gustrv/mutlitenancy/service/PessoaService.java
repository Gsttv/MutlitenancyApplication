package com.gustrv.mutlitenancy.service;


import com.gustrv.mutlitenancy.model.Pessoa;
import com.gustrv.mutlitenancy.repository.cagepa.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    // Método para salvar uma pessoa com escolha do banco de dados
    @Transactional("suplanTransactionManager")
    public Pessoa savePessoaInSuplan(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    @Transactional("cagepaTransactionManager")
    public Pessoa savePessoaInCagepa(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    // Método geral que decide qual banco usar baseado em um parâmetro
    public Pessoa savePessoa(Pessoa pessoa, String tenant) {
        if ("suplan".equalsIgnoreCase(tenant)) {
            return savePessoaInSuplan(pessoa);
        } else if ("cagepa".equalsIgnoreCase(tenant)) {
            return savePessoaInCagepa(pessoa);
        } else {
            throw new IllegalArgumentException("Unknown tenant");
        }
    }

}
