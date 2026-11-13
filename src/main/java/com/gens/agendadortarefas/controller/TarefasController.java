package com.gens.agendadortarefas.controller;

import com.gens.agendadortarefas.business.TarefasService;
import com.gens.agendadortarefas.business.dto.TarefasDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
public class TarefasController {

    private final TarefasService TarefaService;

    @PostMapping
    public ResponseEntity<TarefasDTO> criarTarefa(@RequestHeader("Authorization") String token,
                                                  @RequestBody TarefasDTO tarefasDTO) {

        return ResponseEntity.ok(TarefaService.salvarTarefa(token, tarefasDTO));
    }
}
