package com.pilates.sarahpilates.Service;

import com.pilates.sarahpilates.Mapper.AlunoMapper;
import com.pilates.sarahpilates.Model.Aluno;
import com.pilates.sarahpilates.Model.Dto.AlunoDto;
import com.pilates.sarahpilates.Repository.AlunoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;
    private final AlunoMapper mapper;

    public AlunoService(AlunoRepository alunoRepository, AlunoMapper mapper) {
        this.alunoRepository = alunoRepository;
        this.mapper = mapper;
    }

    @Transactional
    public Aluno cadastrarAluno(AlunoDto dto){
        return alunoRepository.save(mapper.toEntity(dto));
    }

    public List<Aluno> listarTodosAlunos(){
        return alunoRepository.findAll();
    }

    public Optional<Aluno> buscarPorId(Long id){
        return alunoRepository.findById(id);
    }

    @Transactional
    public Aluno atualizarAlunoPorId(Long id, AlunoDto dto) {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
            mapper.atualizarAlunoFromDto(dto, aluno);
            return alunoRepository.save(aluno);
    }

    @Transactional
    public void deletarAlunoPorId(Long id){
        alunoRepository.deleteById(id);
    }



}
