package br.edu.iftm.professores.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.edu.iftm.professores.model.Disciplina;
import br.edu.iftm.professores.repository.DisciplinaRepository;
import br.edu.iftm.professores.repository.ProfessorRepository;


@Controller
public class DisciplinaControle {

	@Autowired
	DisciplinaRepository repoDisciplina;
	
	@Autowired
	ProfessorRepository repoProfessor;

	@GetMapping("/disciplinas")
	public String disciplinasTabela(Model modelo) {
		List<Disciplina> lista = repoDisciplina.buscaTodos();
		modelo.addAttribute("lista", lista);
		return "disciplinas";
	}

	@GetMapping("/novo-disciplina")
	public String exibeForm(Model model) {
		model.addAttribute("disciplina", new Disciplina());
		model.addAttribute("lista", repoProfessor.buscaTodosProfessores());
		return "disciplina-form";
	}

	@PostMapping("/novo-disciplina")
	public String processaForm(Disciplina disciplina) {
		repoDisciplina.grava(disciplina);
		return "redirect:/disciplinas";
	}

	@GetMapping("/editar-disciplina")
	public String exibeFormEdit(Model model, @RequestParam(name = "id", required = false) Integer cod) {
		Disciplina disciplina = repoDisciplina.buscaPorId(cod);
		model.addAttribute("lista", repoProfessor.buscaTodosProfessores());
		model.addAttribute("disciplina", disciplina);
		return "disciplina-form";
	}

	@PostMapping("/editar-disciplina")
	public String processaoFormEdit(Disciplina disciplina) {
		repoDisciplina.atualiza(disciplina);
		return "redirect:/disciplinas";
	}

	@GetMapping("/excluir-disciplina")
	public String excluir(@RequestParam(name = "id", required = true) Integer cod) {
		repoDisciplina.excluir(cod);
		return "redirect:/disciplinas";
	}
}
