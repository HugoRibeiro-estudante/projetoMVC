package br.edu.iftm.professores.model;

public class Professor {
    private String nome;
    private Integer id;

    public Professor() {
    }

    public Professor(String nome, Integer id) {
        this.nome = nome;
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
