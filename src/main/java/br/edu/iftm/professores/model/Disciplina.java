package br.edu.iftm.professores.model;

public class Disciplina {
	private Integer id;
	private String nome;
	private Professor professor;

	public Disciplina() {
		super(); 
	}

	public Disciplina(Integer id, String nome, Professor professor) {
		this.id = id;
		this.nome = nome;
		this.professor = professor;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Professor getProfessor() {
		return this.professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

}
