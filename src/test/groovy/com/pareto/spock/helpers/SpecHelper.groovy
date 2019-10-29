package com.pareto.spock.helpers

import static java.lang.Long.parseLong
import static java.lang.Math.PI
import static java.lang.Math.random
import static java.lang.Math.random
import static java.lang.Math.round
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric

class SpecHelper {

    static def gerarId(int algarismos = 10) {
        return parseLong(randomNumeric(algarismos))
    }

    static def gerarDouble(int algarismos = 5, double aleatorio = PI) {
        return parseLong(randomNumeric(algarismos)) * aleatorio
    }

    static def gerarValorMonetario() {
        round(random() * 100)
    }

    static def gerarBooleanoAleatorio() {
        random() % 2 == 0
    }
}
