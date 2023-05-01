package es.taw.sampletaw.service;

import es.taw.sampletaw.dao.ClienteRepository;
import es.taw.sampletaw.dao.CuentaRepository;
import es.taw.sampletaw.dao.TipoClienteRepository;
import es.taw.sampletaw.dao.TipoclienterelacionadoRepository;
import es.taw.sampletaw.dto.ClienteDTO;
import es.taw.sampletaw.dto.CuentaDTO;
import es.taw.sampletaw.dto.MensajeDTO;
import es.taw.sampletaw.dto.TipoclienterelacionadoDTO;
import es.taw.sampletaw.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TipoclienterelacionadoService {

    @Autowired
    protected TipoclienterelacionadoRepository tipoclienterelacionadoRepository;

    @Autowired
    protected ClienteRepository clienteRepository;

    @Autowired
    protected CuentaRepository cuentaRepository;

    @Autowired
    protected TipoClienteRepository tipoClienteRepository;

    public List<TipoclienterelacionadoDTO> listarTodos() {
        return listaEntidadesADTO(tipoclienterelacionadoRepository.findAll());
    }


    private List<TipoclienterelacionadoDTO> listaEntidadesADTO(List<Tipoclienterelacionado> tipoclienterelacionados) {
        ArrayList dtos = new ArrayList<>();
        tipoclienterelacionados.forEach((final Tipoclienterelacionado tpo) -> dtos.add(tpo.toDTO()));
        return dtos;
    }

    public TipoclienterelacionadoDTO buscarPorCliente(ClienteDTO clienteDTO) {
        return tipoclienterelacionadoRepository.findByCliente(clienteDTO.getId()).toDTO();
    }


    public void guardar(TipoclienterelacionadoDTO tipoclienterelacionado,int cuentaid, int clienteid) {
        Tipoclienterelacionado tipoclienterelacionado1 = new Tipoclienterelacionado();



        tipoclienterelacionado1.setBloqueado(tipoclienterelacionado.getBloqueado());
        tipoclienterelacionado1.setTipoClienteByTipo(tipoClienteRepository.findTipo(tipoclienterelacionado.getTipo().getTipo()));
        tipoclienterelacionado1.setCuentaByCuentaId(cuentaRepository.findById(cuentaid).orElse(null));
        tipoclienterelacionado1.setClienteByClienteId(clienteRepository.findById(clienteid).orElse(null));

        TipoclienterelacionadoPK pk = new TipoclienterelacionadoPK();
        pk.setCuentaId(cuentaid);
        pk.setClienteId(clienteid);

        tipoclienterelacionado1.setTipoclienterelacionadoPK(pk);



        tipoclienterelacionadoRepository.save(tipoclienterelacionado1);
    }
}
