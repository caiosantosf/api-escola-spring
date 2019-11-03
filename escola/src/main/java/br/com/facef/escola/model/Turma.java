package br.com.facef.escola.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "turma")
public class Turma implements Serializable {

    private static final long serialVersionID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String descricao;

    @JsonIgnoreProperties({"turmas"})
    @Transient
    private List<Aluno> alunos;

    @JsonIgnoreProperties({"turmas", "materias"})
    @ManyToOne()
    @PrimaryKeyJoinColumn()
    private Curso curso;

    public Turma() {}

    public Turma(int id, String descricao, List<Aluno> alunos) {
        this.id = id;
        this.descricao = descricao;
        this.alunos = alunos;
    }

    public static long getSerialVersionID() {
        return serialVersionID;
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

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    public Curso getCurso() { return curso; }

    public void setCurso(Curso curso) { this.curso = curso; }
}
