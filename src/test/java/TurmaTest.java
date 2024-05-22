import controller.TurmaController;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

}
