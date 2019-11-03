package br.com.facef.escola.model;

import com.fasterxml.jackson.annotation.JsonView;

import java.io.Serializable;

public class Response implements Serializable {

  private static final long serialVersionUID = -7773546012556812317L;
  private String mensagem;

  public Response(String mensagem) {
    this.mensagem = mensagem;
  }

  public String getMensagem() {
    return mensagem;
  }

  public void setMensagem(String mensagem) {
    this.mensagem = mensagem;
  }
}
