package com.gens.agendadortarefas.business;

import com.gens.agendadortarefas.business.dto.TarefasDTO;
import com.gens.agendadortarefas.business.exceptions.ResourceNotFoundException;
import com.gens.agendadortarefas.infraestructure.entity.TarefasEntity;
import com.gens.agendadortarefas.infraestructure.enuns.StatusNotificacaoEnum;
import com.gens.agendadortarefas.infraestructure.mapper.TarefaUpdateMapper;
import com.gens.agendadortarefas.infraestructure.mapper.TarefasMapper;
import com.gens.agendadortarefas.infraestructure.repository.TarefasRepository;
import com.gens.agendadortarefas.infraestructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefasService {

    private final TarefasRepository tarefasRepository;
    private final TarefasMapper tarefasMapper;
    private final JwtUtil jwtUtil;
    private final TarefaUpdateMapper tarefaUpdateMapper;

    public TarefasDTO gravarTarefa(String token, TarefasDTO tarefasDTO) {
        String emailUsuario = jwtUtil.extrairEmailDoToken(token.substring(7));
        tarefasDTO.setEmailUsuario(emailUsuario);
        tarefasDTO.setDataCriacao(LocalDateTime.now());
        tarefasDTO.setEmailUsuario(emailUsuario);
        tarefasDTO.setStatus(StatusNotificacaoEnum.PENDENTE);

        TarefasEntity tarefasEntity = tarefasMapper.paraTarefasEntity(tarefasDTO);

        return tarefasMapper.paraTarefasDTO(
                tarefasRepository.save(tarefasEntity));
    }

    public List<TarefasDTO> buscarTarefasPorPeriodo(LocalDateTime dataInicio, LocalDateTime dataFim) {
        List<TarefasEntity> tarefasEntities = tarefasRepository.findByDataEventoBetween(dataInicio, dataFim);
        return tarefasMapper.paraListaTarefasDTO(tarefasEntities);
    }

    public List<TarefasDTO> buscasTarefasEmail(String token) {
        String emailUsuario = jwtUtil.extrairEmailDoToken(token.substring(7));
        List<TarefasEntity> tarefasEntities = tarefasRepository.findByEmailUsuario(emailUsuario);
        return tarefasMapper.paraListaTarefasDTO(tarefasEntities);
    }

    public void deletaTarefasPorId(String id) {
        if (!tarefasRepository.existsById(id)) {
            throw new ResourceNotFoundException("Tarefa não encontrada. ID: " + id);
        }
        tarefasRepository.deleteById(id);
    }

    public TarefasDTO atualizarStatusTarefa(String id, StatusNotificacaoEnum novoStatus) {
        TarefasEntity tarefaExistente = tarefasRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada. ID: " + id));

        tarefaExistente.setStatus(novoStatus);
        TarefasEntity tarefaAtualizada = tarefasRepository.save(tarefaExistente);

        return tarefasMapper.paraTarefasDTO(tarefaAtualizada);
    }

    public TarefasDTO updateTarefasId(String id, TarefasDTO tarefasDTO) {
        TarefasEntity tarefaExistente = tarefasRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada. ID: " + id));

        tarefaUpdateMapper.updateTarefas(tarefasDTO, tarefaExistente);

        TarefasEntity tarefaAtualizada = tarefasRepository.save(tarefaExistente);

        return tarefasMapper.paraTarefasDTO(tarefaAtualizada);
    }


}


