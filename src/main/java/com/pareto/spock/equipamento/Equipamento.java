package com.pareto.spock.equipamento;

import com.pareto.spock.core.DominioBasico;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Table(schema = "evento_banquete")
public class Equipamento extends DominioBasico {

    @NotNull
    private Long idHotel;

    @NotBlank
    private String nome;

    private String descricao;

    @NotNull
    private Double valorLocacao;
}