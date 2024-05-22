import controller.AlunoController;
import controller.TurmaController;
import model.AlunoModel;
import model.TurmaModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculoMediaTest {

    @Test
    public void testCalcularMediaSimples() {
        AlunoController alunoController = new AlunoController();
        TurmaController turmaController = new TurmaController();

        alunoController.criarAluno("Ingrid Coelho");
        AlunoModel ingrid = alunoController.buscarAlunos("Ingrid Coelho").getFirst();

        turmaController.criarTurma("Qualidade de Software");
        turmaController.adicionarAlunoNaTurma("Qualidade de Software", ingrid);

        float[] notas = { 8f, 8f, 7f };
        float media = (notas[0] + notas[1] + notas[2]) / notas.length;
        alunoController.adicionarNotaAoAluno(ingrid.getIdAluno(), notas[0]);
        alunoController.adicionarNotaAoAluno(ingrid.getIdAluno(), notas[1]);
        alunoController.adicionarNotaAoAluno(ingrid.getIdAluno(), notas[2]);

        ingrid = alunoController.buscarAlunos("Ingrid Coelho").getFirst();
        float mediaIngrid = ingrid.getMedia();

        System.out.println("ID do Aluno: " + ingrid.getIdAluno());
        System.out.println("Nome do Aluno: " + ingrid.getNome());
        System.out.println("Notas do Aluno: " + ingrid.getListaNotas());
        System.out.println("Média do Aluno: " + mediaIngrid);

        assertEquals(media, mediaIngrid, "A média calculada não está correta.");
    }
    @Test
    public void testCalcularMediaNova() {
        AlunoController alunoController = new AlunoController();
        TurmaController turmaController = new TurmaController();

        alunoController.criarAluno("Thiago Amaral");
        AlunoModel thiago = alunoController.buscarAlunos("Thiago Amaral").getFirst();

        turmaController.criarTurma("Qualidade de Software");
        turmaController.adicionarAlunoNaTurma("Qualidade de Software", thiago);

        float[] notas = { 5f, 5f, 5f, 10 };
        float media = (notas[0] + notas[1] + notas[3]) / 3;
        alunoController.adicionarNotaAoAluno(thiago.getIdAluno(), notas[0]);
        alunoController.adicionarNotaAoAluno(thiago.getIdAluno(), notas[1]);
        alunoController.adicionarNotaAoAluno(thiago.getIdAluno(), notas[2]);
        alunoController.adicionarNotaAoAluno(thiago.getIdAluno(), notas[3]);

        thiago = alunoController.buscarAlunos("Thiago Amaral").getFirst();
        float mediaThiago = thiago.getMedia();

        System.out.println("ID do Aluno: " + thiago.getIdAluno());
        System.out.println("Nome do Aluno: " + thiago.getNome());
        System.out.println("Notas do Aluno: " + thiago.getListaNotas());
        System.out.println("Média do Aluno: " + mediaThiago);

        assertEquals(media, mediaThiago, "A média calculada não está correta.");
    }


}
