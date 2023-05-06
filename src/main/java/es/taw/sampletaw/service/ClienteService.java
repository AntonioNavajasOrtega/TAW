package es.taw.sampletaw.service;

import es.taw.sampletaw.dao.*;
import es.taw.sampletaw.dto.ClienteDTO;
import es.taw.sampletaw.dto.CuentaDTO;
import es.taw.sampletaw.dto.EmpresaDTO;
import es.taw.sampletaw.dto.MensajeDTO;
import es.taw.sampletaw.entity.*;
import es.taw.sampletaw.ui.FiltroApellido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Juan José Torres 40%
 * @author Javier Serrano Contreras 30%
 */
@Service
public class ClienteService {

    @Autowired
    protected ClienteRepository clienteRepository;

    @Autowired
    protected EmpresaRepository empresaRepository;

    @Autowired
    protected ConversacionRepository conversacionRepository;

    @Autowired
    protected CuentaRepository cuentaRepository;

    @Autowired
    protected MensajeRepository mensajeRepository;

    @Autowired
    protected SolicitudRepository solicitudRepository;

    @Autowired
    protected TipoclienterelacionadoRepository tipoclienterelacionadoRepository;

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

    public List<ClienteDTO> listarClientesPorEmpresa(EmpresaDTO empresaDTO) {
        List<Cliente> clientes = this.clienteRepository.findByEmpresa(empresaDTO.getId());
        return this.listaEntidadesADTO(clientes);
    }

    public List<String> listarSocios(EmpresaDTO empresa) {
        List<String> clientes = this.clienteRepository.clientesSocios(empresa.getId());
        return clientes;
    }

    public void guardar(ClienteDTO cliente) {
        Cliente cliente1;
        if(cliente.getId() == null)
        {
             cliente1 = new Cliente();
        }
        else
        {
            cliente1 = this.clienteRepository.findById(cliente.getId()).orElse(null);
        }

        cliente1.setApellido(cliente.getApellido());
        cliente1.setNombre(cliente.getNombre());
        cliente1.setDireccion(cliente.getDireccion());
        cliente1.setTelefono(cliente.getTelefono());
        cliente1.setContrasena(cliente.getContrasena());
        cliente1.setEmail(cliente.getEmail());
        cliente1.setNif(cliente.getNif());

        cliente1.setConversacionsById(conversacionRepository.buscaPorUsuarioConversacionesAbiertas(cliente.getId()));

        List<Cuenta> list =cuentaRepository.findByCliente(cliente.getId());
        if(cliente.getEmpresa() != null){
            cliente1.setEmpresaByEmpresaId(empresaRepository.findById(cliente.getEmpresa().getId()).orElse(null));
            list.add(cuentaRepository.findByEmpresa(cliente.getEmpresa().getId()));
            cliente1.setSolicitudsById(solicitudRepository.findByEmpresa(cliente.getEmpresa().getId()));
            List<Tipoclienterelacionado> lista = new ArrayList<>();
            lista.add(tipoclienterelacionadoRepository.findByCliente(cliente.getId()));
            cliente1.setTipoclienterelacionadosById(lista);
        }

        cliente1.setCuentasById(list);


        cliente1.setMensajesById(mensajeRepository.mensajesCuyoUsuarioEsEmisorOReceptor(cliente.getId()));

        clienteRepository.save(cliente1);
        cliente.setId(cliente1.getId());
    }

    public List<ClienteDTO> listarClientesPorEmpresaFiltroApellido(FiltroApellido filtroApellido) {
        List<Cliente> clientes = this.clienteRepository.findByApellido(filtroApellido.getApellido(),filtroApellido.getIdEmpresa());
        return this.listaEntidadesADTO(clientes);
    }
}
