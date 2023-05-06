package es.taw.sampletaw.service;

import es.taw.sampletaw.dao.CuentaRepository;
import es.taw.sampletaw.dao.TipoTransaccionRepository;
import es.taw.sampletaw.dao.TransaccionRepository;
import es.taw.sampletaw.dto.CuentaDTO;
import es.taw.sampletaw.dto.TipoTransaccionDTO;
import es.taw.sampletaw.dto.TransaccionDTO;
import es.taw.sampletaw.entity.Cuenta;
import es.taw.sampletaw.entity.Transaccion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Javier Serrano Contreras 80%
 */
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

    public void crearTrans(TransaccionDTO transaccion){
        TipoTransaccionDTO tipo = this.tipoTransaccionRepository.findTrans().toDTO();
        transaccion.setTipoTransaccionByTipo(tipo);
    }

    public void crearCamb(TransaccionDTO transaccion){
        TipoTransaccionDTO tipo = this.tipoTransaccionRepository.findCambio().toDTO();
        transaccion.setTipoTransaccionByTipo(tipo);
    }

    public void doRealizar(TransaccionDTO transaccion,int origenID,int destinoID){
        TipoTransaccionDTO tipo = this.tipoTransaccionRepository.findTrans().toDTO();
        CuentaDTO destino = this.cuentaRepository.findById(destinoID).get().toDTO();
        CuentaDTO origen = this.cuentaRepository.findById(origenID).get().toDTO();
        transaccion.setTipoTransaccionByTipo(tipo);
        transaccion.setCuentaByCuentaDestinoId(destino);
        transaccion.setCuentaByCuentaOrigenId(origen);
    }

    public void doTerminarRealizar(TransaccionDTO transaccion,TransaccionDTO transaccion1,CuentaDTO orig,CuentaDTO dest){
        Timestamp date = new Timestamp(System.currentTimeMillis());
        transaccion.setFecha(date);
        transaccion1.setFecha(date);
        transaccion1.setCantidad(transaccion.getCantidad().negate());
        transaccion1.setCuentaByCuentaOrigenId(orig);
        transaccion1.setCuentaByCuentaDestinoId(dest);
        transaccion1.setTipoTransaccionByTipo(transaccion.getTipoTransaccionByTipo());

        if(dest.getTransaccionsById() == null)
        {
            List<TransaccionDTO> list1 = new ArrayList<>();
            list1.add(transaccion);
            dest.setTransaccionsById(list1);
        }
        else {
            dest.getTransaccionsById().add(transaccion);
        }

        if(orig.getTransaccionsById() == null)
        {
            List<TransaccionDTO> list2 = new ArrayList<>();
            list2.add(transaccion1);
            orig.setTransaccionsById(list2);
        }
        else {
            orig.getTransaccionsById().add(transaccion1);
        }

        orig.setSaldo(orig.getSaldo().subtract(transaccion.getCantidad()));
        dest.setSaldo(dest.getSaldo().add(transaccion.getCantidad()));
    }

    public void doCambiarMoneda(TransaccionDTO transaccion,int origenID,int destinoID){
        TipoTransaccionDTO tipo = this.tipoTransaccionRepository.findCambio().toDTO();
        CuentaDTO destino = this.cuentaRepository.findById(destinoID).get().toDTO();
        CuentaDTO origen = this.cuentaRepository.findById(origenID).get().toDTO();
        transaccion.setTipoTransaccionByTipo(tipo);
        transaccion.setCuentaByCuentaDestinoId(destino);
        transaccion.setCuentaByCuentaOrigenId(origen);
    }

    public void doTerminarCambioMoneda(TransaccionDTO transaccion, CuentaDTO cuenta){
        Timestamp date = new Timestamp(System.currentTimeMillis());
        transaccion.setFecha(date);
        transaccion.setCantidad(cuenta.getSaldo());
        transaccion.setCuentaByCuentaDestinoId(cuenta);

        if(cuenta.getTransaccionsById() == null)
        {
            List<TransaccionDTO> list1 = new ArrayList<>();
            list1.add(transaccion);
            cuenta.setTransaccionsById(list1);
        }
        else
        {
            cuenta.getTransaccionsById().add(transaccion);
        }



        if(transaccion.getMoneda().equals("usd")){
            cuenta.setSaldo(transaccion.getCantidad().multiply(BigDecimal.valueOf(1.09708)));
        }else if(transaccion.getMoneda().equals("eur")){
            cuenta.setSaldo(transaccion.getCantidad().divide(BigDecimal.valueOf(1.09708), RoundingMode.HALF_EVEN));
        }
    }
}
