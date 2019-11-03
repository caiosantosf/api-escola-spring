package br.com.facef.escola.business.impl;

import br.com.facef.escola.config.AlunoNaoPodeTerTurmasComMesmoCursoException;
import br.com.facef.escola.model.Aluno;
import br.com.facef.escola.business.AlunoBusiness;
import br.com.facef.escola.model.Turma;
import br.com.facef.escola.repository.AlunoRepository;
import br.com.facef.escola.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AlunoBusinessImpl implements AlunoBusiness {

    private AlunoRepository alunoRepository;
    private TurmaRepository turmaRepository;

    @Autowired
    public AlunoBusinessImpl(AlunoRepository alunoRepository, TurmaRepository turmaRepository){
        this.alunoRepository = alunoRepository;
        this.turmaRepository = turmaRepository;
    }

    @Override
    public List<Aluno> findAll(int pagina) {
        return alunoRepository.findAll(PageRequest.of(pagina, 10)).getContent();
    }

    @Override
    public List<Aluno> findByNomeContaining(String nome, int pagina) {
        return alunoRepository.findByNomeContaining(nome, PageRequest.of(pagina, 10)).getContent();
    }

    @Override
    public Aluno findById(int id) {
        return alunoRepository.findById(id).get();
    }

    @Override
    public void deleteById(int id) { alunoRepository.deleteById(id); }

    @Override
    public Aluno save(int id, Aluno alunoReq) throws Exception{
        Aluno aluno = new Aluno();

        if (id != 0) {
            if (alunoRepository.existsById(id))
                aluno.setId(id);
            else throw new NoSuchElementException();
        }

        aluno.setNome(alunoReq.getNome());
        aluno.setEmail(alunoReq.getEmail());

        List<Turma> turmasReq = alunoReq.getTurmas();

        if (turmasReq != null) {

            List<Turma> turmas = new ArrayList<Turma>();

            for (Turma turma : turmasReq) {
                int idTurma = turma.getId();

                if (idTurma != 0) {
                    turma = turmaRepository.findById(idTurma).get();

                    for (Turma turmaValida : turmas){
                        if (turmaValida.getCurso().getId() == turma.getCurso().getId())
                            throw new AlunoNaoPodeTerTurmasComMesmoCursoException();
                    }
                    turmas.add(turma);
                }
            }

            aluno.setTurmas(turmas);
        }

        return alunoRepository.save(aluno);
    }
}