package Testes;

import controller.AlunoController;

public class TesteCadastroAluno {
    public static void main(String[] args) {
        AlunoController alunoController = new AlunoController();

        alunoController.criarAluno("Paulo");
        alunoController.criarAluno("Maria");
        alunoController.criarAluno("Pedro");
    }
}
