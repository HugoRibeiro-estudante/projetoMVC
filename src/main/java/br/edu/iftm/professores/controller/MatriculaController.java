package br.edu.iftm.professores.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.edu.iftm.professores.model.Disciplina;
import br.edu.iftm.professores.model.Matricula;
import br.edu.iftm.professores.repository.AlunoRepository;
import br.edu.iftm.professores.repository.DisciplinaRepository;
import br.edu.iftm.professores.repository.MatriculaRepository;

@Controller
public class MatriculaController {
    
    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @Autowired
    private MatriculaRepository matriculaRepository;

    @Autowired
    private AlunoRepository alunoRepository;


    @GetMapping("matricular-aluno")
    public String matricula(@RequestParam(name = "id", required = true) Integer cod, Model modelo){
        List<Disciplina> lista = disciplinaRepository.buscaTodos();
        modelo.addAttribute("lista", lista);
        modelo.addAttribute("aluno", alunoRepository.buscaPorId(cod));
        modelo.addAttribute("matricula", matriculaRepository.buscaporAlunoId(cod));
        return "matricula";
    }


    @PostMapping("matricular-aluno")
    public String efetuarMatricula(Matricula matricula){
        matriculaRepository.grava(matricula);
        return "redirect:/alunos";
    }

}
