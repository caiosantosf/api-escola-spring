package br.com.facef.escola.repository;

import br.com.facef.escola.model.Professor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Integer> {

    Page<Professor> findByNomeContaining(String nome, Pageable professorPage);
}