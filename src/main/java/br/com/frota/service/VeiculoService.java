package br.com.frota.service;

import br.com.frota.model.Veiculo;

import java.util.List;

public interface VeiculoService {


    Veiculo findVeiculoDisponivel();

    List<Veiculo> listAll();

    void atualizarStatusVeiculo(Veiculo veiculo);
}
