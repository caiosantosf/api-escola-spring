package br.com.facef.escola.business;

import java.util.List;
import br.com.facef.escola.model.Materia;

public interface MateriaBusiness {
    List<Materia> findAll(int pagina);
    List<Materia> findByDescricaoContaining(String descricao, int pagina);
    Materia findById(int id);
    void deleteById(int id);
    Materia save(int id, Materia turmaReq);
}
