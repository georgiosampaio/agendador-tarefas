package com.gens.agendadortarefas.controller;

import com.gens.agendadortarefas.business.TarefasService;
import com.gens.agendadortarefas.business.dto.TarefasDTO;
import com.gens.agendadortarefas.infraestructure.enuns.StatusNotificacaoEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
public class TarefasController {

    private final TarefasService tarefasService;

    @PostMapping
    public ResponseEntity<TarefasDTO> criarTarefa(@RequestHeader("Authorization") String token,
                                                  @RequestBody TarefasDTO tarefasDTO) {

        return ResponseEntity.ok(tarefasService.gravarTarefa(token, tarefasDTO));
    }

    @GetMapping("/eventos")
    public ResponseEntity<List<TarefasDTO>> buscarTarefasPorPeriodo(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicio,
                                                                    @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFim) {
        return ResponseEntity.ok(tarefasService.buscarTarefasPorPeriodo(dataInicio, dataFim));
    }

    @GetMapping
    public ResponseEntity<List<TarefasDTO>> buscarTarefasPorEmail(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(tarefasService.buscasTarefasEmail(token));
    }

    @DeleteMapping
    public ResponseEntity<Void> deletaTarefasPorId(@RequestParam String id) {
        tarefasService.deletaTarefasPorId(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping
    public ResponseEntity<TarefasDTO> atualizarStatusTarefa(@RequestParam String id,
                                                            @RequestParam("novoStatus") StatusNotificacaoEnum novoStatus) {
        return ResponseEntity.ok(tarefasService.atualizarStatusTarefa(id, novoStatus));
    }

    @PutMapping
    public ResponseEntity<TarefasDTO> atualizarTaresfaId(@RequestParam("id") String id,
                                                         @RequestBody TarefasDTO tarefasDTO) {
        return ResponseEntity.ok(tarefasService.updateTarefasId(id, tarefasDTO));
    }


}
