package br.com.frota.dao;

import br.com.frota.model.Veiculo;
import br.com.frota.model.Viagem;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;

public interface ViagemRepository extends MongoRepository<Viagem, String> {

    Viagem findByCliente(String cliente);
}
