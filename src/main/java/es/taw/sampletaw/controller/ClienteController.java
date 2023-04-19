package es.taw.sampletaw.controller;

import es.taw.sampletaw.dao.*;
import es.taw.sampletaw.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/cliente")
@Controller
public class ClienteController {

    @Autowired
    protected ClienteRepository clienteRepository;

    @Autowired
    protected CuentaRepository cuentaRepository;

    @Autowired
    protected TipoTransaccionRepository tipoTransaccionRepository;

    @Autowired
    protected TransaccionRepository transaccionRepository;

    @Autowired
    protected SolicitudRepository solicitudRepository;

    @Autowired
    protected EmpleadoRepository empleadoRepository;

    @Autowired
    protected TipoSolicitudRepository tipoSolicitudRepository;

    @GetMapping("/")
    public String doListar(@RequestParam("id") Integer idcliente, Model model){
        Cliente cliente = this.clienteRepository.findById(idcliente).orElse(null);
        List<Conversacion> conversaciones = cliente.getConversacionsById().stream().filter(conversacion -> conversacion.getAbierta()==1).collect(Collectors.toList());
        model.addAttribute("cliente", cliente);
        model.addAttribute("conversaciones", conversaciones);
        return "cliente";
    }

    @GetMapping("/nuevo")
    public String doNuevo (Model model) {
        return this.mostrarEditarONuevo(new Cliente(), model);
    }

    private String mostrarEditarONuevo(Cliente cliente, Model model) {
        model.addAttribute("cliente",cliente);
        return "registroCliente";
    }

    @PostMapping("/guardar")
    public String doGuardar(@ModelAttribute("cliente") Cliente cliente){
        this.clienteRepository.save(cliente);
        return "redirect:/cliente/?id=" + cliente.getId();
    }

    @GetMapping("/editar")
    public String doEditar(@RequestParam("id") Integer idcliente,Model modelo){
        Cliente cliente = this.clienteRepository.findById(idcliente).orElse(null);
        return this.mostrarEditarONuevo(cliente,modelo);
    }

    @GetMapping("/transaccion")
    public String doTransaccion(@RequestParam("id") Integer idcuenta, Model model){
        Cuenta cuenta = this.cuentaRepository.findById(idcuenta).orElse(null);
        model.addAttribute("cuenta",cuenta);
        List<Cuenta> cuentas = this.cuentaRepository.findCuentas(cuenta.getId());
        model.addAttribute("cuentas",cuentas);
        Transaccion transaccion = new Transaccion();
        transaccion.setCuentaByCuentaOrigenId(cuenta);
        model.addAttribute("trans",transaccion);
        List<TipoTransaccion> tipos = this.tipoTransaccionRepository.findAll();
        model.addAttribute("tipos",tipos);
        return "operacion";
    }

    @PostMapping("/realizar")
    public String doRealizar(@ModelAttribute("trans") Transaccion transaccion){
        Timestamp date = new Timestamp(System.currentTimeMillis());
        transaccion.setFecha(date);
        Cuenta orig = transaccion.getCuentaByCuentaOrigenId();
        Cuenta dest = transaccion.getCuentaByCuentaDestinoId();
        orig.getTransaccionsById().add(transaccion);
        dest.getTransaccionsById().add(transaccion);
        orig.setSaldo(orig.getSaldo().subtract(transaccion.getCantidad()));
        dest.setSaldo(dest.getSaldo().add(transaccion.getCantidad()));
        this.cuentaRepository.save(orig);
        this.cuentaRepository.save(dest);
        this.transaccionRepository.save(transaccion);
        return "redirect:/cliente/?id=" + orig.getClienteByClienteId().getId();
    }

    @GetMapping("/solicitar")
    public String doSolicitar(@RequestParam("id") Integer idcuenta,Model model){
        Cuenta cuenta = this.cuentaRepository.findById(idcuenta).orElse(null);
        Cliente cliente = cuenta.getClienteByClienteId();
        Empleado empleado = cuenta.getEmpleadoByEmpleadoId();
        TipoSolicitud tipo = this.tipoSolicitudRepository.findActivar();

        Timestamp date = new Timestamp(System.currentTimeMillis());
        Solicitud solicitud = new Solicitud();
        solicitud.setClienteByClienteId(cliente);
        solicitud.setFecha(date);
        solicitud.setTipoSolicitudByTipo(tipo);
        solicitud.setCuentaByCuentaId(cuenta);
        solicitud.setEmpleadoByEmpleadoId(empleado);

        cuenta.getSolicitudsById().add(solicitud);
        cliente.getSolicitudsById().add(solicitud);
        empleado.getSolicitudsById().add(solicitud);

        this.clienteRepository.save(cliente);
        this.cuentaRepository.save(cuenta);
        this.empleadoRepository.save(empleado);
        this.solicitudRepository.save(solicitud);
        return "redirect:/cliente/?id=" + cliente.getId();
    }

}
