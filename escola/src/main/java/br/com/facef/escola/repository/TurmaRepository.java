package br.com.facef.escola.repository;

import br.com.facef.escola.model.Turma;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Integer> {

    Page<Turma> findByDescricaoContaining(String descricao, Pageable turmaPage);

    @Query(value = "select a.* from turma a where a.curso_id = ?1", nativeQuery = true)
    List<Turma> findByCursoId(int curso_id);
}