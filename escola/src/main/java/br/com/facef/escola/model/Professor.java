package br.com.facef.escola.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "professor")
public class Professor implements Serializable {

    private static final long serialVersionID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String nome;

    @Column
    private int numeroRegistro;

    @Column
    private String email;

    @JsonIgnoreProperties({"professor"})
    @Transient
    private List<Materia> materias;

    public Professor() {}

    public Professor(int id, String nome, int numeroRegistro, String email, List<Materia> materias) {
        this.id = id;
        this.nome = nome;
        this.numeroRegistro = numeroRegistro;
        this.email = email;
        this.materias = materias;
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public String getNome() {return nome;}
    public void setNome (String nome) {this.nome = nome;}
    public int getNumeroRegistro() {return numeroRegistro;}
    public void setNumeroRegistro(int numeroRegistro) {this.numeroRegistro = numeroRegistro;}
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public List<Materia> getMaterias() {
        return materias;
    }

    public void setMaterias(List<Materia> materias) {
        this.materias = materias;
    }
}
