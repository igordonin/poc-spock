package com.pareto.spock.domain

import com.pareto.spock.sala.Sala
import spock.lang.Specification
import spock.lang.Unroll

import static com.pareto.spock.helpers.AssertionHelper.*
import static com.pareto.spock.helpers.SpecHelper.gerarDouble
import static com.pareto.spock.helpers.SpecHelper.gerarId
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric

class SalaSpec extends Specification {

    def domain = new Sala()

    @Unroll
    def 'Testa constraints do campo #campo para o valor #valor'() {
        setup:
        domain."$campo" = valor

        expect:
        assertValidationError(domain, "$campo", "$erroValidation")

        where:
        campo  | valor || erroValidation
        'nome' | null  || NOT_BLANK
        'nome' | ""    || NOT_BLANK
        'nome' | " "   || NOT_BLANK
    }

    def 'Domínio válido'() {
        setup:
        def id = gerarId()
        def pai = Stub(Sala)
        def nome = randomAlphanumeric(10)
        def descricao = randomAlphanumeric(10)

        def largura = gerarDouble()
        def comprimento = gerarDouble()
        def altura = gerarDouble()

        when:
        def salao = new Sala(
                id: id,
                pai: pai,
                nome: nome,
                descricao: descricao,
                largura: largura,
                comprimento: comprimento,
                altura: altura
        )

        then: 'Não há erros de validação'
        assertValidationHasNoErrors(salao)
    }
}