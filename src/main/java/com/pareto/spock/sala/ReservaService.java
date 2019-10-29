package com.pareto.spock.sala;

import com.pareto.spock.cliente.Cliente;

import java.util.Calendar;
import java.util.Collection;

public interface ReservaService {
    Reserva reservar(Sala sala, Cliente cliente, Calendar data, Double valorLocacao);

    Collection<Reserva> obter(Cliente cliente);
}
