package es.taw.sampletaw.service;

import es.taw.sampletaw.dao.ClienteRepository;
import es.taw.sampletaw.dto.ClienteDTO;
import es.taw.sampletaw.dto.MensajeDTO;
import es.taw.sampletaw.entity.Cliente;
import es.taw.sampletaw.entity.Conversacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteService {

    @Autowired
    protected ClienteRepository clienteRepository;


    public ClienteDTO buscarCliente(Integer idCliente) {
        Cliente cliente = this.clienteRepository.findById(idCliente).orElse(null);
        if(cliente != null){
            return cliente.toDTO();
        }else{
            return null;
        }

    }

    public List<ClienteDTO> listarClientesConMensajes() {
        List<Cliente> clientes = this.clienteRepository.buscarClientesConMensajes();
        return this.listaEntidadesADTO(clientes);
    }

    public List<ClienteDTO> listarClientes() {
        List<Cliente> clientes = this.clienteRepository.findAll();
        return this.listaEntidadesADTO(clientes);
    }

    private List<ClienteDTO> listaEntidadesADTO(List<Cliente> clientes) {
        ArrayList dtos = new ArrayList<MensajeDTO>();
        clientes.forEach((final Cliente cliente) -> dtos.add(cliente.toDTO()));
        return dtos;
    }
}
