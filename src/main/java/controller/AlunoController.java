package controller;

import model.AlunoModel;
import model.TurmaModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AlunoController {
    private final JsonManager jsonManager;

    public AlunoController() {
        this.jsonManager = new JsonManager();
    }

    public AlunoModel criarAluno(String nome) {
        List<AlunoModel> alunos = jsonManager.carregarDadosAlunos();
        int idAluno = alunos != null ? alunos.size() + 1 : 1;
        AlunoModel novoAluno = new AlunoModel(idAluno, nome);
        alunos.add(novoAluno);
        jsonManager.salvarDadosAlunos(alunos);
        return novoAluno;
    }

    public int excluirAluno(int idAluno) {
        int removed = 0;
        List<AlunoModel> alunos = jsonManager.carregarDadosAlunos();
        if (alunos != null) {
            if (alunos.removeIf(aluno -> aluno.getIdAluno() == idAluno)) {
                removed = 1;
            }
            jsonManager.salvarDadosAlunos(alunos);

            List<TurmaModel> turmas = jsonManager.carregarDadosTurmas();
            if (turmas != null) {
                for (TurmaModel turma : turmas) {
                    turma.removerAluno(idAluno);
                }
                jsonManager.salvarDadosTurmas(turmas);
            }
        }
        return removed;
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

    public AlunoModel buscarAlunosPorId(int idAluno) {
        List<AlunoModel> alunos = jsonManager.carregarDadosAlunos();
        AlunoModel alunoEncontrado = null;
        if (alunos != null) {
            for (AlunoModel aluno : alunos) {
                if (aluno.getIdAluno() == idAluno) {
                    alunoEncontrado = aluno;
                }
            }
        }
        return alunoEncontrado;
    }

    public void adicionarNotaAoAluno(int idAluno, float nota) {
        AlunoModel aluno = buscarAlunosPorId(idAluno);

        if (aluno == null) {
            System.out.println("Aluno não encontrado.");
            return;
        }

        adicionarNota(aluno, nota);
        salvarAlunoNoJson(aluno);
    }

    private void adicionarNota(AlunoModel aluno, float nota) {
        if (aluno.getListaNotas() == null) {
            adicionarPrimeiraNota(aluno, nota);
        } else if (aluno.getListaNotas().size() >= aluno.getNUMAXNOTAS()) {
            adicionarNotaComLimite(aluno, nota);
        } else {
            adicionarNotaSemLimite(aluno, nota);
        }
    }

    private void adicionarPrimeiraNota(AlunoModel aluno, float nota) {
        aluno.adicionarNota(nota);
        System.out.println("Nota adicionada com sucesso.");
    }

    private void adicionarNotaComLimite(AlunoModel aluno, float nota) {
        if (aluno.calcularMedia() >= 7) {
            System.out.println("O aluno já possui três notas e sua média é maior ou igual a 7.0. Não é possível adicionar mais notas.");
        } else {
            float menorNota = Collections.min(aluno.getListaNotas());
            if (nota > menorNota) {
                substituirMenorNota(aluno, menorNota, nota);
            } else {
                System.out.println("Nota não adicionada.");
            }
        }
    }

    private void adicionarNotaSemLimite(AlunoModel aluno, float nota) {
        aluno.adicionarNota(nota);
        if (aluno.getListaNotas().size() == aluno.getNUMAXNOTAS()) {
            float novaMedia = aluno.calcularMedia();
            System.out.println("Nova média calculada: " + novaMedia);
        } else {
            System.out.println("Nota adicionada com sucesso.");
        }
    }

    private void substituirMenorNota(AlunoModel aluno, float menorNota, float novaNota) {
        aluno.getListaNotas().remove(menorNota);
        aluno.getListaNotas().add(novaNota);
        aluno.setMedia(aluno.calcularMedia());
        System.out.println("Nota adicionada com sucesso.");
    }


    private void salvarAlunoNoJson(AlunoModel aluno) {
        List<AlunoModel> alunos = jsonManager.carregarDadosAlunos();
        if (alunos != null) {
            for (int i = 0; i < alunos.size(); i++) {
                if (alunos.get(i).getIdAluno() == aluno.getIdAluno()) {
                    alunos.set(i, aluno);
                    break;
                }
            }
            jsonManager.salvarDadosAlunos(alunos);
        }
    }

}
