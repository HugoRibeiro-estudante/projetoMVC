package br.edu.iftm.professores.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.edu.iftm.professores.model.Disciplina;
import br.edu.iftm.professores.model.Professor;

@Repository
public class DisciplinaRepository {

    @Autowired
    JdbcTemplate jdbc;

    public List<Disciplina> buscaTodos() {
        String consulta = "select d.nome as disciplina_nome, d.id as disciplina_id, "
                + "d.id_professor as disciplina_professor_id, p.nome as professor_nome, p.id as professor_id "
                + "from professor p, disciplina d where d.id_professor=p.id;";
        return jdbc.query(consulta, 
                (res, numeroDaLinha) -> new Disciplina(
                        res.getInt("disciplina_id"), 
                        res.getString("disciplina_nome"),
                        new Professor(res.getString("professor_nome"), res.getInt("professor_id"))));
    }

    public int grava(Disciplina disciplina) {
        String consulta = "insert into disciplina" + "(nome,id_professor) values (?,?)";
        return jdbc.update(consulta, disciplina.getNome(), disciplina.getProfessor().getId());
    }

    public int excluir(Integer id) {
        String consulta = "delete from disciplina where id = ?";
        return jdbc.update(consulta, id);
    }

    public Disciplina buscaPorId(Integer id) {
        return jdbc.queryForObject(
                "select d.nome as disciplina_nome, d.id as disciplina_id, "
                        + "d.id_professor as disciplina_professor_id, p.nome as professor_nome, p.id as professor_id "
                        + "from professor p, disciplina d where d.id_professor=p.id and d.id=?",
                (res, rowNum) -> {
                    return new Disciplina(id,res.getString("disciplina_nome"),  
                    new Professor(res.getString("professor_nome"), res.getInt("professor_id")));
                }, id);
    }

    public int atualiza(Disciplina disciplina) {
        String consulta = "update disciplina set nome=?,id_professor=?" + " where id = ?";
        return jdbc.update(consulta, disciplina.getNome(), disciplina.getProfessor().getId(),disciplina.getId());
    }
}
