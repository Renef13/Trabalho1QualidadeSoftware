package controller;

import model.AlunoModel;
import model.TurmaModel;

import java.util.ArrayList;
import java.util.List;

public class AlunoController {
    private final JsonManager jsonManager;

    public AlunoController() {
        this.jsonManager = new JsonManager();
    }

    public void criarAluno(String nome) {
        List<AlunoModel> alunos = jsonManager.carregarDadosAlunos();
        int idAluno = alunos != null ? alunos.size() + 1 : 1;
        AlunoModel novoAluno = new AlunoModel(idAluno, nome);
        alunos.add(novoAluno);
        jsonManager.salvarDadosAlunos(alunos);
    }

    public void excluirAluno(int idAluno) {
        List<AlunoModel> alunos = jsonManager.carregarDadosAlunos();
        if (alunos != null) {
            alunos.removeIf(aluno -> aluno.getIdAluno() == idAluno);
            jsonManager.salvarDadosAlunos(alunos);

            List<TurmaModel> turmas = jsonManager.carregarDadosTurmas();
            if (turmas != null) {
                for (TurmaModel turma : turmas) {
                    turma.removerAluno(idAluno);
                }
                jsonManager.salvarDadosTurmas(turmas);
            }
        }
    }

    public void editarNomeAluno(int idAluno, String novoNome) {
        List<AlunoModel> alunos = jsonManager.carregarDadosAlunos();
        if (alunos != null) {
            for (AlunoModel aluno : alunos) {
                if (aluno.getIdAluno() == idAluno) {
                    aluno.setNome(novoNome);
                    jsonManager.salvarDadosAlunos(alunos);
                    break;
                }
            }
        }
    }

    public void adicionarNotaAoAluno(int idAluno, float nota) {
        List<AlunoModel> alunos = jsonManager.carregarDadosAlunos();
        if (alunos != null) {
            for (AlunoModel aluno : alunos) {
                if (aluno.getIdAluno() == idAluno) {

                    if (aluno.getListaNotas() != null && aluno.getListaNotas().size() >= 3) {

                        float mediaAtual = aluno.calcularMedia();
                        if (mediaAtual <= 7) {
                            aluno.adicionarNota(nota);
                            jsonManager.salvarDadosAlunos(alunos);
                            break;
                        } else {
                            System.out.println("O aluno já possui três notas e sua média é maior que 7.0. Não é possível adicionar mais notas.");
                            return;
                        }
                    } else {

                        aluno.adicionarNota(nota);
                        jsonManager.salvarDadosAlunos(alunos);
                        break;
                    }
                }
            }
        }
    }

    public List<AlunoModel> buscarAlunos(String nome) {
        List<AlunoModel> alunos = jsonManager.carregarDadosAlunos();
        List<AlunoModel> alunosEncontrados = new ArrayList<>();
        if (alunos != null) {
            for (AlunoModel aluno : alunos) {
                if (aluno.getNome().equals(nome)) {
                    alunosEncontrados.add(aluno);
                }
            }
        }
        return alunosEncontrados;
    }
    public List<AlunoModel> buscarAlunosPorId(int idAluno) {
        List<AlunoModel> alunos = jsonManager.carregarDadosAlunos();
        List<AlunoModel> alunosEncontrados = new ArrayList<>();
        if (alunos != null) {
            for (AlunoModel aluno : alunos) {
                if (aluno.getIdAluno() == idAluno) {
                    alunosEncontrados.add(aluno);
                }
            }
        }
        return alunosEncontrados;
    }

}
