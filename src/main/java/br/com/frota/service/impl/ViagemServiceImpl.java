package br.com.frota.service.impl;

import br.com.frota.dao.ViagemRepository;
import br.com.frota.exception.handler.ServiceValidationException;
import br.com.frota.model.Veiculo;
import br.com.frota.model.Viagem;
import br.com.frota.service.VeiculoService;
import br.com.frota.service.ViagemService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ViagemServiceImpl implements ViagemService {

    /**
     * Default Logger.
     *
     * @see LogManager
     */
    private static final Logger logger = LogManager.getLogger(ViagemServiceImpl.class);

    @Autowired
    private ViagemRepository viagemRepository;

    @Autowired
    private VeiculoService veiculoService;


    /**
     *
     * Could have used a synchronized method but i preferred to let database handle concurrency
     *
     * @param viagem
     * @return Veiculo
     * @see Veiculo
     */
    @Override
    public Viagem save(Viagem viagem) {

        Viagem viagemDb = findByCliente(viagem.getCliente());

        if(viagemDb != null){
            throw getAlreadyExistException();
        }

        Veiculo veiculo = veiculoService.findVeiculoDisponivel();

        if(veiculo == null){
            throw new ServiceValidationException("Veiculo Indisponivel.",
                    "veiculo_indisponivel",
                    "veiculo");
        }

        viagem.setId(UUID.randomUUID().toString());
        viagem.setVeiculo(veiculo);
        viagem.getVeiculo().setStatus("Em trânsito");
        viagem.setStatus("Veiculo a caminho");
        viagem.setValor(new BigDecimal(Math.random()*100).setScale(2, BigDecimal.ROUND_HALF_UP));
        viagem.setChegada((long) (Math.random()*100));

        /*
         * if throw DuplicateKeyException that means a concurrency issue to be treated.
         */
        try{
            veiculoService.atualizarStatusVeiculo(veiculo);
            Viagem viagemAdd = viagemRepository.insert(viagem);

            return viagemAdd;
        }catch (DuplicateKeyException ex ){
            logger.error(ex);
            throw getAlreadyExistException();
        }
    }

    /**
     *
     *  Could have used a synchronized method but i preferred to let database handle concurrency
     *
     * @param viagem
     * @return Veiculo
     * @see Veiculo
     */
    @Override
    public Viagem update(Viagem viagem) {

        try{

            if("FINALIZAR".equals(viagem.getStatus())){
                viagem.getVeiculo().setStatus("Livre");
                viagem.setCliente(viagem.getCliente()+" Finalizado "+System.currentTimeMillis());
                veiculoService.atualizarStatusVeiculo(viagem.getVeiculo());
            }

            return viagemRepository.save(viagem);
        }catch (DuplicateKeyException ex ){
            logger.error(ex);
            throw getAlreadyExistException();
        }
    }

    private ServiceValidationException getAlreadyExistException(){
        return new ServiceValidationException("Viagem ja existe.",
                "viagem_existe",
                "viagem");
    }

    /**
     * @param id
     * @return Veiculo
     * @see Veiculo
     */
    @Override
    public Viagem find(String id){
       Optional<Viagem> viagemOpt = viagemRepository.findById(id);
       return viagemOpt.orElse(null);
    }

    /**
     * @return List<Veiculo>
     * @see Veiculo
     */
    @Override
    public List<Viagem> findAll() {
        return viagemRepository.findAll();
    }

    @Override
    public Viagem findByCliente(String cliente) {
        return viagemRepository.findByCliente(cliente);
    }


    /**
     * @param id
     */
    @Override
    public void deleteById(String id){

        Viagem viagem = find(id);

        viagem.getVeiculo().setStatus("Livre");
        veiculoService.atualizarStatusVeiculo(viagem.getVeiculo());

        viagemRepository.deleteById(id);
    }

}
