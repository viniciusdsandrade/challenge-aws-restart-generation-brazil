package com.desafio.restartawsgeneration.RestartAwsGenerationApplication.service.impl;

import com.desafio.restartawsgeneration.RestartAwsGenerationApplication.dto.DadosAtualizacaoAluno;
import com.desafio.restartawsgeneration.RestartAwsGenerationApplication.dto.DadosCadastroAluno;
import com.desafio.restartawsgeneration.RestartAwsGenerationApplication.dto.DadosDetalhamentoAluno;
import com.desafio.restartawsgeneration.RestartAwsGenerationApplication.entity.Aluno;
import com.desafio.restartawsgeneration.RestartAwsGenerationApplication.exception.MatriculaNotFoundException;
import com.desafio.restartawsgeneration.RestartAwsGenerationApplication.repository.MatriculaRepository;
import com.desafio.restartawsgeneration.RestartAwsGenerationApplication.service.AlunoService;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static org.slf4j.LoggerFactory.getLogger;

@Service("matriculaService")
@Schema(description = "Implementação do serviço de matrícula.")
public class AlunoServiceImpl implements AlunoService {

    @Schema(description = "Logger para a classe.")
    private static final Logger log = getLogger(AlunoServiceImpl.class);

    @Schema(description = "Repositório de matrículas.")
    private final MatriculaRepository matriculaRepository;

    public AlunoServiceImpl(MatriculaRepository matriculaRepository) {
        this.matriculaRepository = matriculaRepository;
    }

    @Override
    @Transactional
    @Schema(description = "Cria uma nova matrícula.")
    public Aluno criarMatricula(@Valid DadosCadastroAluno dados) {
        log.info("Criando nova matrícula com dados: {}", dados);
        Aluno aluno = new Aluno(dados);
        log.debug("Nova matrícula criada: {}", aluno);

        matriculaRepository.save(aluno);
        log.info("Matrícula salva com sucesso: {}", aluno);

        return aluno;
    }

    @Override
    @Schema(description = "Busca uma matrícula por ID.")
    public Aluno buscarMatriculaPorId(Long id) {
        log.info("Verificando matrícula com ID: {}", id);
        Aluno aluno = existePeloId(id);

        log.info("Matrícula encontrada: {}", aluno);
        return aluno;
    }

    @Override
    @Schema(description = "Lista as matrículas com paginação.")
    public Page<DadosDetalhamentoAluno> listar(Pageable paginacao) {
        log.info("Listando alunos com paginação: {}", paginacao);
        Page<Aluno> matriculas = matriculaRepository.findAll(paginacao);

        log.debug("Matrículas encontradas: {}", matriculas.getContent());
        log.info("Total de matrículas encontradas: {}", matriculas.getTotalElements());

        return matriculas.map(DadosDetalhamentoAluno::new);
    }

    @Override
    @Transactional
    @Schema(description = "Atualiza uma matrícula existente.")
    public Aluno atualizarMatricula(@Valid DadosAtualizacaoAluno dados) {
        log.info("Atualizando matrícula com ID: {}", dados.id());
        log.debug("Dados da atualização: {}", dados);

        Aluno aluno = existePeloId(dados.id());
        log.debug("Matrícula encontrada para atualização: {}", aluno);

        aluno.atualizarInformacoes(dados);
        log.debug("Informações da matrícula atualizadas: {}", aluno);

        matriculaRepository.save(aluno);
        log.info("Matrícula atualizada com sucesso: {}", aluno);

        return aluno;
    }

    @Override
    @Transactional
    @Schema(description = "Exclui uma matrícula por ID.")
    public void deleteMatriculaById(Long id) {
        log.info("Excluindo matrícula com ID: {}", id);
        Aluno aluno = existePeloId(id);

        log.debug("Matrícula encontrada para exclusão: {}", aluno);
        matriculaRepository.delete(aluno);
        log.info("Matrícula com ID {} excluída com sucesso.", id);
    }

    private Aluno existePeloId(Long id) {
        log.info("Buscando matrícula com ID: {}", id);
        return matriculaRepository.findById(id)
                .orElseThrow(
                        () -> {
                            log.error("Matrícula com ID {} não encontrada.", id);
                            return new MatriculaNotFoundException("Matrícula não encontrada");
                        }
                );
    }
}
