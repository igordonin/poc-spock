package com.pareto.spock.sala;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

import static com.pareto.spock.core.seguranca.Role.Roles.*;

@RepositoryRestResource(collectionResourceRel = "salas", path = "salas")
@PreAuthorize("hasAnyRole('" + ROLE_ADMIN + "','" + ROLE_MANAGER + "','" + ROLE_ANALYST + "')")
public interface SalaRepository extends CrudRepository<Sala, String> {
}