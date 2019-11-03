package br.com.facef.escola.controller;

import br.com.facef.escola.model.Response;
import br.com.facef.escola.model.Turma;
import br.com.facef.escola.business.TurmaBusiness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/turmas")
public class TurmaController {

    private TurmaBusiness turmaBusiness;

    @Autowired
    public TurmaController(TurmaBusiness turmaBusiness){
        this.turmaBusiness = turmaBusiness;
    }

    @GetMapping
    public ResponseEntity<List<Turma>> findAll(@RequestParam(value = "descricao_contem", required = false) String nome,
                                               @RequestParam(value = "pagina", required = false, defaultValue = "0") int pagina){
        if (nome != null)
            return ResponseEntity.ok(turmaBusiness.findByDescricaoContaining(nome, pagina));
        return ResponseEntity.ok(turmaBusiness.findAll(pagina));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turma> findBy(@PathVariable int id) {
        return ResponseEntity.ok(turmaBusiness.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteById(@PathVariable(value = "id") int id) {
        turmaBusiness.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new Response("Registro excluído!"));
    }

    @PostMapping
    public ResponseEntity<Turma> post(@RequestBody Turma turmaReq){
        return ResponseEntity.status(HttpStatus.CREATED).body(turmaBusiness.save(0, turmaReq));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Turma> put(@PathVariable int id, @RequestBody Turma turmaReq){
        return ResponseEntity.status(HttpStatus.OK).body(turmaBusiness.save(id, turmaReq));
    }
}
