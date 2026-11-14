package com.gens.agendadortarefas.infraestructure.mapper;

import com.gens.agendadortarefas.business.dto.TarefasDTO;
import com.gens.agendadortarefas.infraestructure.entity.TarefasEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TarefasMapper {

    TarefasDTO paraTarefasDTO(TarefasEntity tarefasEntity);

    TarefasEntity paraTarefasEntity(TarefasDTO tarefasDTO);

    List<TarefasDTO> paraListaTarefasDTO(List<TarefasEntity> tarefasEntities);

    List<TarefasEntity> paraListaTarefasEntity(List<TarefasDTO> tarefasDTOs);
}
