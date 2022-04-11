package br.edu.iftm.professores.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.edu.iftm.professores.model.Aluno;


@Repository
public class AlunoRepository {

    @Autowired
    JdbcTemplate jdbc;

    public List<Aluno> buscaTodos() {
        String consulta = "SELECT * FROM Aluno;";
        return jdbc.query(consulta,
                (r, numeroDaLinha) -> new Aluno(r.getInt("id"), r.getString("nome"), r.getString("email"), r.getString("cpf")));
    }

    public int gravaAluno(Aluno aluno){
        String consulta = "insert into aluno(nome, email, cpf) values(?, ?, ?)";
        return jdbc.update(consulta, aluno.getNome(), aluno.getEmail(), aluno.getCpf());
    }

    public int excluirAluno(Integer id){
       
        String consulta = "delete from aluno where id = ?";
        return jdbc.update(consulta, id);
    }

    public Aluno buscaPorId(Integer id) {
        return jdbc.queryForObject(
                "select * from aluno where id = ?",
                (resultSet, rowNum) -> { return new Aluno(resultSet.getInt("id"), resultSet.getString("nome"),resultSet.getString("email"), resultSet.getString("cpf")); },
                id);
    }

    public int atualizaAluno(Aluno aluno){
        String consulta = "update aluno set nome = ?, email = ?, cpf = ? where id = ?";
        return jdbc.update(consulta, aluno.getNome(), aluno.getEmail(), aluno.getCpf(), aluno.getId());
    }
}
