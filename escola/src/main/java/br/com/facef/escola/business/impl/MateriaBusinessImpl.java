package br.com.facef.escola.business.impl;

import br.com.facef.escola.model.Curso;
import br.com.facef.escola.model.Materia;
import br.com.facef.escola.business.MateriaBusiness;
import br.com.facef.escola.model.Professor;
import br.com.facef.escola.repository.CursoRepository;
import br.com.facef.escola.repository.MateriaRepository;
import br.com.facef.escola.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class MateriaBusinessImpl implements MateriaBusiness {

    private MateriaRepository materiaRepository;
    private CursoRepository cursoRepository;
    private ProfessorRepository professorRepository;

    @Autowired
    public MateriaBusinessImpl(MateriaRepository materiaRepository, CursoRepository cursoRepository, ProfessorRepository professorRepository){
        this.materiaRepository = materiaRepository;
        this.cursoRepository = cursoRepository;
        this.professorRepository = professorRepository;
    }

    @Override
    public List<Materia> findAll(int pagina) {
        List<Materia> materias = materiaRepository.findAll(PageRequest.of(pagina, 10)).getContent();
        for (Materia materia : materias) {
            List<Curso> cursos = cursoRepository.findByMateriaId(materia.getId());
            materia.setCursos(cursos);
        }
        return materias;
    }

    @Override
    public List<Materia> findByDescricaoContaining(String descricao, int pagina) {
        List<Materia> materias = materiaRepository.findByDescricaoContaining(descricao, PageRequest.of(pagina, 10)).getContent();
        for (Materia materia : materias) {
            List<Curso> cursos = cursoRepository.findByMateriaId(materia.getId());
            materia.setCursos(cursos);
        }
        return materias;
    }

    @Override
    public Materia findById(int id) {
        Materia materia = materiaRepository.findById(id).get();
        materia.setCursos(cursoRepository.findByMateriaId(id));
        return materia;
    }

    @Override
    public void deleteById(int id) {

        materiaRepository.deleteById(id);
    }

    @Override
    public Materia save(int id, Materia materiaReq) {
        Materia materia = new Materia();

        if (id != 0) {
            if (materiaRepository.existsById(id))
                materia.setId(id);
            else throw new NoSuchElementException();
        }

        materia.setDescricao(materiaReq.getDescricao());
        materia.setQuantidadeAulas(materiaReq.getQuantidadeAulas());

        if (materiaReq.getProfessor() != null) {
            Professor professor = materiaReq.getProfessor();
            int idProfessor = professor.getId();

            if (idProfessor != 0) {
                professor = professorRepository.findById(idProfessor).get();
                materia.setProfessor(professor);
            }
        }

        return materiaRepository.save(materia);
    }
}
