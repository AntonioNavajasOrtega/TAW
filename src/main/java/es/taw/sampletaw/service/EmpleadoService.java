package es.taw.sampletaw.service;

import es.taw.sampletaw.dao.*;
import es.taw.sampletaw.dto.EmpleadoDTO;
import es.taw.sampletaw.dto.SolicitudDTO;
import es.taw.sampletaw.entity.Empleado;
import es.taw.sampletaw.entity.Solicitud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * @author Javier Serrano Contreras 60%
 * @author Antonio Navajas Ortega 40%
 */
@Service
public class EmpleadoService {

    @Autowired
    protected EmpleadoRepository empleadoRepository;

    @Autowired
    protected SolicitudRepository solicitudRepository;

    @Autowired
    protected TipoEmpleadoRepository tipoEmpleadoRepository;

    @Autowired
    protected CuentaRepository cuentaRepository;

    @Autowired
    protected ConversacionRepository conversacionRepository;

    @Autowired
    protected MensajeRepository mensajeRepository;

    public EmpleadoDTO buscarGestor() {
        return this.empleadoRepository.findGestor().toDTO();
    }

    public void guardar(EmpleadoDTO e){
        Empleado empleado;
        if(e.getId() == null)
        {
            empleado = new Empleado();
        }
        else
        {
            empleado = empleadoRepository.findById(e.getId()).orElse(null);
        }

        empleado.setNombreUsuario(e.getNombreUsuario());
        empleado.setContrasena(e.getContrasena());

        empleado.setTipoEmpleadoByTipo(tipoEmpleadoRepository.findById(e.getId()).orElse(null));
        empleado.setCuentasById(cuentaRepository.cuentasEmpleado(e.getId()));
        empleado.setSolicitudsById(solicitudRepository.solicitudesEmpleado(e.getId()));
        empleado.setConversacionsById(conversacionRepository.buscaPorEmpleado(e.getId()));
        empleado.setMensajesById(mensajeRepository.mensajesEmpleadoEmisor(e.getId()));
        empleado.setMensajesById_0(mensajeRepository.mensajesEmpleadoReceptor(e.getId()));

        empleadoRepository.save(empleado);
        e.setId(empleado.getId());
    }
}
