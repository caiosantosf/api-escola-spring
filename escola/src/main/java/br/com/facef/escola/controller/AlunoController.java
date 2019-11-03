package br.com.facef.escola.controller;

import br.com.facef.escola.model.Aluno;
import br.com.facef.escola.business.AlunoBusiness;
import br.com.facef.escola.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/alunos")
public class AlunoController {

    private AlunoBusiness alunoBusiness;

    @Autowired
    public AlunoController(AlunoBusiness alunoBusiness){
        this.alunoBusiness = alunoBusiness;
    }

    @GetMapping
    public ResponseEntity<List<Aluno>> findAll(@RequestParam(value = "nome_contem", required = false) String nome,
                                               @RequestParam(value = "pagina", required = false, defaultValue = "0") int pagina){
        if (nome != null)
            return ResponseEntity.ok(alunoBusiness.findByNomeContaining(nome, pagina));
        return ResponseEntity.ok(alunoBusiness.findAll(pagina));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> findBy(@PathVariable int id) {
        return ResponseEntity.ok(alunoBusiness.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteById(@PathVariable(value = "id") int id) {
        alunoBusiness.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new Response("Registro excluído!"));
    }

    @PostMapping
    public ResponseEntity<Aluno> post(@RequestBody Aluno alunoReq) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(alunoBusiness.save(0, alunoReq));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aluno> put(@PathVariable int id, @RequestBody Aluno alunoReq) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(alunoBusiness.save(id, alunoReq));
    }
}
