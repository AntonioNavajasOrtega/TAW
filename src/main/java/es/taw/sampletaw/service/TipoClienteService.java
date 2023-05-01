package es.taw.sampletaw.service;

import es.taw.sampletaw.dao.TipoClienteRepository;
import es.taw.sampletaw.dto.TipoClienteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TipoClienteService {

    @Autowired
    protected TipoClienteRepository tipoClienteRepository;
    public TipoClienteDTO buscarTipo(String tipoCliente) {

        return tipoClienteRepository.findTipo(tipoCliente).toDTO();

    }
}
