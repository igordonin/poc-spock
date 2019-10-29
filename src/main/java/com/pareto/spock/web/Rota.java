package com.pareto.spock.web;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pareto.spock.core.DominioBasico;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@Entity
@Table(schema = "evento_banquete")
//@ParentLevelConstraint TODO: (ID)
public class Rota extends DominioBasico {

    private String componente;

    @NotBlank
    private String rotulo;

    @ManyToOne
    private Rota pai;

    private String icone;

    @OneToMany(mappedBy = "pai")
    @JsonIgnore
    private List<Rota> filhos;

    @NotBlank
    private String caminho;

    @Setter(AccessLevel.NONE)
    @Column(unique = true)
    @NotBlank
    private String caminhoCompleto;

    @Setter(AccessLevel.NONE)
    @Column
    private boolean raiz;

    @Setter(AccessLevel.NONE)
    @Column
    private boolean folha;

    public void atualizarAtributos() {
        this.atualizarFolha();
        this.atualizarRaiz();
        this.atualizarCaminhoCompleto();
    }

    private void atualizarFolha() {
        this.folha = (this.filhos == null || this.filhos.size() <= 0);
    }

    private void atualizarRaiz() {
        this.raiz = (this.pai == null);
    }

    private void atualizarCaminhoCompleto() {
        this.caminhoCompleto = "";

        if (this.getPai() != null)
            this.caminhoCompleto += this.getPai().getCaminhoCompleto() + SEPARADOR_CAMINHO;

        this.caminhoCompleto += this.getCaminho();
    }

    public static final String SEPARADOR_CAMINHO = "/";
}

