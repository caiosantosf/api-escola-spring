package br.com.facef.escola.business.impl;

import br.com.facef.escola.model.Curso;
import br.com.facef.escola.business.CursoBusiness;
import br.com.facef.escola.model.Materia;
import br.com.facef.escola.model.Turma;
import br.com.facef.escola.repository.CursoRepository;
import br.com.facef.escola.repository.MateriaRepository;
import br.com.facef.escola.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CursoBusinessImpl implements CursoBusiness {

    private CursoRepository cursoRepository;
    private TurmaRepository turmaRepository;
    private MateriaRepository materiaRepository;

    @Autowired
    public CursoBusinessImpl(CursoRepository cursoRepository, TurmaRepository turmaRepository, MateriaRepository materiaRepository){
        this.cursoRepository = cursoRepository;
        this.turmaRepository = turmaRepository;
        this.materiaRepository = materiaRepository;
    }

    @Override
    public List<Curso> findAll(int pagina) {
        List<Curso> cursos = cursoRepository.findAll(PageRequest.of(pagina, 10)).getContent();
        for (Curso curso : cursos) {
            List<Turma> turmas = turmaRepository.findByCursoId(curso.getId());
            curso.setTurmas(turmas);
        }
        return cursos;
    }

    @Override
    public List<Curso> findByDescricaoContaining(String descricao, int pagina) {
        List<Curso> cursos = cursoRepository.findByDescricaoContaining(descricao, PageRequest.of(pagina, 10)).getContent();
        for (Curso curso : cursos) {
            List<Turma> turmas = turmaRepository.findByCursoId(curso.getId());
            curso.setTurmas(turmas);
        }
        return cursos;
    }

    @Override
    public Curso findById(int id) {
        Curso curso = cursoRepository.findById(id).get();
        List<Turma> turmas = turmaRepository.findByCursoId(curso.getId());
        curso.setTurmas(turmas);
        return curso;
    }

    @Override
    public void deleteById(int id) {
        cursoRepository.deleteById(id);
    }

    @Override
    public Curso save(int id, Curso cursoReq){
        Curso curso = new Curso();

        if (id != 0) {
            if (cursoRepository.existsById(id))
                curso.setId(id);
            else throw new NoSuchElementException();
        }

        List<Materia> materiasReq = cursoReq.getMaterias();

        if (materiasReq != null) {

            List<Materia> materias = new ArrayList<Materia>();

            for (Materia materia : materiasReq) {
                int idMateria = materia.getId();

                if (idMateria != 0) {
                    materia = materiaRepository.findById(idMateria).get();
                    materias.add(materia);
                }
            }

            curso.setMaterias(materias);
        }

        curso.setDescricao(cursoReq.getDescricao());

        return cursoRepository.save(curso);
    }
}
