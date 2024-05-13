package controller;

import model.AlunoModel;
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
                    aluno.adicionarNota(nota);
                    jsonManager.salvarDadosAlunos(alunos);
                    break;
                }
            }
        }
    }

    public float gerarMediaAluno(int idAluno) {
        List<AlunoModel> alunos = jsonManager.carregarDadosAlunos();
        if (alunos != null) {
            for (AlunoModel aluno : alunos) {
                if (aluno.getIdAluno() == idAluno) {
                    return aluno.calcularMedia();
                }
            }
        }
        return 0;
    }

    public void substituirNotaDoAluno(int idAluno, float novaNota) {
        List<AlunoModel> alunos = jsonManager.carregarDadosAlunos();
        if (alunos != null) {
            for (AlunoModel aluno : alunos) {
                if (aluno.getIdAluno() == idAluno) {
                    aluno.substituirNota(novaNota);
                    jsonManager.salvarDadosAlunos(alunos);
                    break;
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
}
