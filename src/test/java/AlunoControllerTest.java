import controller.AlunoController;
import model.AlunoModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class AlunoControllerTest {

    @Test
    public void testCriarAlunoComMesmoId() {

        AlunoController alunoController = new AlunoController();

        alunoController.criarAluno("João");
        alunoController.criarAluno("Maria");
        alunoController.criarAluno("Paula");
        alunoController.criarAluno("Pedro");
        alunoController.criarAluno("Fernanda");

        alunoController.criarAluno("João");

        List<AlunoModel> alunos = alunoController.buscarAlunos("João");

        int qtdAlunosComMesmoId = 0;
        int idAlunoParaVerificar = alunos.get(0).getIdAluno();
        for (AlunoModel aluno : alunos) {
            if (aluno.getIdAluno() == idAlunoParaVerificar) {
                qtdAlunosComMesmoId++;
            }
        }
        Assertions.assertEquals(1, qtdAlunosComMesmoId, "Alunos com IDs duplicados");
    }

}
