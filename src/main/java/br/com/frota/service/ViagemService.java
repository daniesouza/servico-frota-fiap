package br.com.frota.service;

import br.com.frota.model.Veiculo;
import br.com.frota.model.Viagem;

import java.util.List;

public interface ViagemService {

    Viagem save(Viagem viagem);
    Viagem update(Viagem viagem);
    Viagem find(String id);
    List<Viagem> findAll();
    Viagem findByCliente(String cliente);
    void deleteById(String id);

}
