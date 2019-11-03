package br.com.facef.escola.repository;

import br.com.facef.escola.model.Curso;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Integer> {

    Page<Curso> findByDescricaoContaining(String descricao, Pageable cursoPage);

    @Query(value = "select a.* from curso a " +
                   "inner join curso_materias b on a.id = b.curso_id " +
                   "where b.materias_id = ?1", nativeQuery = true)
    List<Curso> findByMateriaId(int materiaId);
}