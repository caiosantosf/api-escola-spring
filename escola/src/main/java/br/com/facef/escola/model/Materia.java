package br.com.facef.escola.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "materia")
public class Materia implements Serializable {

    private static final long serialVersionID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String descricao;

    @Column
    private int quantidadeAulas;

    @JsonIgnoreProperties({"materias"})
    @ManyToOne()
    @PrimaryKeyJoinColumn()
    private Professor professor;

    @JsonIgnoreProperties({"materias"})
    @Transient
    private List<Curso> cursos;

    public Materia() {}

    public Materia(int id, String descricao, int quantidadeAulas, Professor professor, List<Curso> cursos) {
        this.id = id;
        this.descricao = descricao;
        this.quantidadeAulas = quantidadeAulas;
        this.professor = professor;
        this.cursos = cursos;
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

    public int getQuantidadeAulas() {
        return quantidadeAulas;
    }

    public void setQuantidadeAulas(int quantidadeAulas) {
        this.quantidadeAulas = quantidadeAulas;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }
}
