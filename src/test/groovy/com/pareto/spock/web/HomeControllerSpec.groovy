package com.pareto.spock.web

import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric
import static org.springframework.http.HttpStatus.OK
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup

class HomeControllerSpec extends Specification {

    MockMvc mockMvc

    def URL_BASE = "/"

    def rotaServiceMock = Mock(RotaService)

    def setup() {
        def controller = new HomeController(this.rotaServiceMock)
        mockMvc = standaloneSetup(controller).build()
    }

    def "GET#index"() {
        given:
        def valorAleatorio = randomAlphanumeric(16)
        def resultadoEsperado = [[value: valorAleatorio]]

        when: "Uma requisição é feita a ação index"
        def response = mockMvc
                .perform(get(URL_BASE))
                .andReturn()
//                .contentType(APPLICATION_JSON)
//                .content('{"url": "/some/value"}'))

        then: "RotaService.obterRotasRaiz deve ser invocado com os parâmetros corretos"
        1 * rotaServiceMock.obterRotasRaiz() >> resultadoEsperado

        and: "A View esperada é retornada"
        response.modelAndView.viewName == "index"

        and: "O Model contém os modelos esperados"
        response.modelAndView.model.containsKey("rotas")

        and: "O modelo contém os valores esperados"
        response.modelAndView.model.get("rotas").value.contains(valorAleatorio)

        and: "O status HTTP esperado é retornado"
        response.mockResponse.status == OK.value()
    }
}
