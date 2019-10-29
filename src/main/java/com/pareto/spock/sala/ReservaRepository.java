package com.pareto.spock.sala;

import com.pareto.spock.cliente.Cliente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.Collection;

import static com.pareto.spock.core.seguranca.Role.Roles.*;

@PreAuthorize("hasAnyRole('" + ROLE_ADMIN + "','" + ROLE_MANAGER + "','" + ROLE_ANALYST + "')")
public interface ReservaRepository extends CrudRepository<Reserva, String> {
    Collection<Reserva> findByCliente(Cliente cliente);
}