package com.pareto.spock.web

import spock.lang.Specification

import static java.lang.Long.parseLong
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric

class RotaResultadoSpec extends Specification {

    def 'Objeto válido recebendo no construtor uma Rota com filhos'() {
        setup:
        def rotaRaiz = criarRota(null)

        def filho1 = criarRota(rotaRaiz)
        def filho2 = criarRota(rotaRaiz)

        rotaRaiz.setFilhos([filho1, filho2])
        rotaRaiz.atualizarAtributos()

        when:
        def resultado = new RotaResultado(rotaRaiz)

        then: 'Verifica atributos do pai'
        atributosValidos(resultado, rotaRaiz)

        and: 'Verifica atributos dos filhos'
        rotaRaiz.filhos.each { rotaFilho ->
            def resultadoFilho = resultado.filhos.find {
                it.id == rotaFilho.id
            }
            atributosValidos(resultadoFilho, rotaFilho)
        }
    }

    private boolean atributosValidos(RotaResultado resultado, Rota rota) {
        resultado.id == rota.id &&
                resultado.componente == rota.componente &&
                resultado.rotulo == rota.rotulo &&
                resultado.icone == rota.icone &&
                resultado.paiId == rota.pai?.id &&
                resultado.caminho == rota.caminho &&
                resultado.caminhoCompleto == rota.caminhoCompleto &&
                resultado.raiz == rota.raiz &&
                resultado.folha == rota.folha &&
                resultado.filhos.id == rota.filhos.id
    }

    private Long generateId() {
        parseLong(randomNumeric(10))
    }

    private Rota criarRota(Rota pai) {
        def rota = new Rota(
                id: generateId(),
                componente: randomAlphanumeric(16),
                rotulo: randomAlphanumeric(16),
                icone: randomAlphanumeric(16),
                pai: pai,
                filhos: [],
                caminho: randomAlphanumeric(16)
        )

        rota.atualizarAtributos()
        rota
    }
}
