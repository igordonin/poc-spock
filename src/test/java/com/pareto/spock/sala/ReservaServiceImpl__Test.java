package com.pareto.spock.sala;

import com.pareto.spock.cliente.Cliente;
import com.pareto.spock.sala.Reserva.ReservaBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Calendar;

import static com.pareto.helpers.UtilitarioTestesJava.gerarDouble;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(SpringJUnit4ClassRunner.class)
@PrepareForTest({ReservaServiceImpl.class, Reserva.class})
public class ReservaServiceImpl__Test {

    @Autowired
    private ReservaServiceImpl service;

    @MockBean
    private ReservaRepository reservaRepositoryMock;

    public void init() {
        initMocks(this);
    }

    @Test
    public void reservar() {
        // Inicialização
        Sala sala = mock(Sala.class);
        Cliente cliente = mock(Cliente.class);
        Calendar data = Calendar.getInstance();
        Double valorLocacao = gerarDouble();

        Reserva reserva = mock(Reserva.class);

        // Mocks do Builder
        PowerMockito.mockStatic(Reserva.class);

        ReservaBuilder reservaBuilderMock = mock(ReservaBuilder.class, RETURNS_SELF);

        BDDMockito.given(Reserva.builder())
                .willReturn(reservaBuilderMock);

        when(reservaBuilderMock.build())
                .thenReturn(reserva);

        // Declaração dos Mocks
        when(this.reservaRepositoryMock.save(reserva))
                .thenReturn(reserva);

        // Execução
        Reserva resultado = this.service.reservar(sala, cliente, data, valorLocacao);

        // Verificações do Builder
        verify(reservaBuilderMock).sala(sala);
        verify(reservaBuilderMock).cliente(cliente);
        verify(reservaBuilderMock).data(data);
        verify(reservaBuilderMock).valorLocacao(valorLocacao);
        verify(reservaBuilderMock).build();

        // Verificações
        assertEquals(reserva, resultado);

        verify(this.reservaRepositoryMock).save(reserva);

        verifyNoMoreInteractions(reservaRepositoryMock, reservaBuilderMock);
    }

    @Configuration
    @Import(ReservaServiceImpl.class)
    static class Config {
    }
}
