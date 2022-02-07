package br.edu.iftm.professores.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.edu.iftm.professores.model.Professor;

@Repository
public class ProfessorRepository {

    @Autowired
    JdbcTemplate jdbc;

    public List<Professor> buscaTodosProfessores() {
        String consulta = "SELECT * FROM PROFESSOR;";
        return jdbc.query(consulta,
                (resultados, numeroDaLinha) -> new Professor(resultados.getString("nome"), resultados.getInt("id")));
    }

    public int gravaProfessor(Professor professor){
        String consulta = "insert into professor(nome) values(?)";
        return jdbc.update(consulta, professor.getNome());
    }

    public int excluirProfessor(Integer id){
        System.out.println(" -------------> id = " + id);
        String consulta = "delete from professor where id = ?";
        return jdbc.update(consulta, id);
    }

    public Professor buscaPorId(Integer id) {
        return jdbc.queryForObject(
                "select * from professor where id = ?",
                (resultSet, rowNum) -> { return new Professor(resultSet.getString("nome"),resultSet.getInt("id")); },
                id);
    }

    public int atualizaProfessor(Professor professor){
        String consulta = "update professor set nome = ? where id = ?";
        return jdbc.update(consulta, professor.getNome(), professor.getId());
    }
}
