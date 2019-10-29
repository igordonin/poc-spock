package com.pareto.spock.web;

import com.pareto.spock.core.CrudBasicoService;
import com.pareto.spock.web.Rota;
import com.pareto.spock.web.RotaRepository;
import com.pareto.spock.web.RotaResultado;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class RotaService extends CrudBasicoService<Rota, String> {

    private RotaRepository rotaRepository;

    public RotaService(RotaRepository rotaRepository) {
        super(rotaRepository);

        this.rotaRepository = rotaRepository;
    }

    public List<RotaResultado> obterRotasRaiz() {
        return this.rotaRepository.findByRaizIsTrue().stream()
                .map(RotaResultado::new)
                .collect(toList());
    }
}
