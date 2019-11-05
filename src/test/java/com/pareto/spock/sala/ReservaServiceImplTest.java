package com.pareto.spock.sala;

import com.pareto.spock.cliente.Cliente;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.stubbing.Answer;
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
@PrepareForTest({ReservaServiceImpl.class})
public class ReservaServiceImplTest {

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

        // Mock da resposta
        Answer<Reserva> salvar = invocation -> invocation.getArgument(0);

        // Declaração dos Mocks
        doAnswer(salvar)
                .when(this.reservaRepositoryMock)
//                .save(any(Reserva.class));
                .save(reserva);

        // Execução
        Reserva resultado = this.service.reservar(sala, cliente, data, valorLocacao);

        // Verificações
        assertEquals(cliente, resultado.getCliente());
        assertEquals(sala, resultado.getSala());
        assertEquals(valorLocacao, resultado.getValorLocacao());
        assertEquals(data, resultado.getData());

        verify(this.reservaRepositoryMock).save(reserva);
//        verify(this.reservaRepositoryMock).save(any(Reserva.class));

        verifyNoMoreInteractions(reservaRepositoryMock);
    }

    @Configuration
    @Import(ReservaServiceImpl.class)
    static class Config {
    }
}
