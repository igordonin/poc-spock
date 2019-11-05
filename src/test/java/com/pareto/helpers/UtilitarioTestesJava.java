package com.pareto.helpers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;

import java.nio.charset.Charset;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;
import static java.lang.Math.random;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;

public class UtilitarioTestesJava {

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String gerarGuid() {
        return randomAlphanumeric(10);
    }

    public static Long gerarId() {
        return parseLong(randomNumeric(10));
    }

    public static Long gerarLongo() {
        return parseLong(randomNumeric(10));
    }

    public static Integer gerarInteiro() {
        return parseInt(randomNumeric(8));
    }

    public static Double gerarDouble() {
        return random();
    }

    public static boolean gerarBooleano() {
        return random() % 2 == 0;
    }

    public static final MediaType CONTENT_TYPE_JSON_UTF8 = new MediaType(
            MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8")
    );
}
