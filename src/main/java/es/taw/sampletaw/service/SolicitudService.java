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

/*
    @author Javier Serrano Contreras 75%
 */
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

    public void guardar(SolicitudDTO solicitud, ClienteDTO clienteDTO, CuentaDTO cuentaDTO, EmpleadoDTO empleadoDTO) {
        Cliente cliente = this.clienteRepository.findById(clienteDTO.getId()).orElse(null);
        Empleado empleado;
        Cuenta cuenta;

        if(empleadoDTO == null){
            empleado = this.empleadoRepository.findGestor();
        }else{
            empleado = this.empleadoRepository.findById(empleadoDTO.getId()).orElse(null);
        }

        if(cuentaDTO.getIban().equals("00000000")){
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
        if(cliente.getEmpresaByEmpresaId() != null){
            s.setEmpresaByEmpresaId(empresaRepository.findById(solicitud.getEmpresaByEmpresaId().getId()).orElse(null));
        }
        s.setTipoSolicitudByTipo(tipoSolicitudRepository.findById(solicitud.getTipoSolicitudByTipo().getId()).orElse(null));
        empleado.getSolicitudsById().add(s);

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
        EmpleadoDTO gestor = this.empleadoRepository.findGestor().toDTO();
        SolicitudDTO solicitud = new SolicitudDTO();
        solicitud.setClienteByClienteId(cliente);
        solicitud.setFecha(date);
        solicitud.setTipoSolicitudByTipo(tipo);
        solicitud.setCuentaByCuentaId(cuenta);
        solicitud.setEmpresaByEmpresaId(empresa);
        solicitud.setEmpleadoByEmpleadoId(gestor);
        return  solicitud;
    }

    public void solicitudPrimeraCuenta(ClienteDTO cliente){
        SolicitudDTO solicitud = new SolicitudDTO();
        solicitud.setClienteByClienteId(cliente);
        solicitud.setFecha(new Timestamp(System.currentTimeMillis()));
        EmpleadoDTO gestor = this.empleadoRepository.findGestor().toDTO();
        solicitud.setEmpleadoByEmpleadoId(gestor);
        TipoSolicitudDTO tipo = this.tipoSolicitudRepository.findSolicitarCuent().toDTO();
        solicitud.setTipoSolicitudByTipo(tipo);
        CuentaDTO c = new CuentaDTO();
        c.setIban("00000000");
        c.setSaldo(BigDecimal.valueOf(0));
        c.setClienteByClienteId(cliente);
        EstadoCuentaDTO estado = this.estadoCuentaRepository.findBloq().toDTO();
        c.setEstadoCuentaByEstado(estado);
        c.setSwift("342");
        c.setPais("-----");
        c.setEmpleadoByEmpleadoId(gestor);
        solicitud.setCuentaByCuentaId(c);

        List<SolicitudDTO> sol = new ArrayList<SolicitudDTO>();
        sol.add(solicitud);
        this.guardar(solicitud,cliente,c,gestor);
    }

    public void doSolicitar(CuentaDTO cuenta){
        ClienteDTO cliente = cuenta.getClienteByClienteId();
        EmpleadoDTO empleado = cuenta.getEmpleadoByEmpleadoId();
        TipoSolicitudDTO tipo = this.tipoSolicitudRepository.findActivar().toDTO();

        Timestamp date = new Timestamp(System.currentTimeMillis());
        SolicitudDTO solicitud = new SolicitudDTO();
        solicitud.setClienteByClienteId(cliente);
        solicitud.setFecha(date);
        solicitud.setTipoSolicitudByTipo(tipo);
        solicitud.setCuentaByCuentaId(cuenta);
        solicitud.setEmpleadoByEmpleadoId(empleado);

        this.guardar(solicitud,cliente,cuenta,empleado);
    }
}
