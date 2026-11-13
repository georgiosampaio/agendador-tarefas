package com.gens.agendadortarefas.infraestructure.repository;

import com.gens.agendadortarefas.infraestructure.entity.TarefasEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarefasRepository extends MongoRepository<TarefasEntity, String> {

    void deleteByEmailUsuario(String email);
}
