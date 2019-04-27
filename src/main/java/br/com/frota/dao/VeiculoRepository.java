package br.com.frota.dao;

import br.com.frota.model.Veiculo;
import br.com.frota.model.Viagem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface VeiculoRepository{

    List<Veiculo> listAll();

    void atualizarStatusVeiculo(Veiculo veiculo);

}
