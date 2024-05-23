import controller.AlunoController;
import controller.TurmaController;
import model.AlunoModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TurmaTest {
    @Test
    public void testRegistroSalaNomeUnico() {
        TurmaController turmaController = new TurmaController();

        turmaController.criarTurma("Qualidade de Software");

        String mensagemErro = "";
        try {
            turmaController.criarTurma("Qualidade de Software");
        } catch (RuntimeException e) {
            mensagemErro = e.getMessage();
        }

        assertEquals("O nome da sala já existe! Por favor, insira um nome único para a sala.", mensagemErro,
                "A mensagem de erro não corresponde à esperada.");
    }
    @Test
    public void testLimiteDeSala() {
        AlunoController alunoController = new AlunoController();
        TurmaController turmaController = new TurmaController();

        turmaController.criarTurma("Qualidade de Software");

        for (int i = 0; i < 5; i++) {
            alunoController.criarAluno("Aluno " + (i + 1));
            AlunoModel aluno = alunoController.buscarAlunos("Aluno " + (i + 1)).getFirst();
            turmaController.adicionarAlunoNaTurma("Qualidade de Software", aluno);
        }

        alunoController.criarAluno("Aluno 6");
        AlunoModel aluno6 = alunoController.buscarAlunos("Aluno 6").getFirst();

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            turmaController.adicionarAlunoNaTurma("Qualidade de Software", aluno6);
        });
        assertEquals("A sala não pode ter mais de 5 discentes.", exception.getMessage());
    }

}

