package com.pareto.spock.sala;

import com.pareto.spock.cliente.Cliente;
import com.pareto.spock.core.DominioBasico;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Calendar;

@Getter
@Setter
@Builder
public class Reserva extends DominioBasico {
    private Sala sala;
    private Calendar data;
    private Double valorLocacao;
    private Cliente cliente;
}