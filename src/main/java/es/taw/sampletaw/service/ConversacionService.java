package es.taw.sampletaw.service;

import es.taw.sampletaw.dao.ClienteRepository;
import es.taw.sampletaw.dao.ConversacionRepository;
import es.taw.sampletaw.dao.EmpleadoRepository;
import es.taw.sampletaw.dto.ConversacionDTO;
import es.taw.sampletaw.dto.EmpleadoDTO;
import es.taw.sampletaw.entity.Cliente;
import es.taw.sampletaw.entity.Conversacion;
import es.taw.sampletaw.entity.Empleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

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

        conversacion.setId(dto.getId());
        conversacion.setAbierta(dto.getAbierta());
        conversacion.setAsunto(dto.getAsunto());
        conversacion.setFechaApertura(dto.getFechaApertura());
        conversacion.setFechaApertura(dto.getFechaCierre());
        Empleado empleado = this.empleadoRepository.findById(dto.getEmpleado().getId()).orElse(null);
        conversacion.setEmpleadoByEmpleado(empleado);
        Cliente cliente = this.clienteRepository.findById(dto.getCliente().getId()).orElse(null);
        conversacion.setClienteByCliente(cliente);

        this.conversacionRepository.save(conversacion);
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

        return conversacionDTO;
    }


    public void cerrarConversacion(ConversacionDTO conv) {
        conv.setAbierta((byte)0);
        Timestamp fechaCierre = new Timestamp(System.currentTimeMillis());
        conv.setFechaCierre(fechaCierre);
    }
}
