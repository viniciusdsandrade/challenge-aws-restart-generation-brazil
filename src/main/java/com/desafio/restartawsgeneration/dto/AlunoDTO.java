package com.desafio.restartawsgeneration.dto;

import com.desafio.restartawsgeneration.entity.Aluno;
import com.desafio.restartawsgeneration.entity.Professor;
import com.desafio.restartawsgeneration.entity.Sala;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AlunoDTO {
    
    private Aluno aluno;
    private Professor professor;
    private Sala sala;
}
