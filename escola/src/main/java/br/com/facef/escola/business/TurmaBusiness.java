package br.com.facef.escola.business;

import java.util.List;
import br.com.facef.escola.model.Turma;

public interface TurmaBusiness {
    List<Turma> findAll(int pagina);
    List<Turma> findByDescricaoContaining(String descricao, int pagina);
    Turma findById(int id);
    void deleteById(int id);
    Turma save(int id, Turma turmaReq);
}
