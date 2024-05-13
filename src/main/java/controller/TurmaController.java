package controller;

import model.AlunoModel;
import model.TurmaModel;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class TurmaController {
    private final JsonManager jsonManager;

    public TurmaController() {
        this.jsonManager = new JsonManager();
    }

    public void criarTurma(String nome) {
        List<TurmaModel> turmas = jsonManager.carregarDadosTurmas();

        for (TurmaModel turma : turmas) {
            if (turma.getNome().equalsIgnoreCase(nome)) {
                System.out.println("Já existe uma turma com o nome '" + nome + "'.");
                return;
            }
        }

        int idTurma = turmas != null ? turmas.size() + 1 : 1;
        TurmaModel novaTurma = new TurmaModel(idTurma, nome, 0, 0, new ArrayList<>());
        turmas.add(novaTurma);
        jsonManager.salvarDadosTurmas(turmas);
    }

    public void editarNomeTurma(String nomeAtual, String novoNome) {
        List<TurmaModel> turmas = jsonManager.carregarDadosTurmas();
        if (turmas != null) {
            for (TurmaModel turma : turmas) {
                if (turma.getNome().equalsIgnoreCase(nomeAtual)) {

                    for (TurmaModel outraTurma : turmas) {
                        if (outraTurma.getNome().equalsIgnoreCase(novoNome)) {
                            throw new IllegalArgumentException("Nome existente: " + novoNome);
                        }
                    }
                    turma.setNome(novoNome);
                    jsonManager.salvarDadosTurmas(turmas);
                    return;
                }
            }
        }
        throw new NoSuchElementException("Turma não encontrada: " + nomeAtual);
    }

    public void excluirTurma(String nomeTurma) {
        List<TurmaModel> turmas = jsonManager.carregarDadosTurmas();
        if (turmas != null) {
            String nomeTurmaUppercase = nomeTurma.toUpperCase();

            turmas.removeIf(turma -> turma.getNome().toUpperCase().equals(nomeTurmaUppercase));

            jsonManager.salvarDadosTurmas(turmas);
        }
    }

    public float gerarMediaTurmaPorNome(String nomeTurma) {
        List<TurmaModel> turmas = jsonManager.carregarDadosTurmas();
        if (turmas != null) {
            for (TurmaModel turma : turmas) {
                if (turma.getNome().toUpperCase().equals(nomeTurma.toUpperCase())) {
                    return turma.calcularMediaTurma();
                }
            }
        }
        return 0;
    }

    public void adicionarAlunoNaTurma(int idTurma, AlunoModel aluno) {
        List<TurmaModel> turmas = jsonManager.carregarDadosTurmas();
        if (turmas != null) {
            for (TurmaModel turma : turmas) {
                if (turma.getIdTurma() == idTurma) {
                    turma.adicionarAluno(aluno);
                    jsonManager.salvarDadosTurmas(turmas);
                    break;
                }
            }
        }
    }

    public void removerAlunoDaTurma(int idTurma, int idAluno) {
        List<TurmaModel> turmas = jsonManager.carregarDadosTurmas();
        if (turmas != null) {
            for (TurmaModel turma : turmas) {
                if (turma.getIdTurma() == idTurma) {
                    turma.removerAluno(idAluno);
                    jsonManager.salvarDadosTurmas(turmas);
                    break;
                }
            }
        }
    }

    public TurmaModel buscarTurma(String nome) {
        List<TurmaModel> turmas = jsonManager.carregarDadosTurmas();
        if (turmas != null) {
            String nomeUpperCase = nome.toUpperCase();
            for (TurmaModel turma : turmas) {
                if (turma.getNome().toUpperCase().equals(nomeUpperCase)) {
                    return turma;
                }
            }
        }
        return null;
    }
}
