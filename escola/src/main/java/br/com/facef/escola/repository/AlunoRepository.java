package br.com.facef.escola.repository;

import br.com.facef.escola.model.Aluno;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Integer> {

    Page<Aluno> findByNomeContaining(String nome, Pageable alunoPage);

    @Query(value = "select a.* from aluno a " +
                   "inner join aluno_turmas b on a.id = b.aluno_id " +
                   "where b.turmas_id = ?1 order by a.nome asc", nativeQuery = true)
    List<Aluno> findByTurmaId(int turma_id);
}