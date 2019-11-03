package br.com.facef.escola.controller;

import br.com.facef.escola.model.Professor;
import br.com.facef.escola.business.ProfessorBusiness;
import br.com.facef.escola.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/professores")
public class ProfessorController {

    private ProfessorBusiness professorBusiness;

    @Autowired
    public ProfessorController(ProfessorBusiness professorBusiness){
        this.professorBusiness = professorBusiness;
    }

    @GetMapping
    public ResponseEntity<List<Professor>> findAll(@RequestParam(value = "nome_contem", required = false) String nome,
                                               @RequestParam(value = "pagina", required = false, defaultValue = "0") int pagina){
        if (nome != null)
            return ResponseEntity.ok(professorBusiness.findByNomeContaining(nome, pagina));
        return ResponseEntity.ok(professorBusiness.findAll(pagina));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Professor> findBy(@PathVariable int id) {
        return ResponseEntity.ok(professorBusiness.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteById(@PathVariable(value = "id") int id) {
        professorBusiness.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new Response("Registro excluído!"));
    }

    @PostMapping
    public ResponseEntity<Professor> post(@RequestBody Professor professorReq) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(professorBusiness.save(0, professorReq));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Professor> put(@PathVariable int id, @RequestBody Professor professorReq) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(professorBusiness.save(id, professorReq));
    }
}
