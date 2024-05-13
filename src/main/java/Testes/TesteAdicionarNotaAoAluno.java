package Testes;

import controller.AlunoController;
import model.AlunoModel;

import java.util.List;

public class TesteAdicionarNotaAoAluno {
    public static void main(String[] args) {
        AlunoController alunoController = new AlunoController();

        alunoController.criarAluno("João");

        alunoController.adicionarNotaAoAluno(1, 7.5f);
        alunoController.adicionarNotaAoAluno(1, 8.0f);
        alunoController.adicionarNotaAoAluno(1, 6.0f);

        alunoController.adicionarNotaAoAluno(1, 9.0f);

        List<AlunoModel> alunos = alunoController.buscarAlunos("João");
        for (AlunoModel aluno : alunos) {
            System.out.println("Aluno: " + aluno.getNome());
            System.out.println("Notas: " + aluno.getListaNotas());
            System.out.println("Média: " + aluno.getMedia());
        }
    }
}
