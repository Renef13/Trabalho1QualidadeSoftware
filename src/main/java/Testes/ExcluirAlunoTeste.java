package Testes;

import controller.AlunoController;

public class ExcluirAlunoTeste {
    public static void main(String[] args) {

        AlunoController alunoController = new AlunoController();

        alunoController.excluirAluno(4);

        System.out.println("Aluno excluído com sucesso!");
    }
}
