package es.taw.sampletaw.service;

import es.taw.sampletaw.dao.CuentaRepository;
import es.taw.sampletaw.dao.TipoTransaccionRepository;
import es.taw.sampletaw.dao.TransaccionRepository;
import es.taw.sampletaw.dto.TransaccionDTO;
import es.taw.sampletaw.entity.Cuenta;
import es.taw.sampletaw.entity.Transaccion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransaccionService {

    @Autowired
    protected TransaccionRepository transaccionRepository;

    @Autowired
    protected TipoTransaccionRepository tipoTransaccionRepository;

    @Autowired
    protected CuentaRepository cuentaRepository;

    public TransaccionDTO buscarPorIdTransaccion(Integer idtrans){
        return transaccionRepository.findById(idtrans).orElse(null).toDTO();
    }

    public void guardar(TransaccionDTO t){
        Transaccion transaccion;
        if(t.getId() == null)
        {
            transaccion = new Transaccion();
        }
        else
        {
            transaccion = transaccionRepository.findById(t.getId()).orElse(null);
        }

        transaccion.setCantidad(t.getCantidad());
        transaccion.setFecha(t.getFecha());
        transaccion.setMoneda(t.getMoneda());


        transaccion.setTipoTransaccionByTipo(tipoTransaccionRepository.getById(t.getTipoTransaccionByTipo().getId()));
        transaccion.setCuentaByCuentaDestinoId(cuentaRepository.getById(t.getCuentaByCuentaDestinoId().getId()));
        transaccion.setCuentaByCuentaOrigenId(cuentaRepository.getById(t.getCuentaByCuentaOrigenId().getId()));

        transaccionRepository.save(transaccion);
        t.setId(transaccion.getId());
    }
}
