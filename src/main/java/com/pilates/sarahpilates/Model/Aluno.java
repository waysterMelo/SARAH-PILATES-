package com.pilates.sarahpilates.Model;

import com.pilates.sarahpilates.Model.Dto.AlunoDto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private LocalDate dataNascimento;
    private Integer idade;
    private String telefone;
    private String endereco;
    private String bairro;
    private String cidade;
    private String estado;
    private String profissao;
    private String diagnosticoClinico;
    private String medicoResponsavel;
    private Boolean jaPraticouPilates;

    private LocalDateTime dataCadastro;

    @PrePersist
    public void aoCadastrar(){
        this.dataCadastro = LocalDateTime.now();
    }


    public Aluno (AlunoDto dto){
        this.nome = dto.nome;
        this.dataNascimento = dto.dataNascimento;
        this.idade = dto.idade;
        this.telefone = dto.telefone;
        this.endereco = dto.endereco;
        this.bairro = dto.bairro;
        this.cidade = dto.cidade;
        this.estado = dto.estado;
        this.profissao = dto.profissao;
        this.diagnosticoClinico = dto.diagnosticoClinico;
        this.medicoResponsavel = dto.medicoResponsavel;
        this.jaPraticouPilates = dto.jaPraticouPilates;
    }
}
