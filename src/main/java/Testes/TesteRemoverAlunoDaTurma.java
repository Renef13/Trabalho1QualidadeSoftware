package Testes;

import controller.AlunoController;
import controller.TurmaController;

public class TesteRemoverAlunoDaTurma {

    public static void main(String[] args) {

        AlunoController alunoController = new AlunoController();

        alunoController.criarAluno("João Silva");
        alunoController.criarAluno("Maria Gomes");
        alunoController.criarAluno("Pedro Oliveira");

        TurmaController turmaController = new TurmaController();

        turmaController.criarTurma("Turma A");

        int idTurmaA = turmaController.buscarTurma("Turma A").getIdTurma();

        turmaController.adicionarAlunoNaTurma(idTurmaA, alunoController.buscarAlunos("João Silva").get(0));
        turmaController.adicionarAlunoNaTurma(idTurmaA, alunoController.buscarAlunos("Maria Gomes").get(0));
        turmaController.adicionarAlunoNaTurma(idTurmaA, alunoController.buscarAlunos("Pedro Oliveira").get(0));

        turmaController.removerAlunoDaTurma(idTurmaA, 2);
    }
}
