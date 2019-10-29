package com.pareto.spock.web;

import com.pareto.spock.web.Rota;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "rota", path = "rota")
// TODO: (ID) Avaliar
//@PreAuthorize("hasAnyRole('" + ROLE_ADMIN + "','" + ROLE_MANAGER + "','" + ROLE_ANALYST + "')")
public interface RotaRepository extends CrudRepository<Rota, String> {

    List<Rota> findByRaizIsTrue();
}