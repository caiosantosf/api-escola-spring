package br.com.facef.escola.business;

import java.util.List;
import br.com.facef.escola.model.Curso;

public interface CursoBusiness {
    List<Curso> findAll(int pagina);
    List<Curso> findByDescricaoContaining(String nome, int pagina);
    Curso findById(int id);
    void deleteById(int id);
    Curso save(int id, Curso turmaReq);
}
