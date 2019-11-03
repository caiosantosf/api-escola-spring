package br.com.facef.escola.repository;

import br.com.facef.escola.model.Materia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MateriaRepository extends JpaRepository<Materia, Integer> {
    Page<Materia> findByDescricaoContaining(String descricao, Pageable turmaPage);

    @Query(value = "select a.* from materia a where a.professor_id = ?1", nativeQuery = true)
    List<Materia> findByProfessorId(int professor_id);
}