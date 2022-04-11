package br.edu.iftm.professores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class ProfessoresApplication implements CommandLineRunner {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public static void main(String[] args) {
		SpringApplication.run(ProfessoresApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		jdbcTemplate.execute("CREATE TABLE professor(id SERIAL, nome VARCHAR(255), PRIMARY KEY (id))");	
		jdbcTemplate.execute("CREATE TABLE disciplina(id SERIAL, id_professor int, nome VARCHAR(255), PRIMARY KEY (id),FOREIGN KEY (id_professor) REFERENCES professor(id));");
		jdbcTemplate.execute("CREATE TABLE aluno(id SERIAL, nome VARCHAR(255), email VARCHAR(50), cpf VARCHAR(11), PRIMARY KEY (id))");	
		jdbcTemplate.execute("CREATE TABLE matricula( id_aluno INT UNSIGNED NOT NULL,id_disciplina INT UNSIGNED NOT NULL, PRIMARY KEY(id_aluno,id_disciplina), CONSTRAINT matricula_aluno_fk FOREIGN KEY (id_aluno) REFERENCES aluno(id) ON DELETE CASCADE ON UPDATE CASCADE, CONSTRAINT matricula_disciplina_fk FOREIGN KEY(id_disciplina) REFERENCES disciplina(id) ON DELETE CASCADE ON UPDATE CASCADE);");

		jdbcTemplate.update("INSERT INTO professor(nome) VALUES (?)", "Edson Angoti Júnior");
		jdbcTemplate.update("INSERT INTO professor(nome) VALUES (?)", "Clarimundo Machado");
		jdbcTemplate.update("INSERT INTO professor(nome) VALUES (?)", "Rodrigo Cavanha");
		jdbcTemplate.update("INSERT INTO professor(nome) VALUES (?)", "Bruno Queiroz");


		jdbcTemplate.update("INSERT INTO aluno(id, nome, cpf, email) VALUES (?,?,?,?)", 1, "Hugo Ribeiro Alves", "01932900098", "hugo.ribeiro.alves1999@gmail.com");

		jdbcTemplate.update("INSERT INTO disciplina(id, id_professor,nome) VALUES (?,?,?)", 1, 1,"Sistema Web MVC SQL");
		jdbcTemplate.update("INSERT INTO disciplina(id, id_professor,nome) VALUES (?,?,?)", 2, 3,"Banco de dados");
		jdbcTemplate.update("INSERT INTO disciplina(id, id_professor,nome) VALUES (?,?,?)", 3, 2,"Programação OO");
		jdbcTemplate.update("INSERT INTO disciplina(id, id_professor,nome) VALUES (?,?,?)", 4, 4,"Algoritmo e Programação");


	}

}
