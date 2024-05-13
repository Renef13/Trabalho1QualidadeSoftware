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


    public AlunoModel() {}


    public int getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(int idAluno) {
        this.idAluno = idAluno;
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

    public void setListaNotas(List<Float> listaNotas) {
        this.listaNotas = listaNotas;
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

    public void substituirNota(float novaNota) {
        if (novaNota > 0) {
            float menorNota = Float.MAX_VALUE;
            int indiceMenorNota = -1;
            for (int i = 0; i < listaNotas.size(); i++) {
                if (listaNotas.get(i) < menorNota) {
                    menorNota = listaNotas.get(i);
                    indiceMenorNota = i;
                }
            }
            if (novaNota > menorNota) {
                listaNotas.set(indiceMenorNota, novaNota);
                media = calcularMedia();
            }
        }
    }
}

