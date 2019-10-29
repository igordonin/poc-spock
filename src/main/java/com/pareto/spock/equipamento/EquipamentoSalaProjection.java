package com.pareto.spock.equipamento;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "equipamentoSala", types = {EquipamentoSala.class})
public interface EquipamentoSalaProjection {
    String getId();

    String getIdSala();

    String getIdEquipamento();

    Double getQuantidade();
}