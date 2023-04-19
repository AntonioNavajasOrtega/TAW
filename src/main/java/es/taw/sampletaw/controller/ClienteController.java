package es.taw.sampletaw.controller;

import es.taw.sampletaw.dao.*;
import es.taw.sampletaw.entity.*;
import es.taw.sampletaw.ui.FiltroOperaciones;
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
        return this.procesarFiltrado(null,idcliente,model);
    }

    @GetMapping("/nuevo")
    public String doNuevo (Model model) {
        return this.mostrarEditarONuevo(new Cliente(), model);
    }

    private String mostrarEditarONuevo(Cliente cliente, Model model) {
        /*Solicitud solicitud = new Solicitud();
        solicitud.setClienteByClienteId(cliente);
        solicitud.setFecha(new Timestamp(System.currentTimeMillis()));
        solicitud.setEmpleadoByEmpleadoId();//Nose como sacar el gestor
        TipoSolicitud tipo = this.tipoSolicitudRepository.findSolicitarCuent();
        solicitud.setTipoSolicitudByTipo(tipo);

        cliente.getSolicitudsById().add(solicitud);
        this.solicitudRepository.save(solicitud);*/
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
        Cuenta orig = this.cuentaRepository.getById(transaccion.getCuentaByCuentaOrigenId().getId());
        Cuenta dest = this.cuentaRepository.getById(transaccion.getCuentaByCuentaDestinoId().getId());

        Transaccion transaccion1 = new Transaccion();
        transaccion.setFecha(date);
        transaccion1.setFecha(date);
        transaccion1.setCantidad(transaccion.getCantidad().negate());
        transaccion1.setCuentaByCuentaOrigenId(orig);
        transaccion1.setCuentaByCuentaDestinoId(dest);
        transaccion1.setTipoTransaccionByTipo(transaccion.getTipoTransaccionByTipo());

        orig.getTransaccionsById().add(transaccion1);
        dest.getTransaccionsById().add(transaccion);
        orig.setSaldo(orig.getSaldo().subtract(transaccion.getCantidad()));
        dest.setSaldo(dest.getSaldo().add(transaccion.getCantidad()));
        this.cuentaRepository.save(orig);
        this.cuentaRepository.save(dest);
        this.transaccionRepository.save(transaccion);
        this.transaccionRepository.save(transaccion1);
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

    @PostMapping("/filtrarop")
    public String doFiltrar(@ModelAttribute("filtro")FiltroOperaciones filtro,Model model){
        Integer idcliente = filtro.getClienteid();
        return procesarFiltrado(filtro,idcliente,model);
    }

    protected String procesarFiltrado(FiltroOperaciones filtro, Integer idcliente,Model model) {
        Cliente cliente = this.clienteRepository.findById(idcliente).orElse(null);
        List<Conversacion> conversaciones = cliente.getConversacionsById().stream().filter(conversacion -> conversacion.getAbierta()==1).collect(Collectors.toList());
        List<Transaccion> transacciones;

        if(filtro == null || (filtro.getCuentadestino().isEmpty() && filtro.getDate().isEmpty())){
            transacciones = this.cuentaRepository.findClienteTrans(cliente.getId());
            filtro = new FiltroOperaciones();
        }else{
            if(filtro.getCuentadestino().isEmpty()){
                transacciones = this.cuentaRepository.findDateTrans(cliente.getId());
            }else if(filtro.getDate().isEmpty()){
                transacciones = this.cuentaRepository.findDestTrans(cliente.getId());
            }else{
                transacciones = this.cuentaRepository.findDestDateTrans(cliente.getId());
            }
        }

        model.addAttribute("transacciones",transacciones);
        model.addAttribute("filtro",filtro);
        model.addAttribute("cliente", cliente);
        model.addAttribute("conversaciones", conversaciones);
        return "cliente";
    }

}
