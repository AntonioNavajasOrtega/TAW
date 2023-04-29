package es.taw.sampletaw.service;

import es.taw.sampletaw.dao.ClienteRepository;
import es.taw.sampletaw.dao.ConversacionRepository;
import es.taw.sampletaw.dao.EmpleadoRepository;
import es.taw.sampletaw.dto.ConversacionDTO;
import es.taw.sampletaw.dto.EmpleadoDTO;
import es.taw.sampletaw.dto.MensajeDTO;
import es.taw.sampletaw.entity.Cliente;
import es.taw.sampletaw.entity.Conversacion;
import es.taw.sampletaw.entity.Empleado;
import es.taw.sampletaw.entity.Mensaje;
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

    public ConversacionDTO buscarConversacion(Integer idChat) {
        Conversacion conversacion = this.conversacionRepository.findById(idChat).orElse(null);
        if(conversacion != null){
            return conversacion.toDTO();
        }else{
            return null;
        }

    }

    public void guardar(ConversacionDTO dto) {
        Conversacion conversacion = new Conversacion();

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
        conversacionDTO.setAbierta((byte)1);
        Timestamp fechaApertura = new Timestamp(System.currentTimeMillis());
        conversacionDTO.setFechaApertura(fechaApertura);
        this.guardar(conversacionDTO);
        return conversacionDTO;
    }


    public void cerrarConversacion(ConversacionDTO conv) {
        conv.setAbierta((byte)0);
        Timestamp fechaCierre = new Timestamp(System.currentTimeMillis());
        conv.setFechaCierre(fechaCierre);
    }

    public List<ConversacionDTO> listarConversacionesAbiertasDeUnCliente(Integer idcliente) {
        List<Conversacion> conversaciones = this.conversacionRepository.buscaPorUsuarioConversacionesAbiertas(idcliente);
        return this.listaEntidadesADTO(conversaciones);
    }

    private List<ConversacionDTO> listaEntidadesADTO(List<Conversacion> conversaciones) {
        ArrayList dtos = new ArrayList<MensajeDTO>();
        conversaciones.forEach((final Conversacion conversacion) -> dtos.add(conversacion.toDTO()));
        return dtos;
    }
}
