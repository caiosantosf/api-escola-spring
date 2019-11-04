package br.com.facef.escola.config;

import br.com.facef.escola.model.Response;
import java.util.NoSuchElementException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestException {

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public Response exception(Exception ex) {
     return new Response("Erro interno de servidor!");
  }

  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
  public Response exception(HttpRequestMethodNotSupportedException ex) {
    return new Response("Método não suportado!");
  }

  @ExceptionHandler(EmptyResultDataAccessException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public Response exception(EmptyResultDataAccessException ex) {
    return new Response("Registro não encontrado!");
  }

  @ExceptionHandler(NoSuchElementException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public Response exception(NoSuchElementException ex) {
    return new Response("Registro não encontrado!");
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  @ResponseStatus(HttpStatus.FORBIDDEN)
  public Response exception(DataIntegrityViolationException ex) {
    return new Response("Registro possui relacionamentos e não pode ser excluído!");
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public Response exception(HttpMessageNotReadableException ex) {
    return new Response("Existem erros na requisição!");
  }

  @ExceptionHandler(AlunoNaoPodeTerTurmasComMesmoCursoException.class)
  @ResponseStatus(HttpStatus.FORBIDDEN)
  public Response exception(AlunoNaoPodeTerTurmasComMesmoCursoException ex) {
    return new Response("Aluno não pode ter turmas com o mesmo curso!");
  }
}

