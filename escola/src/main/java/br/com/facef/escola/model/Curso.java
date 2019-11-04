package br.com.facef.escola.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "curso")
public class Curso implements Serializable {

    private static final long serialVersionID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String descricao;

    @JsonIgnoreProperties({"curso","alunos"})
    @Transient
    private List<Turma> turmas;

    @JsonIgnoreProperties({"cursos"})
    @OneToMany
    @PrimaryKeyJoinColumn
    private List<Materia> materias;

    public Curso() {}

    public Curso(int id, String descricao, List<Turma> turmas, List<Materia> materias) {
        this.id = id;
        this.descricao = descricao;
        this.turmas = turmas;
        this.materias = materias;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Turma> getTurmas() { return turmas; }

    public void setTurmas(List<Turma> turmas) { this.turmas = turmas; }

    public List<Materia> getMaterias() {
        return materias;
    }

    public void setMaterias(List<Materia> materias) {
        this.materias = materias;
    }
}
