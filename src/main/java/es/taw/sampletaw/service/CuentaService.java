package es.taw.sampletaw.service;

import es.taw.sampletaw.dao.*;
import es.taw.sampletaw.dto.*;
import es.taw.sampletaw.entity.Cliente;
import es.taw.sampletaw.entity.Cuenta;
import es.taw.sampletaw.entity.Transaccion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Javier Serrano Contreras 40%
 */
@Service
public class CuentaService {

    @Autowired
    protected CuentaRepository cuentaRepository;

    @Autowired
    protected EmpresaRepository empresaRepository;

    @Autowired
    protected ClienteRepository clienteRepository;

    @Autowired
    protected EmpleadoRepository empleadoRepository;

    @Autowired
    protected TipoClienteRepository tipoClienteRepository;

    @Autowired
    protected SolicitudRepository solicitudRepository;

    @Autowired
    protected TransaccionRepository transaccionRepository;

    @Autowired
    protected EstadoCuentaRepository estadoCuentaRepository;

    @Autowired
    protected  EmpleadoService empleadoService;

    @Autowired
    protected EstadoCuentaService estadoCuentaService;


    public CuentaDTO buscarPorEmpresa(EmpresaDTO empresa) {
        if(cuentaRepository.findByEmpresa(empresa.getId()) == null)
        {
            return null;
        }
        else
        {
            return  cuentaRepository.findByEmpresa(empresa.getId()).toDTO();
        }

    }
    public List<TransaccionDTO> buscarEmpresaTrans(EmpresaDTO empresa) {

      return   listaTransaccionADTO(cuentaRepository.findEmpresaTrans(empresaRepository.getById(empresa.getId())));
    }

    private List<TransaccionDTO> listaTransaccionADTO(List<Transaccion> transaccions) {
        ArrayList dtos = new ArrayList<>();
        transaccions.forEach((final Transaccion transaccion) -> dtos.add(transaccion.toDTO()));
        return dtos;
    }

    public CuentaDTO buscarPorIdCuenta(Integer idcuenta) {
        return cuentaRepository.findById(idcuenta).get().toDTO();
    }

    public List<CuentaDTO> buscarCuentas(Integer idcuenta) {
        return listaCuentasADTO(cuentaRepository.findCuentas(idcuenta));
    }

    private List<CuentaDTO> listaCuentasADTO(List<Cuenta> cuentas) {
        ArrayList dtos = new ArrayList<>();
        cuentas.forEach((final Cuenta cuenta) -> dtos.add(cuenta.toDTO()));
        return dtos;
    }

    public void guardar(CuentaDTO c) {
        Cuenta cuenta;
        if(c.getId() == null)
        {
            cuenta = new Cuenta();
        }
        else
        {
            cuenta = cuentaRepository.findById(c.getId()).orElse(null);
        }

        cuenta.setSaldo(c.getSaldo());

        cuenta.setIban(c.getIban());
        cuenta.setPais(c.getPais());
        cuenta.setSwift(c.getSwift());

        cuenta.setClienteByClienteId(clienteRepository.findById(c.getClienteByClienteId().getId()).orElse(null));
        cuenta.setEstadoCuentaByEstado(estadoCuentaRepository.findById(c.getEstadoCuentaByEstado().getId()).orElse(null));
        cuenta.setEmpleadoByEmpleadoId(empleadoRepository.findById(c.getEmpleadoByEmpleadoId().getId()).orElse(null));
        if(c.getEmpresaByEmpresaId() != null)
        {
            cuenta.setEmpresaByEmpresaId(empresaRepository.findById(c.getEmpresaByEmpresaId().getId()).orElse(null));
        }


        cuentaRepository.save(cuenta);
        c.setId(cuenta.getId());
    }

    public List<TransaccionDTO> findClienteTrans(ClienteDTO cliente) {
       return listaTransaccionADTO(this.cuentaRepository.findClienteTrans(cliente.getId()));
    }

    public List<TransaccionDTO> findDateTrans(ClienteDTO cliente) {
        return listaTransaccionADTO( this.cuentaRepository.findDateTrans(cliente.getId()));
    }

    public List<TransaccionDTO> findDestTrans(ClienteDTO cliente) {
        return listaTransaccionADTO( this.cuentaRepository.findDestTrans(cliente.getId()));
    }

    public List<TransaccionDTO> findDestDateTrans(ClienteDTO cliente) {
        return listaTransaccionADTO(this.cuentaRepository.findDestDateTrans(cliente.getId()));
    }

    public List<CuentaDTO> listarCuentasCliente(Integer idcliente){
        return this.listaCuentasADTO(this.cuentaRepository.cuentasCliente(idcliente));
    }

    public CuentaDTO crearCuentaBloqueada(ClienteDTO cliente) {
        CuentaDTO c = new CuentaDTO();
        EmpleadoDTO gestor = empleadoService.buscarGestor();

        c.setIban("00000000");
        c.setSaldo(BigDecimal.valueOf(0));
        c.setClienteByClienteId(cliente);
        EstadoCuentaDTO estado = estadoCuentaService.bloqueada();
        c.setEstadoCuentaByEstado(estado);
        c.setSwift("342");
        c.setPais("-----");
        c.setEmpleadoByEmpleadoId(gestor);
        c.setEmpresaByEmpresaId(cliente.getEmpresa());
        return  c;
    }
}
