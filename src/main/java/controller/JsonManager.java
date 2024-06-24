package controller;

import com.google.gson.*;
import model.AlunoModel;
import model.TurmaModel;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class JsonManager {

    private final String turmasFilePath = "src/main/java/data/Turmas.json";
    private final String alunosFilePath = "src/main/java/data/Alunos.json";
    private final Gson gson;

    public JsonManager() {
        gson = new GsonBuilder().setPrettyPrinting().create();
        criarArquivosSeNaoExistem();
    }

    private void criarArquivosSeNaoExistem() {
        File turmasFile = new File(turmasFilePath);
        File alunosFile = new File(alunosFilePath);
        try {
            if (!turmasFile.exists()) {
                turmasFile.createNewFile();
                System.out.println("Arquivo de turmas criado em: " + turmasFilePath);
            } else {
                System.out.println("Arquivo de turmas já existe em: " + turmasFilePath);
            }
            if (!alunosFile.exists()) {
                alunosFile.createNewFile();
                System.out.println("Arquivo de alunos criado em: " + alunosFilePath);
            } else {
                System.out.println("Arquivo de alunos já existe em: " + alunosFilePath);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Erro ao criar arquivos: " + e.getMessage());
        }
    }

    public void salvarDadosTurmas(List<TurmaModel> turmas) {
        try (Reader reader = new FileReader(turmasFilePath)) {
            JsonElement jsonElement = JsonParser.parseReader(reader);
            JsonObject jsonObject;
            if (jsonElement.isJsonObject()) {
                jsonObject = jsonElement.getAsJsonObject();
            } else {
                jsonObject = new JsonObject();
            }
            JsonArray turmasArray = new JsonArray();
            for (TurmaModel turma : turmas) {
                JsonObject turmaObject = new JsonObject();
                turmaObject.addProperty("idTurma", turma.getIdTurma());
                turmaObject.addProperty("nome", turma.getNome());
                turmaObject.addProperty("qtdAlunos", turma.getQtdAlunos());
                turmaObject.addProperty("mediaTurma", turma.getMediaTurma());

                JsonArray alunosArray = new JsonArray();
                for (AlunoModel aluno : turma.getAlunos()) {
                    JsonObject alunoObject = new JsonObject();
                    alunoObject.addProperty("idAluno", aluno.getIdAluno());
                    alunoObject.addProperty("nome", aluno.getNome());
                    alunoObject.addProperty("media", aluno.getMedia());
                    alunosArray.add(alunoObject);
                }
                turmaObject.add("alunos", alunosArray);

                turmasArray.add(turmaObject);
            }

            jsonObject.add("turmas", turmasArray);

            try (Writer writer = new FileWriter(turmasFilePath)) {
                gson.toJson(jsonObject, writer);
                System.out.println("Dados das turmas salvos em " + turmasFilePath);
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("Erro ao salvar os dados das turmas: " + e.getMessage());
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Erro ao carregar o arquivo de turmas: " + e.getMessage());
        }
    }

    public void salvarDadosAlunos(List<AlunoModel> alunos) {
        JsonObject jsonObject = new JsonObject();
        JsonArray alunosArray = new JsonArray();
        for (AlunoModel aluno : alunos) {
            JsonObject alunoObject = new JsonObject();
            alunoObject.addProperty("idAluno", aluno.getIdAluno());
            alunoObject.addProperty("nome", aluno.getNome());

            JsonArray notasArray = new JsonArray();
            for (float nota : aluno.getListaNotas()) {
                notasArray.add(nota);
            }
            alunoObject.add("listaNotas", notasArray);

            alunoObject.addProperty("media", aluno.getMedia());

            alunosArray.add(alunoObject);
        }
        jsonObject.add("alunos", alunosArray);

        try (Writer writer = new FileWriter(alunosFilePath)) {
            gson.toJson(jsonObject, writer);
            System.out.println("Dados dos alunos salvos em " + alunosFilePath);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Erro ao salvar os dados dos alunos: " + e.getMessage());
        }
    }

    public List<AlunoModel> carregarDadosAlunos() {
        return carregarDadosGenerico(alunosFilePath, "alunos", this::parseAluno);
    }

    public List<TurmaModel> carregarDadosTurmas() {
        return carregarDadosGenerico(turmasFilePath, "turmas", this::parseTurma);
    }

    private <T> List<T> carregarDadosGenerico(String filePath, String chave, Function<JsonObject, T> parser) {
        List<T> dados = new ArrayList<>();

        try (Reader reader = new FileReader(filePath)) {
            JsonElement jsonElement = JsonParser.parseReader(reader);

            if (!jsonElement.isJsonObject()) {
                System.out.println("O conteúdo do arquivo não é um objeto JSON válido.");
                return dados;
            }

            JsonObject jsonObject = jsonElement.getAsJsonObject();

            if (!jsonObject.has(chave)) {
                System.out.println("Arquivo não contém a chave '" + chave + "'.");
                return dados;
            }

            JsonArray array = jsonObject.getAsJsonArray(chave);

            for (JsonElement element : array) {
                JsonObject obj = element.getAsJsonObject();
                T parsedObject = parser.apply(obj);
                dados.add(parsedObject);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dados;
    }

    private AlunoModel parseAluno(JsonObject alunoObject) {
        int idAluno = alunoObject.get("idAluno").getAsInt();
        String nome = alunoObject.get("nome").getAsString();
        List<Float> listaNotas = new ArrayList<>();
        JsonArray notasArray = alunoObject.getAsJsonArray("listaNotas");
        if (notasArray != null) {
            for (JsonElement notaElement : notasArray) {
                listaNotas.add(notaElement.getAsFloat());
            }
        }
        float media = alunoObject.get("media").getAsFloat();
        return new AlunoModel(idAluno, nome, listaNotas, media);
    }

    private TurmaModel parseTurma(JsonObject turmaObject) {
        int idTurma = turmaObject.get("idTurma").getAsInt();
        String nome = turmaObject.get("nome").getAsString();
        int qtdAlunos = turmaObject.get("qtdAlunos").getAsInt();
        float mediaTurma = turmaObject.get("mediaTurma").getAsFloat();

        List<AlunoModel> alunos = new ArrayList<>();
        JsonArray alunosArray = turmaObject.getAsJsonArray("alunos");
        if (alunosArray != null) {
            for (JsonElement alunoElement : alunosArray) {
                JsonObject alunoObj = alunoElement.getAsJsonObject();
                int idAluno = alunoObj.get("idAluno").getAsInt();
                String nomeAluno = alunoObj.get("nome").getAsString();
                alunos.add(new AlunoModel(idAluno, nomeAluno));
            }
        }
        return new TurmaModel(idTurma, nome, qtdAlunos, mediaTurma, alunos);
    }

}
