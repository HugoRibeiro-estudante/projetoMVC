package br.edu.iftm.professores.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import br.edu.iftm.professores.model.Aluno;

import br.edu.iftm.professores.repository.AlunoRepository;
import br.edu.iftm.professores.repository.DisciplinaRepository;
import br.edu.iftm.professores.repository.MatriculaRepository;

import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AlunoController{

	@Autowired
	AlunoRepository repo;

	@Autowired
	DisciplinaRepository disc;

	@Autowired
	MatriculaRepository mat;

	@GetMapping("/alunos")
	public String disciplinasTabela(Model modelo) {
		List<Aluno> lista = repo.buscaTodos();
		modelo.addAttribute("alunos", lista);
		modelo.addAttribute("matriculas", mat.buscaMatriculas());
		modelo.addAttribute("disciplinas", disc.buscaTodos());
		return "alunos";
	}

	@GetMapping(value = "/novo-aluno")
	public String exibeForm(Model modelo) {
		modelo.addAttribute("aluno", new Aluno());
		return "form-aluno";
	}

	@PostMapping(value = "/novo-aluno")
	public String processaForm(Aluno aluno) {
		if (aluno.getId() == null) {
			repo.gravaAluno(aluno);
		} else {
			repo.atualizaAluno(aluno);
		}
		return "redirect:/alunos";
	}

	@GetMapping(value = "/editar-aluno")
	public String editarAluno(@RequestParam(name = "id", required = true) Integer cod, Model modelo) {
		modelo.addAttribute("aluno", repo.buscaPorId(cod));
		return "form-aluno";
	}

	@GetMapping(value = "/excluir-aluno")
	public String excluirAluno(@RequestParam(name = "id", required = true) Integer cod) {
		repo.excluirAluno(cod);
		return "redirect:/alunos";
	}

	@GetMapping("/matricular")
	public String matricular(@RequestParam(name = "id", required = true) Integer cod, Model modelo) {
		

		return "matricular";
	}

}
