package es.taw.sampletaw.service;

import es.taw.sampletaw.dao.*;
import es.taw.sampletaw.dto.*;
import es.taw.sampletaw.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    protected EstadoCuentaRepository estadoCuentaRepository;

    @Autowired
    protected EmpleadoService empleadoService;

    public void guardar(SolicitudDTO solicitud, ClienteDTO clienteDTO, CuentaDTO cuentaDTO, EmpleadoDTO empleadoDTO) {
        Cliente cliente = this.clienteRepository.findById(clienteDTO.getId()).orElse(null);
        Empleado empleado;
        Cuenta cuenta;

        if(empleadoDTO == null){
            empleado = this.empleadoRepository.findGestor();
        }else{
            empleado = this.empleadoRepository.findById(empleadoDTO.getId()).orElse(null);
        }

        if(cuentaDTO.getIban() == "00000000"){
            cuenta = new Cuenta();
            cuenta.setSaldo(cuentaDTO.getSaldo());
            cuenta.setIban(cuentaDTO.getIban());
            cuenta.setClienteByClienteId(cliente);
            EstadoCuenta estado = this.estadoCuentaRepository.findBloq();
            cuenta.setEstadoCuentaByEstado(estado);
            cuenta.setSwift(cuentaDTO.getSwift());
            cuenta.setPais(cuentaDTO.getPais());
            cuenta.setEmpleadoByEmpleadoId(empleado);
        }else{
            cuenta = this.cuentaRepository.findById(cuentaDTO.getId()).orElse(null);
        }

        Solicitud s;
        if(solicitud.getId() == null)
        {
            s = new Solicitud();
        }
        else {
            s = solicitudRepository.findById(solicitud.getId()).orElse(null);
        }

        s.setId(solicitud.getId());
        s.setFecha(solicitud.getFecha());
        s.setClienteByClienteId(cliente);
        s.setEmpleadoByEmpleadoId(empleado);
        s.setCuentaByCuentaId(cuenta);
        s.setEmpresaByEmpresaId(empresaRepository.findById(solicitud.getEmpresaByEmpresaId().getId()).orElse(null));
        s.setTipoSolicitudByTipo(tipoSolicitudRepository.findById(solicitud.getTipoSolicitudByTipo().getId()).orElse(null));

        clienteRepository.save(cliente);
        cuentaRepository.save(cuenta);
        empleadoRepository.save(empleado);
        solicitudRepository.save(s);
    }

    private List<SolicitudDTO> listaSolicitudADTO(List<Solicitud> solicitudes) {
        ArrayList dtos = new ArrayList<>();
        solicitudes.forEach((final Solicitud solicitud) -> dtos.add(solicitud.toDTO()));
        return dtos;
    }

    public List<SolicitudDTO> solicitudCliente(Integer idcliente){
        return this.listaSolicitudADTO(this.solicitudRepository.findSolicitudCliente(idcliente));
    }

    public SolicitudDTO crear(ClienteDTO cliente, TipoSolicitudDTO tipo, CuentaDTO cuenta, EmpresaDTO empresa) {
        Timestamp date = new Timestamp(System.currentTimeMillis());
        EmpleadoDTO gestor = empleadoService.buscarGestor();
        SolicitudDTO solicitud = new SolicitudDTO();
        solicitud.setClienteByClienteId(cliente);
        solicitud.setFecha(date);
        solicitud.setTipoSolicitudByTipo(tipo);
        solicitud.setCuentaByCuentaId(cuenta);
        solicitud.setEmpresaByEmpresaId(empresa);
        solicitud.setEmpleadoByEmpleadoId(gestor);
        return  solicitud;
    }
}
