package br.com.frota.dao.impl;

import br.com.frota.config.BeanMapper;
import br.com.frota.dao.VeiculoRepository;
import br.com.frota.model.Veiculo;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Repository
public class VeiculoRepositoryImpl implements VeiculoRepository {

    final String API_URL = "https://vehicle-control-service.herokuapp.com/v1/veiculos/";


    @Override
    public List<Veiculo> listAll() {

            RestTemplate restTemplate = new RestTemplate();
            LinkedHashMap veiculoRequest = restTemplate.getForObject(API_URL, LinkedHashMap.class);
            List<Veiculo> veiculosApi = new ArrayList<>();


            if(veiculoRequest != null){
                veiculoRequest.forEach((key, value) -> {
                    veiculosApi.add(BeanMapper.getInstance().map(value, Veiculo.class));
                });

            }

            return veiculosApi;

    }

    @Override
    public void atualizarStatusVeiculo(Veiculo veiculo) {

        RestTemplate restTemplate = new RestTemplate();

        String status = "{\n" +
                "  \"status\": \""+veiculo.getStatus()+"\"\n" +
                "}";

        HttpEntity<String> request = new HttpEntity<>(status);


        restTemplate.put(API_URL+"/status/"+veiculo.getId(),request);
    }
}
