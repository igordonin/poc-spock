package com.pareto.spock.cliente;

import com.pareto.spock.core.DominioBasico;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Cliente extends DominioBasico {
    private String nome;
}