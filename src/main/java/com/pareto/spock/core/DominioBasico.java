package com.pareto.spock.core;

import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
@Getter
public abstract class DominioBasico implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "gerador-guid")
    @GenericGenerator(name = "gerador-guid", strategy = "guid")
    protected String id;

    @Version
    @Column(nullable = false)
    protected Long version;
}
