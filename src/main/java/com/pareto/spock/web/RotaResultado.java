package com.pareto.spock.web;

import lombok.Getter;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Getter
public class RotaResultado {
    private String id;
    private String componente;
    private String rotulo;
    private String icone;
    private String paiId;
    private List<RotaResultado> filhos;
    private String caminho;
    private String caminhoCompleto;
    private boolean raiz;
    private boolean folha;

    public RotaResultado(Rota rota) {
        this.id = rota.getId();
        this.componente = rota.getComponente();
        this.rotulo = rota.getRotulo();
        this.icone = rota.getIcone();
        this.paiId = rota.getPai() != null ? rota.getPai().getId() : null;
        this.caminho = rota.getCaminho();
        this.caminhoCompleto = rota.getCaminhoCompleto();
        this.raiz = rota.isRaiz();
        this.folha = rota.isFolha();

        this.filhos = rota.getFilhos().stream()
                .map(RotaResultado::new)
                .collect(toList());
    }
}
