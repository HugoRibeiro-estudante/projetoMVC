package br.edu.iftm.professores.model;

public class Aluno {
    private String nome, email, cpf;
    private Integer id;

    public Aluno() {
    }

    public Aluno(Integer id, String nome, String email, String cpf) {
        
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        
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

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

}
