package br.com.frota.service.impl;

import br.com.frota.dao.VeiculoRepository;
import br.com.frota.exception.handler.ServiceValidationException;
import br.com.frota.model.Veiculo;
import br.com.frota.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class VeiculoServiceImpl implements VeiculoService {


    @Autowired
    private VeiculoRepository veiculoRepository;


    public Veiculo findVeiculoDisponivel(){

        List<Veiculo> veiculos = listAll();

        return veiculos.stream().filter(v -> v.getStatus().equals("AVAILABLE")).findFirst().orElse(null);
    }

    @Override
    public List<Veiculo> listAll() {
        return veiculoRepository.listAll();
    }

    @Override
    public void atualizarStatusVeiculo(Veiculo veiculo) {

        if(veiculo == null){
           throw new ServiceValidationException("Veiculo Obrigatorio.",
                    "veiculo_obrigatorio",
                    "veiculo");
        }

        if(veiculo.getId() == null || "".equals(veiculo.getId().trim())){
            throw new ServiceValidationException("ID Veiculo Obrigatorio.",
                    "id_veiculo_obrigatorio",
                    "veiculo");
        }

        if(veiculo.getStatus() == null || "".equals(veiculo.getStatus().trim())){
            throw new ServiceValidationException("Status Veiculo Obrigatorio.",
                    "status_veiculo_obrigatorio",
                    "veiculo");
        }

        veiculoRepository.atualizarStatusVeiculo(veiculo);

    }
}
