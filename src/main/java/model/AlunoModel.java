package model;

import java.util.ArrayList;
import java.util.List;

public class AlunoModel {
    private int idAluno;
    private String nome;
    private List<Float> listaNotas;
    private float media;

    public AlunoModel(int idAluno, String nome) {
        this.idAluno = idAluno;
        this.nome = nome;
        this.listaNotas = new ArrayList<>();
        this.media = 0;
    }

    public AlunoModel(int idAluno, String nome, List<Float> listaNotas, float media) {
        this.idAluno = idAluno;
        this.nome = nome;
        this.listaNotas = listaNotas;
        this.media = media;
    }

    public int getIdAluno() {
        return idAluno;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Float> getListaNotas() {
        return listaNotas;
    }

    public float getMedia() {
        return media;
    }

    public void setMedia(float media) {
        this.media = media;
    }

    public float calcularMedia() {
        float soma = 0;
        for (float nota : listaNotas) {
            soma += nota;
        }
        return soma / listaNotas.size();
    }

    public void adicionarNota(float nota) {
        listaNotas.add(nota);
        media = calcularMedia();
    }



}

