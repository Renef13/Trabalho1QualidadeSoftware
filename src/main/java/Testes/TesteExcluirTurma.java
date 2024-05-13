package Testes;

import controller.TurmaController;

public class TesteExcluirTurma {
    public static void main(String[] args) {
        TurmaController turmaController = new TurmaController();

        turmaController.criarTurma("Turma A");
        turmaController.criarTurma("Turma B");
        turmaController.criarTurma("Turma C");

        turmaController.excluirTurma("Turma B");

    }

}