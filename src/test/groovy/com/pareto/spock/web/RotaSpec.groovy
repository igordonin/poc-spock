package com.pareto.spock.web

import spock.lang.Specification
import spock.lang.Unroll

import static com.pareto.spock.helpers.AssertionHelper.*
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric

class RotaSpec extends Specification {

    Rota domain = new Rota()

    @Unroll
    def 'Testa constraints do campo #campo para o valor #valor'() {
        setup:
        domain."$campo" = valor

        expect:
        assertValidationError(domain, "$campo", "$erroValidation")

        where:
        campo     | valor || erroValidation
        'rotulo'  | null  || NOT_BLANK
        'rotulo'  | ""    || NOT_BLANK
        'rotulo'  | " "   || NOT_BLANK
        'caminho' | null  || NOT_BLANK
        'caminho' | ""    || NOT_BLANK
        'caminho' | " "   || NOT_BLANK
    }

    def 'Domínio válido quando é raíz e não possui filhos'() {
        setup:
        def componente = randomAlphanumeric(10)
        def rotulo = randomAlphanumeric(10)
        def caminho = randomAlphanumeric(10)

        when:
        def rota = new Rota()
        rota.setComponente(componente)
        rota.setRotulo(rotulo)
        rota.setCaminho(caminho)

        rota.atualizarAtributos()

        then: 'Não há erros de validação'
        assertValidationHasNoErrors(rota)

        and: 'Caminho completo deve incluir caminho completo do pai mais o próprio caminho'
        rota.getCaminhoCompleto() == caminho

        and: 'Raíz deve estar marcado como falso'
        rota.isRaiz()

        and: 'Folha deve ser verdadeiro'
        rota.isFolha()
    }

    def 'Domínio válido quando é raíz e possui filhos'() {
        setup:
        def componente = randomAlphanumeric(10)
        def rotulo = randomAlphanumeric(10)
        def caminho = randomAlphanumeric(10)

        def filhos = [Stub(Rota), Stub(Rota)]

        when:
        def rota = new Rota()
        rota.setComponente(componente)
        rota.setRotulo(rotulo)
        rota.setCaminho(caminho)
        rota.setFilhos(filhos)

        rota.atualizarAtributos()

        then: 'Não há erros de validação'
        assertValidationHasNoErrors(rota)

        and: 'Caminho completo deve incluir caminho completo do pai mais o próprio caminho'
        rota.getCaminhoCompleto() == caminho

        and: 'Raíz deve estar marcado como falso'
        rota.isRaiz()

        and: 'Folha deve ser verdadeiro'
        !rota.isFolha()
    }

    def 'Domínio válido quando não é raíz e não possui filhos'() {
        setup:
        def componente = randomAlphanumeric(10)
        def rotulo = randomAlphanumeric(10)
        def caminho = randomAlphanumeric(10)
        def pai = Stub(Rota)

        pai.getCaminhoCompleto() >> "/"

        when:
        def rota = new Rota()
        rota.setComponente(componente)
        rota.setRotulo(rotulo)
        rota.setCaminho(caminho)
        rota.setPai(pai)

        rota.atualizarAtributos()

        then: 'Não há erros de validação'
        assertValidationHasNoErrors(rota)

        and: 'Caminho completo deve incluir caminho completo do pai mais o próprio caminho'
        rota.getCaminhoCompleto() == pai.getCaminhoCompleto() + "/" + caminho

        and: 'Raíz deve estar marcado como falso'
        !rota.isRaiz()

        and: 'Folha deve ser verdadeiro'
        rota.isFolha()
    }

    def 'Domínio válido quando não é raíz e possui filhos'() {
        setup:
        def componente = randomAlphanumeric(10)
        def rotulo = randomAlphanumeric(10)
        def caminho = randomAlphanumeric(10)

        def filhos = [Stub(Rota), Stub(Rota)]

        def pai = Stub(Rota)
        pai.getCaminhoCompleto() >> "/"

        when:
        def rota = new Rota()
        rota.setComponente(componente)
        rota.setRotulo(rotulo)
        rota.setCaminho(caminho)
        rota.setFilhos(filhos)
        rota.setPai(pai)

        rota.atualizarAtributos()

        then: 'Não há erros de validação'
        assertValidationHasNoErrors(rota)

        and: 'Caminho completo deve incluir caminho completo do pai mais o próprio caminho'
        rota.getCaminhoCompleto() == pai.getCaminhoCompleto() + "/" + caminho

        and: 'Raíz deve estar marcado como falso'
        !rota.isRaiz()

        and: 'Folha deve ser verdadeiro'
        !rota.isFolha()
    }
}