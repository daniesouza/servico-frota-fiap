package br.com.frota.model.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

public class ViagemCreateDTO implements Serializable{


    private static final long serialVersionUID = 1L;


    @NotNull(message = "cepAtual não pode ser nulo!")
    private String cepAtual;

    @NotNull(message = "numeroAtual não pode ser nulo!")
    private Long numeroAtual;

    @NotNull(message = "cepDestino não pode ser nulo!")
    private String cepDestino;

    @NotNull(message = "numeroDestino não pode ser nulo!")
    private Long numeroDestino;

    @NotNull(message = "cliente não pode ser nulo!")
    private String cliente;



    public String getCepAtual() {
        return cepAtual;
    }

    public void setCepAtual(String cepAtual) {
        this.cepAtual = cepAtual;
    }

    public Long getNumeroAtual() {
        return numeroAtual;
    }

    public void setNumeroAtual(Long numeroAtual) {
        this.numeroAtual = numeroAtual;
    }

    public String getCepDestino() {
        return cepDestino;
    }

    public void setCepDestino(String cepDestino) {
        this.cepDestino = cepDestino;
    }

    public Long getNumeroDestino() {
        return numeroDestino;
    }

    public void setNumeroDestino(Long numeroDestino) {
        this.numeroDestino = numeroDestino;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

}
