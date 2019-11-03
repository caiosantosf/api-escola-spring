package br.com.facef.escola.business.impl;

import br.com.facef.escola.model.Materia;
import br.com.facef.escola.model.Professor;
import br.com.facef.escola.business.ProfessorBusiness;
import br.com.facef.escola.repository.MateriaRepository;
import br.com.facef.escola.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProfessorBusinessImpl implements ProfessorBusiness {

    private ProfessorRepository professorRepository;
    private MateriaRepository materiaRepository;

    @Autowired
    public ProfessorBusinessImpl(ProfessorRepository professorRepository, MateriaRepository materiaRepository){
        this.professorRepository = professorRepository;
        this.materiaRepository = materiaRepository;
    }

    @Override
    public List<Professor> findAll(int pagina) {
        List<Professor> professores = professorRepository.findAll(PageRequest.of(pagina, 10)).getContent();
        for (Professor professor : professores) {
            List<Materia> materias = materiaRepository.findByProfessorId(professor.getId());
            professor.setMaterias(materias);
        }
        return professores;
    }

    @Override
    public List<Professor> findByNomeContaining(String nome, int pagina) {
        List<Professor> professores = professorRepository.findByNomeContaining(nome, PageRequest.of(pagina, 10)).getContent();
        for (Professor professor : professores) {
            List<Materia> materias = materiaRepository.findByProfessorId(professor.getId());
            professor.setMaterias(materias);
        }
        return professores;
    }

    @Override
    public Professor findById(int id) {
        Professor professor = professorRepository.findById(id).get();
        List<Materia> materias = materiaRepository.findByProfessorId(professor.getId());
        professor.setMaterias(materias);
        return professor;
    }

    @Override
    public void deleteById(int id) {
        professorRepository.deleteById(id);
    }

    @Override
    public Professor save(int id, Professor professorReq) throws Exception {
        Professor professor = new Professor();

        if (id != 0) {
            if (professorRepository.existsById(id))
                professor.setId(id);
            else throw new NoSuchElementException();
        }

        professor.setNome(professorReq.getNome());
        professor.setEmail(professorReq.getEmail());
        professor.setNumeroRegistro(professorReq.getNumeroRegistro());

        return professorRepository.save(professor);
    }
}
