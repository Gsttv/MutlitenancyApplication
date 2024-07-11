package com.gustrv.mutlitenancy.repository.suplan;

import com.gustrv.mutlitenancy.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PessoaSuplanRepository extends JpaRepository<Pessoa, UUID> {
}
