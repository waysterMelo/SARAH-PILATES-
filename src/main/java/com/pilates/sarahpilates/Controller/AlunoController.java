package com.pilates.sarahpilates.Controller;

import com.pilates.sarahpilates.Model.Aluno;
import com.pilates.sarahpilates.Model.Dto.AlunoDto;
import com.pilates.sarahpilates.Service.AlunoService;
import jakarta.servlet.ServletConfig;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/alunos")
public class AlunoController {

    private final AlunoService alunoService;
    private final ServletConfig servletConfig;

    public AlunoController(AlunoService alunoService, ServletConfig servletConfig) {
        this.alunoService = alunoService;
        this.servletConfig = servletConfig;
    }

    @PostMapping
    public ResponseEntity<Aluno> cadastrarAluno(@RequestBody AlunoDto alunoDto){
        Aluno aluno = alunoService.cadastrarAluno(alunoDto);
        return new ResponseEntity<>(aluno, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<Aluno>> listarTodosAlunos() {
        List<Aluno> alunos = alunoService.listarTodosAlunos();
        return ResponseEntity.ok(alunos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> buscarAlunoPorId(@PathVariable Long id) {
        Optional<Aluno> alunoOptional = alunoService.buscarPorId(id);
        return alunoOptional
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aluno> atualizarAluno(@PathVariable Long id, @RequestBody AlunoDto alunoDto){
        try {
                Aluno alunoAtualizado = alunoService.atualizarAlunoPorId(id, alunoDto);
                return ResponseEntity.ok(alunoAtualizado);

        }catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAluno(@PathVariable Long id) {
        try {
            if (alunoService.buscarPorId(id).isPresent()) { //
                alunoService.deletarAlunoPorId(id);
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
