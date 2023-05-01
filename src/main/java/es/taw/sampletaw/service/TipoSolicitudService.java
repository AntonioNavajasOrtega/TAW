package es.taw.sampletaw.service;

import es.taw.sampletaw.dao.TipoSolicitudRepository;
import es.taw.sampletaw.dto.TipoSolicitudDTO;
import es.taw.sampletaw.entity.TipoSolicitud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TipoSolicitudService {

    @Autowired
    protected TipoSolicitudRepository tipoSolicitudRepository;
    public TipoSolicitudDTO activar() {
        return tipoSolicitudRepository.findActivar().toDTO();
    }

    public TipoSolicitudDTO solicitarCuenta() {
       return this.tipoSolicitudRepository.findSolicitarCuent().toDTO();
    }
}
