package com.pareto.spock.sala;

import com.pareto.spock.core.DominioBasico;
import com.pareto.spock.equipamento.EquipamentoSala;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
public class Sala extends DominioBasico {
    private Sala pai;

    @NotBlank
    private String nome;

    private String descricao;
    private Double largura;
    private Double comprimento;
    private Double altura;
    private List<Sala> filhos;
    private List<EquipamentoSala> equipamentosSala;
}