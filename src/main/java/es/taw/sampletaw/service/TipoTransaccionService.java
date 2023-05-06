package es.taw.sampletaw.service;

import es.taw.sampletaw.dao.TipoTransaccionRepository;
import es.taw.sampletaw.dto.TipoTransaccionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * @author Antonio Navajas Ortega
 */
@Service
public class TipoTransaccionService {

    @Autowired
    protected TipoTransaccionRepository tipoTransaccionRepository;
    public TipoTransaccionDTO cambio() {
        return this.tipoTransaccionRepository.findCambio().toDTO();
    }

    public TipoTransaccionDTO transaccion() {
       return this.tipoTransaccionRepository.findTrans().toDTO();
    }
}
