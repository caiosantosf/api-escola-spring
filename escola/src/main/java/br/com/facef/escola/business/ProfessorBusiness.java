package br.com.facef.escola.business;

import java.util.List;
import br.com.facef.escola.model.Professor;

public interface ProfessorBusiness {
    List<Professor> findAll(int pagina);
    List<Professor> findByNomeContaining(String nome, int pagina);
    Professor findById(int id);
    void deleteById(int id);
    Professor save(int id, Professor professorReq) throws Exception;
}
