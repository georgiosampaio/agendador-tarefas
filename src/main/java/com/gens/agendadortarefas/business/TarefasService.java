package com.gens.agendadortarefas.business;

import com.gens.agendadortarefas.business.dto.TarefasDTO;
import com.gens.agendadortarefas.infraestructure.entity.TarefasEntity;
import com.gens.agendadortarefas.infraestructure.enuns.StatusNotificacaoEnum;
import com.gens.agendadortarefas.infraestructure.mapper.TarefasMapper;
import com.gens.agendadortarefas.infraestructure.repository.TarefasRepository;
import com.gens.agendadortarefas.infraestructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TarefasService {

    private final TarefasRepository tarefasRepository;
    private final TarefasMapper tarefasMapper;
    private final JwtUtil jwtUtil;

    public TarefasDTO salvarTarefa(String token, TarefasDTO tarefasDTO) {
        String emailUsuario = jwtUtil.extrairEmailDoToken(token.substring(7));
        tarefasDTO.setEmailUsuario(emailUsuario);
        tarefasDTO.setDataCriacao(LocalDateTime.now());
        tarefasDTO.setEmailUsuario(emailUsuario);
        tarefasDTO.setStatus(StatusNotificacaoEnum.PENDENTE);

        TarefasEntity tarefasEntity = tarefasMapper.paraTarefasEntity(tarefasDTO);

        return tarefasMapper.paraTarefasDTO(
                tarefasRepository.save(tarefasEntity));
    }


}
