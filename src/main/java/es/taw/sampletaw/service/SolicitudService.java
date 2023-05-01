package es.taw.sampletaw.service;

import es.taw.sampletaw.dao.*;
import es.taw.sampletaw.dto.SolicitudDTO;
import es.taw.sampletaw.entity.Solicitud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SolicitudService {

    @Autowired
    protected SolicitudRepository solicitudRepository;

    @Autowired
    protected ClienteRepository clienteRepository;

    @Autowired
    protected EmpleadoRepository empleadoRepository;

    @Autowired
    protected CuentaRepository cuentaRepository;

    @Autowired
    protected EmpresaRepository empresaRepository;

    @Autowired
    protected TipoSolicitudRepository tipoSolicitudRepository;

    public void guardar(SolicitudDTO solicitud) {
        Solicitud s = new Solicitud();

        s.setId(solicitud.getId());
        s.setFecha(solicitud.getFecha());
        s.setClienteByClienteId(clienteRepository.findById(solicitud.getClienteByClienteId().getId()).orElse(null));
        s.setEmpleadoByEmpleadoId(empleadoRepository.findGestor());
        s.setCuentaByCuentaId(cuentaRepository.findById(solicitud.getCuentaByCuentaId().getId()).orElse(null));
        s.setEmpresaByEmpresaId(empresaRepository.findById(solicitud.getId()).orElse(null));
        s.setTipoSolicitudByTipo(tipoSolicitudRepository.findById(solicitud.getTipoSolicitudByTipo().getId()).orElse(null));


        solicitudRepository.save(s);
    }
}
