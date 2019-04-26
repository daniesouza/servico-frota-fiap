package br.com.frota.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.math.BigDecimal;

@Document(collection = "viagem")
public class Viagem implements Serializable {


    private static final long serialVersionUID = -5452972941608457365L;

    @Id
    private String id;

    private String cepAtual;
    private Long numeroAtual;
    private String cepDestino;
    private Long numeroDestino;
    private String cliente;
    private BigDecimal valor;
    private String status;
    private Long chegada;
    private Veiculo veiculo;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getChegada() {
        return chegada;
    }

    public void setChegada(Long chegada) {
        this.chegada = chegada;
    }
}
