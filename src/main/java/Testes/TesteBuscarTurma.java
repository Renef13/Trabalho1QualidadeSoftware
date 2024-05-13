package Testes;

import controller.TurmaController;
import model.TurmaModel;

public class TesteBuscarTurma {
    public static void main(String[] args) {
        TurmaController turmaController = new TurmaController();

        turmaController.criarTurma("Turma S");

        String nomeTurma = "TURMA S";

        TurmaModel turmaEncontrada = turmaController.buscarTurma(nomeTurma);

        if (turmaEncontrada != null) {
            System.out.println("Turma encontrada: " + turmaEncontrada.getNome());
            System.out.println("ID da turma: " + turmaEncontrada.getIdTurma());

        } else {
            System.out.println("Turma não encontrada com o nome: " + nomeTurma);
        }
    }
}
