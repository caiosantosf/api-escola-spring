package br.com.facef.escola.controller;

import br.com.facef.escola.model.Materia;
import br.com.facef.escola.business.MateriaBusiness;
import br.com.facef.escola.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/materias")
public class MateriaController {

    private MateriaBusiness materiaBusiness;

    @Autowired
    public MateriaController(MateriaBusiness materiaBusiness){
        this.materiaBusiness = materiaBusiness;
    }

    @GetMapping
    public ResponseEntity<List<Materia>> findAll(@RequestParam(value = "descricao_contem", required = false) String nome,
                                               @RequestParam(value = "pagina", required = false, defaultValue = "0") int pagina){
        if (nome != null)
            return ResponseEntity.ok(materiaBusiness.findByDescricaoContaining(nome, pagina));
        return ResponseEntity.ok(materiaBusiness.findAll(pagina));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Materia> findBy(@PathVariable int id) {
        return ResponseEntity.ok(materiaBusiness.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteById(@PathVariable(value = "id") int id) {
        materiaBusiness.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new Response("Registro excluído!"));
    }

    @PostMapping
    public ResponseEntity<Materia> post(@RequestBody Materia materiaReq){
        return ResponseEntity.status(HttpStatus.CREATED).body(materiaBusiness.save(0, materiaReq));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Materia> put(@PathVariable int id, @RequestBody Materia materiaReq){
        return ResponseEntity.status(HttpStatus.OK).body(materiaBusiness.save(id, materiaReq));
    }
}
