package com.pareto.spock.equipamento;

import com.pareto.spock.core.DominioBasico;
import com.pareto.spock.sala.Sala;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Table(schema = "evento_banquete")
public class EquipamentoSala extends DominioBasico implements EquipamentoSalaProjection {

    @ManyToOne
    @JoinColumn(name = "id_sala", updatable = false)
    @NotNull
    private Sala sala;

    @ManyToOne
    @JoinColumn(name = "id_equipamento", updatable = false)
    @NotNull
    private Equipamento equipamento;

    @NotNull
    private Double quantidade;

    // TODo: (ID) Fazem parte da estrutura com projection para obter os ids dos relacionamentos de EquipamenoSalao
    public String getIdSala() {
        return this.getSala().getId();
    }

    public String getIdEquipamento() {
        return this.getEquipamento().getId();
    }
}