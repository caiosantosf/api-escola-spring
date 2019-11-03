package br.com.facef.escola.business.impl;

import br.com.facef.escola.model.Aluno;
import br.com.facef.escola.model.Curso;
import br.com.facef.escola.model.Turma;
import br.com.facef.escola.business.TurmaBusiness;
import br.com.facef.escola.repository.AlunoRepository;
import br.com.facef.escola.repository.CursoRepository;
import br.com.facef.escola.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TurmaBusinessImpl implements TurmaBusiness {

    private AlunoRepository alunoRepository;
    private TurmaRepository turmaRepository;
    private CursoRepository cursoRepository;

    @Autowired
    public TurmaBusinessImpl(AlunoRepository alunoRepository, TurmaRepository turmaRepository, CursoRepository cursoRepository){
        this.alunoRepository = alunoRepository;
        this.turmaRepository = turmaRepository;
        this.cursoRepository = cursoRepository;
    }

    @Override
    public List<Turma> findAll(int pagina) {
        List<Turma> turmas = turmaRepository.findAll(PageRequest.of(pagina, 10)).getContent();
        for (Turma turma : turmas) {
            List<Aluno> alunos = alunoRepository.findByTurmaId(turma.getId());
            turma.setAlunos(alunos);
        }
        return turmas;
    }

    @Override
    public List<Turma> findByDescricaoContaining(String descricao, int pagina) {
        List<Turma> turmas = turmaRepository.findByDescricaoContaining(descricao, PageRequest.of(pagina, 10)).getContent();
        for (Turma turma : turmas) {
            List<Aluno> alunos = alunoRepository.findByTurmaId(turma.getId());
            turma.setAlunos(alunos);
        }
        return turmas;
    }

    @Override
    public Turma findById(int id) {
        Turma turma = turmaRepository.findById(id).get();
        List<Aluno> alunos = alunoRepository.findByTurmaId(turma.getId());
        turma.setAlunos(alunos);
        return turma;
    }

    @Override
    public void deleteById(int id) {
        turmaRepository.deleteById(id);
    }

    @Override
    public Turma save(int id, Turma turmaReq){
        Turma turma = new Turma();

        turma.setDescricao(turmaReq.getDescricao());

        if (id != 0) {
            if (turmaRepository.existsById(id))
                turma.setId(id);
            else throw new NoSuchElementException();
        }

        if (turmaReq.getCurso() != null) {
            Curso curso = turmaReq.getCurso();
            int idCurso = curso.getId();

            if (idCurso != 0) {
                curso = cursoRepository.findById(idCurso).get();
                turma.setCurso(curso);
            }
        }

        return turmaRepository.save(turma);
    }
}
