package com.pareto.spock.web

import spock.lang.Specification

import static org.apache.commons.lang3.RandomStringUtils.randomNumeric

class RotaServiceSpec extends Specification {

    RotaService service

    def rotaRepositoryMock = Mock(RotaRepository)

    def setup() {
        this.service = new RotaService(this.rotaRepositoryMock)
    }

    def "obterRotaRaiz"() {
        setup:
        def rotaIds = [generateId(), generateId()]

        def rotasRaiz = rotaIds.collect {
            new Rota(id: it, filhos: [])
        }

        when: 'O método do service é executado'
        def result = this.service.obterRotasRaiz()

        then: 'Deve executar rotaRepository.findByRaizIsTrue()'
        1 * this.rotaRepositoryMock.findByRaizIsTrue() >> rotasRaiz

        and: 'O resultado deve ser a lista de RotaResultado criada'
        result.id == rotaIds

        and: 'Garante o tipo retornado'
        result.every { it instanceof RotaResultado }
    }

    def generateId() {
        randomNumeric(10)
    }
}
