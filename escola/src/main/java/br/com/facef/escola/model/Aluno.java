package br.com.facef.escola.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "aluno")
public class Aluno implements Serializable {

    private static final long serialVersionID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String nome;

    @Column
    private String email;

    @JsonIgnoreProperties({"alunos"})
    @ManyToMany()
    @PrimaryKeyJoinColumn()
    private List<Turma> turmas;

    public Aluno() {
    }

    public Aluno(int id, String nome, String email, List<Turma> turmas) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.turmas = turmas;
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Turma> getTurmas() { return turmas; }

    public void setTurmas(List<Turma> turma) {
        this.turmas = turma;
    }
}
