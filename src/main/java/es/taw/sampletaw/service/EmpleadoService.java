package es.taw.sampletaw.service;

import es.taw.sampletaw.dao.EmpleadoRepository;
import es.taw.sampletaw.dto.EmpleadoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpleadoService {

    @Autowired
    protected EmpleadoRepository empleadoRepository;

    public EmpleadoDTO buscarGestor() {
        return this.empleadoRepository.findGestor().toDTO();
    }
}
