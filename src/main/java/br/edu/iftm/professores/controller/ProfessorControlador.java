package br.edu.iftm.professores.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import br.edu.iftm.professores.model.Professor;
import br.edu.iftm.professores.repository.ProfessorRepository;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProfessorControlador {

	@Autowired
	ProfessorRepository repo;

	@GetMapping("/professores")
	public String disciplinasTabela(Model modelo) {
		List<Professor> lista = repo.buscaTodosProfessores();
		modelo.addAttribute("professores", lista);
		return "professores";
	}

	@GetMapping(value = "/novo-professor")
	public String exibeForm(Model modelo) {
		modelo.addAttribute("professor", new Professor());
		return "form-professor";
	}

	@PostMapping(value = "/novo-professor")
	public String processaForm(Professor professor) {
		if (professor.getId() == null) {
			repo.gravaProfessor(professor);
		} else {
			repo.atualizaProfessor(professor);
		}
		return "redirect:/professores";
	}

	@GetMapping(value = "/editar-professor")
	public String editarProfessor(@RequestParam(name = "id", required = true) Integer cod, Model modelo) {
		modelo.addAttribute("professor", repo.buscaPorId(cod));
		return "form-professor";
	}

	@GetMapping(value = "/excluir-professor")
	public String excluirProfessor(@RequestParam(name = "id", required = true) Integer cod) {
		repo.excluirProfessor(cod);
		return "redirect:/professores";
	}
}
