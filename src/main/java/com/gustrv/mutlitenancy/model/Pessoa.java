package com.gustrv.mutlitenancy.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.TenantId;

import java.util.UUID;

@Entity
@Data
@Table(name = "tb_pessoas")
@AllArgsConstructor
@NoArgsConstructor
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column
    private String nome;

    @Column(name = "tenant_id")
    private String tenantId; // Campo que armazena o ID do tenant

}
