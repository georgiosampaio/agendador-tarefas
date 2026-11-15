package com.gens.agendadortarefas.infraestructure.mapper;

import com.gens.agendadortarefas.business.dto.TarefasDTO;
import com.gens.agendadortarefas.infraestructure.entity.TarefasEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TarefaUpdateMapper {

    void updateTarefas(TarefasDTO tarefasDTO, @MappingTarget TarefasEntity tarefasEntity);

}
