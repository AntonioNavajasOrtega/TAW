package es.taw.sampletaw.service;

import es.taw.sampletaw.dao.ClienteRepository;
import es.taw.sampletaw.dao.ConversacionRepository;
import es.taw.sampletaw.dao.EmpleadoRepository;
import es.taw.sampletaw.dao.MensajeRepository;
import es.taw.sampletaw.dto.ConversacionDTO;
import es.taw.sampletaw.dto.MensajeDTO;
import es.taw.sampletaw.entity.Cliente;
import es.taw.sampletaw.entity.Conversacion;
import es.taw.sampletaw.entity.Empleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class ConversacionService {
    @Autowired
    protected ConversacionRepository conversacionRepository;

    @Autowired
    protected ClienteRepository clienteRepository;

    @Autowired
    protected EmpleadoRepository empleadoRepository;

    @Autowired
    protected MensajeRepository mensajeRepository;

    public ConversacionDTO buscarConversacion(Integer idChat) {
        Conversacion conversacion = this.conversacionRepository.findById(idChat).orElse(null);
        if (conversacion != null) {
            return conversacion.toDTO();
        } else {
            return null;
        }

    }

    public void guardarOEditar(ConversacionDTO dto) {
        Conversacion conversacion;
        if (dto.getId() == null) {
            conversacion = new Conversacion();
        } else {
            conversacion = this.conversacionRepository.findById(dto.getId()).orElse(null);
        }

        conversacion.setAbierta(dto.getAbierta());
        conversacion.setAsunto(dto.getAsunto());
        conversacion.setFechaApertura(dto.getFechaApertura());
        conversacion.setFechaCierre(dto.getFechaCierre());
        Empleado empleado = this.empleadoRepository.findById(dto.getEmpleado().getId()).orElse(null);
        conversacion.setEmpleadoByEmpleado(empleado);
        Cliente cliente = this.clienteRepository.findById(dto.getCliente().getId()).orElse(null);
        conversacion.setClienteByCliente(cliente);

        this.conversacionRepository.save(conversacion);
        dto.setId(conversacion.getId());
    }

    public ConversacionDTO iniciarChat(Integer idCliente, String asunto) {
        ConversacionDTO conversacionDTO = new ConversacionDTO();
        Cliente cliente = this.clienteRepository.findById(idCliente).orElse(null);
        conversacionDTO.setCliente(cliente.toDTO());
        Empleado asistente = this.empleadoRepository.findById(2).orElse(null);
        conversacionDTO.setEmpleado(asistente.toDTO());
        conversacionDTO.setAsunto(asunto);
        conversacionDTO.setAbierta((byte) 1);
        Timestamp fechaApertura = new Timestamp(System.currentTimeMillis());
        conversacionDTO.setFechaApertura(fechaApertura);

        return conversacionDTO;
    }

    public void cerrarConversacion(ConversacionDTO conv) {
        conv.setAbierta((byte) 0);
        Timestamp fechaCierre = new Timestamp(System.currentTimeMillis());
        conv.setFechaCierre(fechaCierre);
    }

    public List<ConversacionDTO> listarConversacionesAbiertasDeUnCliente(Integer idcliente) {
        List<Conversacion> conversaciones = this.conversacionRepository
                .buscaPorUsuarioConversacionesAbiertas(idcliente);
        return this.listaEntidadesADTO(conversaciones);
    }

    private List<ConversacionDTO> listaEntidadesADTO(List<Conversacion> conversaciones) {
        ArrayList dtos = new ArrayList<MensajeDTO>();
        conversaciones.forEach((final Conversacion conversacion) -> {
            Integer numeroMensajes = this.mensajeRepository.mensajesPorConversacion(conversacion.getId()).size();
            ConversacionDTO conversacionDTO = conversacion.toDTO();
            conversacionDTO.setNumeroMensajes(numeroMensajes);
            dtos.add(conversacionDTO);
        });
        return dtos;
    }

    public List<ConversacionDTO> listarConversaciones() {
        List<Conversacion> conversaciones = this.conversacionRepository.findAll();
        return this.listaEntidadesADTO(conversaciones);
    }

    public List<ConversacionDTO> listarConversaciones(String usuario, String fechaApertura, String numeroMensajes) {
        List<Conversacion> conversaciones;
        if(usuario.isEmpty() && fechaApertura.isEmpty() && numeroMensajes.isEmpty()){
            conversaciones = this.conversacionRepository.findAll();
        }else if (numeroMensajes.equals("asc") && usuario.isEmpty() && fechaApertura.isEmpty()) {
            conversaciones = this.conversacionRepository.ordenPorNumeroMensajesAsc();
        } else if (numeroMensajes.equals("desc") && usuario.isEmpty() && fechaApertura.isEmpty()) {
            conversaciones = this.conversacionRepository.ordenPorNumeroMensajesDesc();
        } else if (numeroMensajes.isEmpty() && !usuario.isEmpty() && fechaApertura.isEmpty()) {
            conversaciones = this.conversacionRepository.buscaPorUsuario(usuario);
        } else if (numeroMensajes.isEmpty() && usuario.isEmpty() && fechaApertura.equals("asc")) {
            conversaciones = this.conversacionRepository.ordenPorFechaAperturaAsc();
        } else if (numeroMensajes.isEmpty() && usuario.isEmpty() && fechaApertura.equals("desc")) {
            conversaciones = this.conversacionRepository.ordenPorFechaAperturaDesc();
        } else if (numeroMensajes.equals("desc") && usuario.isEmpty() && fechaApertura.equals("desc")) {
            conversaciones = this.conversacionRepository.ordenFechaAperturaDescYMensajesDesc();
        } else if (numeroMensajes.equals("asc") && usuario.isEmpty() && fechaApertura.equals("asc")) {
            conversaciones = this.conversacionRepository.ordenFechaAperturaAscYMensajesAsc();
        } else if (numeroMensajes.equals("asc") && usuario.isEmpty() && fechaApertura.equals("desc")) {
            conversaciones = this.conversacionRepository.ordenFechaAperturaDescYMensajesAsc();
        } else if (numeroMensajes.equals("desc") && usuario.isEmpty() && fechaApertura.equals("asc")) {
            conversaciones = this.conversacionRepository.ordenFechaAperturaAscYMensajesDesc();
        } else if (numeroMensajes.equals("desc") && !usuario.isEmpty() && fechaApertura.equals("desc")) {
            conversaciones = this.conversacionRepository.ordenFechaAperturaDescYMensajesDescYBuscaUsuario(usuario);
        } else if (numeroMensajes.equals("asc") && !usuario.isEmpty() && fechaApertura.equals("asc")) {
            conversaciones = this.conversacionRepository.ordenFechaAperturaAscYMensajesAscYBuscaUsuario(usuario);
        } else if (numeroMensajes.equals("asc") && !usuario.isEmpty() && fechaApertura.equals("desc")) {
            conversaciones = this.conversacionRepository.ordenFechaAperturaDescYMensajesAscYBuscaUsuario(usuario);
        } else if (numeroMensajes.equals("desc") && !usuario.isEmpty() && fechaApertura.equals("asc")) {
            conversaciones = this.conversacionRepository.ordenFechaAperturaAscYMensajesDescYBuscaUsuario(usuario);
        } else if (numeroMensajes.equals("asc") && !usuario.isEmpty() && fechaApertura.isEmpty()) {
            conversaciones = this.conversacionRepository.ordenPorNumeroMensajesAscYBuscaUsuario(usuario);
        } else if (numeroMensajes.equals("desc") && !usuario.isEmpty() && fechaApertura.isEmpty()) {
            conversaciones = this.conversacionRepository.ordenPorNumeroMensajesDescYBuscaUsuario(usuario);
        } else if (numeroMensajes.isEmpty() && !usuario.isEmpty() && fechaApertura.equals("asc")) {
            conversaciones = this.conversacionRepository.ordenPorFechaAperturaAscYBuscaUsuario(usuario);
        } else if (numeroMensajes.isEmpty() && !usuario.isEmpty() && fechaApertura.equals("desc")) {
            conversaciones = this.conversacionRepository.ordenPorFechaAperturaDescYBuscaUsuario(usuario);
        } else if (numeroMensajes.equals("desc") && !usuario.isEmpty() && fechaApertura.equals("desc")) {
            conversaciones = this.conversacionRepository.ordenFechaAperturaDescYMensajesDescYBuscaUsuario(usuario);
        } else if (numeroMensajes.equals("asc") && !usuario.isEmpty() && fechaApertura.equals("asc")) {
            conversaciones = this.conversacionRepository.ordenFechaAperturaAscYMensajesAscYBuscaUsuario(usuario);
        } else if (numeroMensajes.equals("asc") && !usuario.isEmpty() && fechaApertura.equals("desc")) {
            conversaciones = this.conversacionRepository.ordenFechaAperturaDescYMensajesAscYBuscaUsuario(usuario);
        } else {
            conversaciones = this.conversacionRepository.ordenFechaAperturaAscYMensajesDescYBuscaUsuario(usuario);
        }
        return this.listaEntidadesADTO(conversaciones);
    }


}
