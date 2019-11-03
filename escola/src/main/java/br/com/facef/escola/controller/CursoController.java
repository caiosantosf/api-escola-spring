package br.com.facef.escola.controller;

import br.com.facef.escola.model.Curso;
import br.com.facef.escola.business.CursoBusiness;
import br.com.facef.escola.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/cursos")
public class CursoController {

    private CursoBusiness cursoBusiness;

    @Autowired
    public CursoController(CursoBusiness cursoBusiness){
        this.cursoBusiness = cursoBusiness;
    }

    @GetMapping
    public ResponseEntity<List<Curso>> findAll(@RequestParam(value = "descricao_contem", required = false) String nome,
                                               @RequestParam(value = "pagina", required = false, defaultValue = "0") int pagina){
        if (nome != null)
            return ResponseEntity.ok(cursoBusiness.findByDescricaoContaining(nome, pagina));
        return ResponseEntity.ok(cursoBusiness.findAll(pagina));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> findBy(@PathVariable int id) {
        return ResponseEntity.ok(cursoBusiness.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteById(@PathVariable(value = "id") int id) {
        cursoBusiness.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new Response("Registro excluído!"));
    }

    @PostMapping
    public ResponseEntity<Curso> post(@RequestBody Curso cursoReq){
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoBusiness.save(0, cursoReq));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Curso> put(@PathVariable int id, @RequestBody Curso cursoReq){
        return ResponseEntity.status(HttpStatus.OK).body(cursoBusiness.save(id, cursoReq));
    }
}
