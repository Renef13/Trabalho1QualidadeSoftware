package Testes;

import controller.TurmaController;
import model.TurmaModel;

import java.util.List;

public class TesteEditarNomeTurma {
    public static void main(String[] args) {
        TurmaController turmaController = new TurmaController();

        turmaController.criarTurma("Turma A");
        turmaController.criarTurma("Turma B");
        turmaController.criarTurma("Turma C");

        try {
            turmaController.editarNomeTurma("Turma D", "Turma C");
            System.out.println("Turma renomeada com sucesso.");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao renomear a turma: " + e.getMessage());
        }

    }
}
