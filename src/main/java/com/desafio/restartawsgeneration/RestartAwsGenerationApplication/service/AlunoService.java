package com.desafio.restartawsgeneration.RestartAwsGenerationApplication.service;

import com.desafio.restartawsgeneration.RestartAwsGenerationApplication.dto.DadosAtualizacaoAluno;
import com.desafio.restartawsgeneration.RestartAwsGenerationApplication.dto.DadosCadastroAluno;
import com.desafio.restartawsgeneration.RestartAwsGenerationApplication.dto.DadosDetalhamentoAluno;
import com.desafio.restartawsgeneration.RestartAwsGenerationApplication.entity.Aluno;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Schema(description = "Serviço de matrícula")
public interface AlunoService {

    @Transactional
    @Schema(description = "Criar matrícula")
    Aluno criarMatricula(@Valid DadosCadastroAluno aluno);

    @Schema(description = "Buscar matrícula por ID")
    Aluno buscarMatriculaPorId(Long id);

    @Schema(description = "Listar matrículas")
    Page<DadosDetalhamentoAluno> listar(Pageable paginacao);

    @Transactional
    @Schema(description = "Atualizar matrícula")
    Aluno atualizarMatricula(@Valid DadosAtualizacaoAluno aluno);

    @Transactional
    @Schema(description = "Deletar matrícula por ID")
    void deleteMatriculaById(Long id);
}
