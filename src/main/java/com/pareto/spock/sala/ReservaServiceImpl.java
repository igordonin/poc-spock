package com.pareto.spock.sala;

import com.pareto.spock.cliente.Cliente;
import com.pareto.spock.core.CrudBasicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Collection;

@Service
public class ReservaServiceImpl
        extends CrudBasicoService<Reserva, String>
        implements ReservaService {

    private ReservaRepository reservaRepository;

    @Autowired
    public ReservaServiceImpl(ReservaRepository reservaRepository) {
        super(reservaRepository);

        this.reservaRepository = reservaRepository;
    }

    @Override
    public Reserva reservar(Sala sala, Cliente cliente, Calendar data, Double valorLocacao) {
        Reserva reserva = Reserva.builder()
                .sala(sala)
                .cliente(cliente)
                .data(data)
                .valorLocacao(valorLocacao)
                .build();

        return this.save(reserva);
    }

    @Override
    public Collection<Reserva> obter(Cliente cliente) {
        return this.reservaRepository.findByCliente(cliente);
    }
}
