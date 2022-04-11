package br.edu.iftm.professores.model;

import java.util.List;

public class Matricula {
    private List<Integer> disciplinas;
    private Integer idAluno;

    public Matricula(){
    }

        public Matricula(List<Integer> disciplinas, Integer idAluno){

            this.disciplinas = disciplinas;
            this.idAluno = idAluno;

        } 

        public Integer getIdAluno(){
            return this.idAluno;
        }
    
        public void setIdAluno(Integer idAluno){
            this.idAluno = idAluno;
        }

        public List<Integer> getDisciplinas(){
            return this.disciplinas;
        }

        public void setDisciplinas(List<Integer> disciplinas){
            this.disciplinas = disciplinas;
        }

    }

