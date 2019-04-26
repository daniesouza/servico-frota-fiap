package br.com.frota.model.dto;

import javax.validation.constraints.NotNull;

public class OperacaoDTO {

    @NotNull(message = "operacao não pode ser nulo!")
    private String operacao;


    public String getOperacao() {
        return operacao;
    }

    public void setOperacao(String operacao) {
        this.operacao = operacao;
    }
}
