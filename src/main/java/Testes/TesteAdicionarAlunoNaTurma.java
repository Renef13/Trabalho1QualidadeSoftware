package Testes;

import controller.AlunoController;
import controller.TurmaController;
import model.AlunoModel;
public class TesteAdicionarAlunoNaTurma {

    public static void main(String[] args) {

        AlunoController alunoController = new AlunoController();

        alunoController.criarAluno("João Silva");
        alunoController.criarAluno("Maria Gomes");
        alunoController.criarAluno("Pedro Oliveira");

        TurmaController turmaController = new TurmaController();

        turmaController.criarTurma("Turma A");
        turmaController.criarTurma("Turma B");

        int idTurmaA = turmaController.buscarTurma("Turma A").getIdTurma();
        int idTurmaB = turmaController.buscarTurma("Turma B").getIdTurma();

        AlunoModel joao = new AlunoModel(1, "João Silva");
        AlunoModel maria = new AlunoModel(2, "Maria Gomes");
        AlunoModel pedro = new AlunoModel(3, "Pedro Oliveira");

        turmaController.adicionarAlunoNaTurma(idTurmaA, joao);
        turmaController.adicionarAlunoNaTurma(idTurmaA, maria);
        turmaController.adicionarAlunoNaTurma(idTurmaB, pedro);
    }
}

