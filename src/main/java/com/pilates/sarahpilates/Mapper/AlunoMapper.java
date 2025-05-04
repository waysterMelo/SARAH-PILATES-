package com.pilates.sarahpilates.Mapper;

import ch.qos.logback.core.model.ComponentModel;
import com.pilates.sarahpilates.Model.Aluno;
import com.pilates.sarahpilates.Model.Dto.AlunoDto;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface AlunoMapper {


    Aluno toEntity(AlunoDto dto);
    AlunoDto toDto(Aluno aluno);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dataCadastro", ignore = true)
    void atualizarAlunoFromDto(AlunoDto dto, @MappingTarget Aluno aluno);



}
