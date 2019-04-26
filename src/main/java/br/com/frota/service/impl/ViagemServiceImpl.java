package br.com.frota.service.impl;

import br.com.frota.dao.ViagemRepository;
import br.com.frota.exception.handler.ServiceValidationException;
import br.com.frota.model.Veiculo;
import br.com.frota.model.Viagem;
import br.com.frota.service.ViagemService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

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
            ServiceValidationException sve = getAlreadyExistException();
            throw sve;
        }

        viagem.setId(UUID.randomUUID().toString());

        /*
         * if throw DuplicateKeyException that means a concurrency issue to be treated.
         */
        try{
            return viagemRepository.insert(viagem);
        }catch (DuplicateKeyException ex ){
            logger.error(ex);
            ServiceValidationException sve = getAlreadyExistException();
            throw sve;
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

        Viagem viagemDb = viagemRepository.findByCliente(viagem.getCliente());

        if(viagemDb != null && !viagemDb.getId().equals(viagem.getId())){
            ServiceValidationException sve = getAlreadyExistException();
            throw sve;
        }

        /*
         * if throw DuplicateKeyException that means a concurrency issue to be treated.
         */
        try{
            return viagemRepository.save(viagem);
        }catch (DuplicateKeyException ex ){
            logger.error(ex);
            ServiceValidationException sve = getAlreadyExistException();
            throw sve;
        }
    }

    private ServiceValidationException getAlreadyExistException(){
        return new ServiceValidationException("Viagem already existis.",
                "viagem_exist",
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
        viagemRepository.deleteById(id);
    }
}
