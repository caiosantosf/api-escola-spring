package br.com.facef.escola.business;

import java.util.List;
import br.com.facef.escola.model.Aluno;

public interface AlunoBusiness {
    List<Aluno> findAll(int pagina);
    List<Aluno> findByNomeContaining(String nome, int pagina);
    Aluno findById(int id);
    void deleteById(int id);
    Aluno save(int id, Aluno alunoReq) throws Exception;
}
