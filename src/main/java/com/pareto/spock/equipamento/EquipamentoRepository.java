package com.pareto.spock.equipamento;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "equipamentos", path = "equipamentos")
// TODO: (ID) Avaliar
//@PreAuthorize("hasAnyRole('" + ROLE_ADMIN + "','" + ROLE_MANAGER + "','" + ROLE_ANALYST + "')")
public interface EquipamentoRepository extends CrudRepository<Equipamento, String> {
}