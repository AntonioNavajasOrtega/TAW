package es.taw.sampletaw.service;

import es.taw.sampletaw.dao.EstadoCuentaRepository;
import es.taw.sampletaw.dto.EstadoCuentaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstadoCuentaService {

    @Autowired
    protected EstadoCuentaRepository estadoCuentaRepository;


    public EstadoCuentaDTO bloqueada() {
        return this.estadoCuentaRepository.findBloq().toDTO();
    }
}
