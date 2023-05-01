package es.taw.sampletaw.service;

import es.taw.sampletaw.dao.ClienteRepository;
import es.taw.sampletaw.dao.CuentaRepository;
import es.taw.sampletaw.dao.EmpresaRepository;
import es.taw.sampletaw.dao.SolicitudRepository;
import es.taw.sampletaw.dto.ClienteDTO;
import es.taw.sampletaw.dto.EmpresaDTO;
import es.taw.sampletaw.entity.Cuenta;
import es.taw.sampletaw.entity.Empresa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmpresaService {

    @Autowired
    protected EmpresaRepository empresaRepository;

    @Autowired
    protected SolicitudRepository solicitudRepository;

    @Autowired
    protected ClienteRepository clienteRepository;

    @Autowired
    protected CuentaRepository cuentaRepository;

    public void guardar(EmpresaDTO empresa) {
        Empresa empresa1 = new Empresa();
        empresa1.setId(empresa.getId());
        empresa1.setDireccion(empresa.getDireccion());
        empresa1.setNombre(empresa.getNombre());
        empresa1.setTelefono(empresa.getTelefono());
        empresa1.setSolicitudsById(solicitudRepository.findByEmpresa(empresa.getId()));
        empresa1.setClientesById(clienteRepository.findByEmpresa(empresa1.getId()));
        List<Cuenta> list = new ArrayList<>();
        list.add(cuentaRepository.findByEmpresa(empresa1.getId()));
        empresa1.setCuentasById(list);

        empresaRepository.save(empresa1);
    }

    public EmpresaDTO buscarPorId(int idempresa) {
        return empresaRepository.findById(idempresa).get().toDTO();
    }
}
