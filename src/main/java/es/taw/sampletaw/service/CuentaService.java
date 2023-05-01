package es.taw.sampletaw.service;

import es.taw.sampletaw.dao.CuentaRepository;
import es.taw.sampletaw.dao.EmpresaRepository;
import es.taw.sampletaw.dto.*;
import es.taw.sampletaw.entity.Cliente;
import es.taw.sampletaw.entity.Cuenta;
import es.taw.sampletaw.entity.Transaccion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CuentaService {

    @Autowired
    protected CuentaRepository cuentaRepository;

    @Autowired
    protected EmpresaRepository empresaRepository;

    public CuentaDTO buscarPorEmpresa(EmpresaDTO empresa) {
        return  cuentaRepository.findByEmpresa(empresa.getId()).toDTO();
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
}
