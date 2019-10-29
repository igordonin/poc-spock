package com.pareto.spock.equipamento

import com.pareto.spock.sala.Sala
import spock.lang.Specification
import spock.lang.Unroll

import static com.pareto.spock.helpers.AssertionHelper.*
import static com.pareto.spock.helpers.SpecHelper.gerarDouble
import static com.pareto.spock.helpers.SpecHelper.gerarId

class EquipamentoSalaSpec extends Specification {

    def domain = new EquipamentoSala()

    @Unroll
    def 'Testa constraints do campo #campo para o valor #valor'() {
        setup:
        domain."$campo" = valor

        expect:
        assertValidationError(domain, "$campo", "$erroValidation")

        where:
        campo         | valor || erroValidation
        'sala'        | null  || NOT_NULL
        'equipamento' | null  || NOT_NULL
        'quantidade'  | null  || NOT_NULL
    }

    def 'Domínio válido'() {
        setup:
        def id = gerarId()
        def sala = Stub(Sala)
        def equipamento = Stub(Equipamento)
        def quantidade = gerarDouble()

        when:
        def equipamentoSala = new EquipamentoSala(
                id: id,
                sala: sala,
                equipamento: equipamento,
                quantidade: quantidade
        )

        then: 'Não há erros de validação'
        assertValidationHasNoErrors(equipamentoSala)
    }
}