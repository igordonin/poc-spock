package com.pareto.spock.equipamento;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(
        excerptProjection = EquipamentoSalaProjection.class,
        collectionResourceRel = "equipamentos-sala",
        path = "equipamentos-sala"
)
// TODO: (ID) Avaliar
//@PreAuthorize("hasAnyRole('" + ROLE_ADMIN + "','" + ROLE_MANAGER + "','" + ROLE_ANALYST + "')")
public interface EquipamentoSalaRepository extends CrudRepository<EquipamentoSala, String> {
}