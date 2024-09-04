package com.desafio.restartawsgeneration.RestartAwsGenerationApplication.controller;

import com.desafio.restartawsgeneration.RestartAwsGenerationApplication.dto.DadosAtualizacaoAluno;
import com.desafio.restartawsgeneration.RestartAwsGenerationApplication.dto.DadosCadastroAluno;
import com.desafio.restartawsgeneration.RestartAwsGenerationApplication.dto.DadosDetalhamentoAluno;
import com.desafio.restartawsgeneration.RestartAwsGenerationApplication.service.AlunoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/api/v1/matricula")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Tag(name = "Matrícula", description = "APIs para manipulação de matrículas")
public class AlunoController {

    @Schema(description = "Serviço de matrícula")
    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @PostMapping
    @Transactional
    @Operation(summary = "Cadastrar uma nova matrícula", description = "Cria uma nova matrícula com os dados fornecidos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Matrícula criada com sucesso."),
            @ApiResponse(responseCode = "400", description = "Dados de cadastro inválidos.")
    })
    public ResponseEntity<DadosDetalhamentoAluno> cadastrar(
            @RequestBody @Valid DadosCadastroAluno dadosCadastroAluno,
            UriComponentsBuilder uriBuilder
    ) {
        var aluno = alunoService.criarMatricula(dadosCadastroAluno);
        var uri = uriBuilder.path("/api/v1/matricula/{id}").buildAndExpand(aluno.getId()).toUri();
        return created(uri).body(new DadosDetalhamentoAluno(aluno));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Detalhar uma matrícula", description = "Retorna os detalhes de uma matrícula específica.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Matrícula encontrada."),
            @ApiResponse(responseCode = "404", description = "Matrícula não encontrada.")
    })
    public ResponseEntity<DadosDetalhamentoAluno> detalhar(@PathVariable Long id) {
        var aluno = alunoService.buscarMatriculaPorId(id);
        return ok(new DadosDetalhamentoAluno(aluno));
    }

    @GetMapping
    @Operation(summary = "Listar matrículas", description = "Retorna uma lista paginada de matrículas.")
    @ApiResponse(responseCode = "200", description = "Lista de matrículas.")
    public ResponseEntity<Page<DadosDetalhamentoAluno>> listar(@PageableDefault(size = 20, sort = {"nome"}) Pageable paginacao) {
        var alunos = alunoService.listar(paginacao);
        return ok(alunos);
    }

    @GetMapping("/detalhar-completo/{id}")
    @Operation(summary = "Detalhar uma matrícula completa", description = "Retorna os detalhes completos de uma matrícula específica.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Matrícula encontrada."),
            @ApiResponse(responseCode = "404", description = "Matrícula não encontrada.")
    })
    public ResponseEntity<DadosDetalhamentoAluno> detalharCompleto(@PathVariable Long id) {
        var aluno = alunoService.buscarMatriculaPorId(id);
        return ok(new DadosDetalhamentoAluno(aluno));
    }

    @PutMapping
    @Transactional
    @Operation(summary = "Atualizar os dados de uma matrícula")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Matrícula atualizada com sucesso."),
            @ApiResponse(responseCode = "400", description = "Dados de atualização inválidos."),
            @ApiResponse(responseCode = "404", description = "Matrícula não encontrada.")
    })
    public ResponseEntity<DadosDetalhamentoAluno> atualizar(@RequestBody @Valid DadosAtualizacaoAluno dadosAtualizacaoAluno) {
        var aluno = alunoService.atualizarMatricula(dadosAtualizacaoAluno);
        return ok(new DadosDetalhamentoAluno(aluno));
    }

    @DeleteMapping("/{id}")
    @Transactional
    @Operation(summary = "Deletar uma matrícula da base de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Matrícula deletada com sucesso."),
            @ApiResponse(responseCode = "404", description = "Matrícula não encontrada.")
    })
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        alunoService.deleteMatriculaById(id);
        return noContent().build();
    }
}