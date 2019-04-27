package br.com.frota.dao.impl;

import br.com.frota.dao.VeiculoRepository;
import br.com.frota.model.Veiculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class VeiculoRepositoryImpl implements VeiculoRepository {

   // @Autowired
  //  private RestTemplate restTemplate;

   static List<Veiculo> veiculos = new ArrayList<>();

   static{



       veiculos.add(new Veiculo());
       veiculos.add(new Veiculo());
       veiculos.add(new Veiculo());

       Veiculo v1 = veiculos.get(0);

       v1.setId(UUID.randomUUID().toString());
       v1.setAno(2019l);
       v1.setCor("Vermeho");
       v1.setLatitude(100000l);
       v1.setLongitude(200000l);
       v1.setModelo("GOL");
       v1.setPlaca("afv-2345");
       v1.setPorta("Fechado");
       v1.setStatus("AVAILABLE");

       Veiculo v2 = veiculos.get(1);

       v2.setId(UUID.randomUUID().toString());
       v2.setAno(2019l);
       v2.setCor("Verde");
       v2.setLatitude(100000l);
       v2.setLongitude(200000l);
       v2.setModelo("CORSA");
       v2.setPlaca("afv-2345");
       v2.setPorta("Fechado");
       v2.setStatus("OFFLINE");


       Veiculo v3 = veiculos.get(2);

       v3.setId(UUID.randomUUID().toString());
       v3.setAno(2019l);
       v3.setCor("Azul");
       v3.setLatitude(100000l);
       v3.setLongitude(200000l);
       v3.setModelo("SAVEIRO");
       v3.setPlaca("afv-2345");
       v3.setPorta("Fechado");
       v3.setStatus("TRAVELLING");
   }

    @Override
    public List<Veiculo> listAll() {




        return veiculos;
    }

    @Override
    public void atualizarStatusVeiculo(Veiculo veiculo) {

        //Atualiza status no endpoint
    }
}
