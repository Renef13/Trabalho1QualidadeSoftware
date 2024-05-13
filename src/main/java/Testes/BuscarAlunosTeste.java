package Testes;

import controller.AlunoController;
import model.AlunoModel;

import java.util.List;

public class BuscarAlunosTeste {
    public static void main(String[] args) {

        AlunoController alunoController = new AlunoController();

        List<AlunoModel> alunosEncontrados = alunoController.buscarAlunos("Pedro Oliveira");

        if (!alunosEncontrados.isEmpty()) {

            System.out.println("Alunos encontrados com o nome 'Pedro Oliveira':");
            for (AlunoModel aluno : alunosEncontrados) {
                System.out.println("ID do aluno: " + aluno.getIdAluno());
                System.out.println("Nome do aluno: " + aluno.getNome());
                System.out.println("Lista de notas do aluno: " + aluno.getListaNotas());
                System.out.println("Média do aluno: " + aluno.getMedia());
                System.out.println();
            }
        } else {
            System.out.println("Nenhum aluno encontrado com o nome 'Pedro Oliveira'.");
        }
    }
}

