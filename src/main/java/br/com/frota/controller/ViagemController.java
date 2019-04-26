package br.com.frota.controller;

import br.com.frota.config.BeanMapper;
import br.com.frota.model.Viagem;
import br.com.frota.model.dto.IdDTO;
import br.com.frota.model.dto.OperacaoDTO;
import br.com.frota.model.dto.ViagemDTO;
import br.com.frota.model.dto.MessageDTO;
import br.com.frota.service.ViagemService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/viagem")
public class ViagemController {

    /**
     * Default Logger.
     *
     * @see LogManager
     */
    private static final Logger logger = LogManager.getLogger(ViagemController.class);

    @Autowired
    private ViagemService viagemService;


    /**
     * @return List<ViagemDTO>.
     * @see ViagemDTO
     */
    @GetMapping("/")
    public ResponseEntity<List<ViagemDTO>> findAll() {

        List<ViagemDTO> dtos = new ArrayList<>();

        viagemService.findAll()
                .forEach(cc -> {
                    dtos.add(BeanMapper.getInstance().map(cc, ViagemDTO.class));
                });

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    /**
     * @param id
     * @return List<ViagemDTO>.
     * @see ViagemDTO
     */
    @GetMapping("/{id}")
    public ResponseEntity<ViagemDTO> find(@PathVariable("id") String id) {

        Viagem viagem = viagemService.find(id);

        if (viagem == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(BeanMapper.getInstance().map(viagem, ViagemDTO.class), HttpStatus.OK);
    }

    /**
     * @param cliente
     * @return ViagemDTO.
     * @see ViagemDTO
     */
    @GetMapping("/{cliente}")
    public ResponseEntity<ViagemDTO> findByCliente(@PathVariable("cliente") String cliente) {

        Viagem viagem = viagemService.findByCliente(cliente);

        if (viagem == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(BeanMapper.getInstance().map(viagem, ViagemDTO.class), HttpStatus.OK);
    }

    /**
     * @param viagemDTO
     * @return List<IdDTO>.
     * @see ViagemDTO
     */
    @PostMapping("/")
    public ResponseEntity<ViagemDTO> save(@RequestBody @Valid final ViagemDTO viagemDTO) {

        logger.info("Saving "+ viagemDTO.toString());

        Viagem viagem = viagemService.save(BeanMapper.getInstance().map(viagemDTO, Viagem.class));

        return new ResponseEntity<>(BeanMapper.getInstance().map(viagem, ViagemDTO.class), HttpStatus.CREATED);
    }

    /**
     * @param operacaoDTO
     * @return ViagemDTO
     * @see IdDTO
     */
    @PutMapping("/{id}")
    public ResponseEntity<ViagemDTO> update(@PathVariable("id") String id, @RequestBody @Valid final OperacaoDTO operacaoDTO) {

        logger.info("Updating viagem "+ id);

        Viagem viagem = viagemService.find(id);

        if (viagem == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        viagem.setStatus(operacaoDTO.getOperacao());

        viagemService.update(viagem);

        return new ResponseEntity<>(BeanMapper.getInstance().map(viagem, ViagemDTO.class), HttpStatus.OK);
    }

    /**
     * @param id
     * @return List<MessageDTO>.
     * @see MessageDTO
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<MessageDTO> delete(@PathVariable("id") String id) {
        logger.info("Deleting Viagem with ID = " + id + "...");

        Viagem viagem = viagemService.find(id);

        if (viagem == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        viagemService.deleteById(id);
        return new ResponseEntity<>(new MessageDTO("Viagem has been deleted!"), HttpStatus.OK);
    }


}
