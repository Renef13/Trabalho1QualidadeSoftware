package Testes;

import controller.TurmaController;

public class TesteCadastroTurma {
    public static void main(String[] args) {
        TurmaController turmaController = new TurmaController();

        turmaController.criarTurma("Turma Ingrid");

        System.out.println("Turma cadastrada com sucesso!");
    }
}
