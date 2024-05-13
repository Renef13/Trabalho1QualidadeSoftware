package Testes;

import controller.AlunoController;

public class EditarNomeAlunoTeste {
    public static void main(String[] args) {

        AlunoController alunoController = new AlunoController();

        alunoController.editarNomeAluno(3, "Ana Clara");

    }
}
