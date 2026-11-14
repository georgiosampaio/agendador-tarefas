package com.gens.agendadortarefas.infraestructure.repository;

import com.gens.agendadortarefas.infraestructure.entity.TarefasEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TarefasRepository extends MongoRepository<TarefasEntity, String> {

    void deleteByEmailUsuario(String email);

    List<TarefasEntity> findByDataEventoBetween(LocalDateTime dataInicio, LocalDateTime dataFim);

    List<TarefasEntity> findByEmailUsuario(String email);


}
