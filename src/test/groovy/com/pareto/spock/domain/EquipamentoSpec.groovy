package com.pareto.spock.domain

import com.pareto.spock.equipamento.Equipamento
import spock.lang.Specification
import spock.lang.Unroll

import static com.pareto.spock.helpers.AssertionHelper.*
import static com.pareto.spock.helpers.SpecHelper.gerarDouble
import static com.pareto.spock.helpers.SpecHelper.gerarId
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric

class EquipamentoSpec extends Specification {

    def domain = new Equipamento()

    @Unroll
    def 'Testa constraints do campo #campo para o valor #valor'() {
        setup:
        domain."$campo" = valor

        expect:
        assertValidationError(domain, "$campo", "$erroValidation")

        where:
        campo          | valor || erroValidation
        'idHotel'      | null  || NOT_NULL
        'nome'         | null  || NOT_BLANK
        'nome'         | ""    || NOT_BLANK
        'nome'         | " "   || NOT_BLANK
        'valorLocacao' | null  || NOT_NULL
    }

    def 'Domínio válido'() {
        setup:
        def id = gerarId()
        def idHotel = gerarId()
        def nome = randomAlphanumeric(10)
        def descricao = randomAlphanumeric(10)

        when:
        def equipamento = new Equipamento(
                id: id,
                idHotel: idHotel,
                nome: nome,
                descricao: descricao,
                valorLocacao: gerarDouble()
        )

        then: 'Não há erros de validação'
        assertValidationHasNoErrors(equipamento)
    }
}