package es.taw.sampletaw.service;

import es.taw.sampletaw.dao.ClienteRepository;
import es.taw.sampletaw.dao.ConversacionRepository;
import es.taw.sampletaw.dao.EmpleadoRepository;
import es.taw.sampletaw.dao.MensajeRepository;
import es.taw.sampletaw.dto.ClienteDTO;
import es.taw.sampletaw.dto.ConversacionDTO;
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
public class MensajeService {

    @Autowired
    protected MensajeRepository mensajeRepository;

    @Autowired
    protected ConversacionService conversacionService;

    @Autowired
    protected ConversacionRepository conversacionRepository;

    @Autowired
    protected EmpleadoRepository empleadoRepository;
    @Autowired
    protected ClienteRepository clienteRepository;

    public MensajeDTO crearMensaje(Integer idChat, String textoMensaje, Byte soyAsistente) {
        MensajeDTO mensajeDTO = new MensajeDTO();
        ConversacionDTO conversacion = this.conversacionService.buscarConversacion(idChat);
        mensajeDTO.setConversacionByConversacion(conversacion);
        mensajeDTO.setContenido(textoMensaje);
        Timestamp fechaMsg = new Timestamp(System.currentTimeMillis());
        mensajeDTO.setFecha(fechaMsg);

        if(soyAsistente == 0){
            mensajeDTO.setClienteByEmisorCliente(conversacion.getCliente());
            mensajeDTO.setEmpleadoByReceptorEmpleado(conversacion.getEmpleado());
        }else{
            mensajeDTO.setClienteByReceptorCliente(conversacion.getCliente());
            mensajeDTO.setEmpleadoByEmisorEmpleado(conversacion.getEmpleado());
        }
        return  mensajeDTO;
    }

    public void guardar(MensajeDTO dto) {
        Mensaje mensaje = new Mensaje();
        mensaje.setFecha(dto.getFecha());
        mensaje.setContenido(dto.getContenido());
        Conversacion conversacion = this.conversacionRepository.findById(dto.getConversacionByConversacion().getId()).orElse(null);
        mensaje.setConversacionByConversacion(conversacion);
        if(dto.getEmpleadoByEmisorEmpleado() == null){
            Cliente emisorCliente = this.clienteRepository.findById(dto.getClienteByEmisorCliente().getId()).orElse(null);
            Empleado receptorEmpleado = this.empleadoRepository.findById(dto.getEmpleadoByReceptorEmpleado().getId()).orElse(null);
            mensaje.setEmpleadoByReceptorEmpleado(receptorEmpleado);
            mensaje.setClienteByEmisorCliente(emisorCliente);

        }else{
            Empleado emisorEmpleado = this.empleadoRepository.findById(dto.getEmpleadoByEmisorEmpleado().getId()).orElse(null);
            mensaje.setEmpleadoByEmisorEmpleado(emisorEmpleado);
            Cliente receptorCliente = this.clienteRepository.findById(dto.getClienteByReceptorCliente().getId()).orElse(null);
            mensaje.setClienteByReceptorCliente(receptorCliente);
        }

        this.mensajeRepository.save(mensaje);
    }

    public List<MensajeDTO> listarMensajes(ConversacionDTO conversacion) {
        List<Mensaje> mensajes = this.mensajeRepository.mensajesPorConversacion(conversacion.getId());
        return this.listaEntidadesADTO(mensajes);
    }

    private List<MensajeDTO> listaEntidadesADTO(List<Mensaje> mensajes) {
        ArrayList dtos = new ArrayList<MensajeDTO>();
        mensajes.forEach((final Mensaje mensaje) -> dtos.add(mensaje.toDTO()));
        return dtos;
    }
}
