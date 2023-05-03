package es.taw.sampletaw.controller;

import es.taw.sampletaw.dao.*;
import es.taw.sampletaw.dto.*;

import es.taw.sampletaw.entity.*;
import es.taw.sampletaw.service.*;
import es.taw.sampletaw.ui.FiltroOperaciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/cliente")
@Controller
public class ClienteController {

    @Autowired
    protected ClienteService clienteService;

    @Autowired
    protected CuentaService cuentaService;

    @Autowired
    protected TipoTransaccionService tipoTransaccionService;

    @Autowired
    protected TransaccionService transaccionService;

    @Autowired
    protected SolicitudService solicitudService;

    @Autowired
    protected EmpleadoService empleadoService;

    @Autowired
    protected TipoSolicitudService tipoSolicitudService;

    @Autowired
    protected EstadoCuentaService estadoCuentaService;

    @Autowired
    protected ConversacionService conversacionService;

    @GetMapping("/")
    public String doListar(@RequestParam("id") Integer idcliente, Model model,HttpSession session){
        return this.procesarFiltrado(null,idcliente,model,session);
    }

    @GetMapping("/nuevo")
    public String doNuevo (Model model) {
        return this.mostrarEditarONuevo(new ClienteDTO(), model);
    }

    private String mostrarEditarONuevo(ClienteDTO cliente, Model model) {
        model.addAttribute("cliente",cliente);
        return "registroCliente";
    }

    @PostMapping("/guardar")
    public String doGuardar(@ModelAttribute("cliente") ClienteDTO cliente){
        if(this.cuentaService.listarCuentasCliente(cliente.getId()) == null){
            this.clienteService.guardar(cliente);
            SolicitudDTO solicitud = new SolicitudDTO();
            solicitud.setClienteByClienteId(cliente);
            solicitud.setFecha(new Timestamp(System.currentTimeMillis()));
            EmpleadoDTO gestor = this.empleadoService.buscarGestor();
            solicitud.setEmpleadoByEmpleadoId(gestor);
            TipoSolicitudDTO tipo = this.tipoSolicitudService.solicitarCuenta();
            solicitud.setTipoSolicitudByTipo(tipo);
            CuentaDTO c = new CuentaDTO();
            c.setIban("00000000");
            c.setSaldo(BigDecimal.valueOf(0));
            c.setClienteByClienteId(cliente);
            EstadoCuentaDTO estado = this.estadoCuentaService.bloqueada();
            c.setEstadoCuentaByEstado(estado);
            c.setSwift("342");
            c.setPais("-----");
            c.setEmpleadoByEmpleadoId(gestor);
            this.cuentaService.guardar(c);
            solicitud.setCuentaByCuentaId(c);

            List<SolicitudDTO> sol = new ArrayList<SolicitudDTO>();
            sol.add(solicitud);
            this.solicitudService.guardar(solicitud,cliente,null,gestor);
        }
        this.clienteService.guardar(cliente);
        return "redirect:/cliente/?id=" + cliente.getId();
    }

    @GetMapping("/editar")
    public String doEditar(@RequestParam("id") Integer idcliente,Model modelo){
        ClienteDTO cliente = this.clienteService.buscarCliente(idcliente);
        return this.mostrarEditarONuevo(cliente,modelo);
    }

    protected String pasarAOperacion(Integer idcuenta,Model model, TransaccionDTO transaccion){
        CuentaDTO cuenta = this.cuentaService.buscarPorIdCuenta(idcuenta);
        model.addAttribute("cuenta",cuenta);
        List<CuentaDTO> cuentas = this.cuentaService.buscarCuentas(cuenta.getId());
        model.addAttribute("cuentas",cuentas);
        transaccion.setCuentaByCuentaOrigenId(cuenta);
        model.addAttribute("trans",transaccion);
        model.addAttribute("volver",idcuenta);

        if(transaccion.getTipoTransaccionByTipo().getTipo().equals("Pago")){
            return "transaccion";
        }else{
            return "cambioDivisas";
        }
    }

    @GetMapping("/transaccion")
    public String doTransaccion(@RequestParam("id") Integer idcuenta, Model model){
        TransaccionDTO transaccion = new TransaccionDTO();
        TipoTransaccionDTO tipo = this.tipoTransaccionService.transaccion();
        transaccion.setTipoTransaccionByTipo(tipo);
        return this.pasarAOperacion(idcuenta,model,transaccion);
    }

    @GetMapping("/cambio")
    public String doCambio(@RequestParam("id") Integer idcuenta, Model model){
        TransaccionDTO transaccion = new TransaccionDTO();
        TipoTransaccionDTO tipo = this.tipoTransaccionService.cambio();
        transaccion.setTipoTransaccionByTipo(tipo);
        return this.pasarAOperacion(idcuenta,model,transaccion);
    }

    @PostMapping("/realizar")
    public String doRealizar(@ModelAttribute("trans") TransaccionDTO transaccionDTO
    ,@RequestParam("volver") int volver){
        Timestamp date = new Timestamp(System.currentTimeMillis());
        TransaccionDTO transaccion = this.transaccionService.buscarPorIdTransaccion(transaccionDTO.getId());
        CuentaDTO orig = this.cuentaService.buscarPorIdCuenta(transaccion.getCuentaByCuentaOrigenId().getId());
        CuentaDTO dest = this.cuentaService.buscarPorIdCuenta(transaccion.getCuentaByCuentaDestinoId().getId());
        TransaccionDTO transaccion1 = new TransaccionDTO();
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
        this.cuentaService.guardar(orig);
        this.cuentaService.guardar(dest);
        this.transaccionService.guardar(transaccion);
        this.transaccionService.guardar(transaccion1);
        if(orig.getClienteByClienteId().getEmpresa() == null)
        {
            return "redirect:/cliente/?id=" + orig.getClienteByClienteId().getId();
        }
        else
        {
            return "redirect:/empresa/?id=" + volver;
        }

    }

    @PostMapping("/cambiarmoneda")
    public String doCambiarMoneda(@ModelAttribute("trans") TransaccionDTO transaccionDTO
            ,@RequestParam("volver") int volver){

        TransaccionDTO transaccion = this.transaccionService.buscarPorIdTransaccion(transaccionDTO.getId());

        CuentaDTO cuenta = this.cuentaService.buscarPorIdCuenta(transaccion.getCuentaByCuentaOrigenId().getId());


        Timestamp date = new Timestamp(System.currentTimeMillis());
        transaccion.setFecha(date);
        transaccion.setCantidad(cuenta.getSaldo());
        transaccion.setCuentaByCuentaDestinoId(cuenta);


        cuenta.getTransaccionsById().add(transaccion);
        if(transaccion.getMoneda().equals("usd")){
            cuenta.setSaldo(transaccion.getCantidad().multiply(BigDecimal.valueOf(1.09708)));
        }else if(transaccion.getMoneda().equals("eur")){
            cuenta.setSaldo(transaccion.getCantidad().divide(BigDecimal.valueOf(1.09708), RoundingMode.HALF_EVEN));
        }
        this.cuentaService.guardar(cuenta);
        this.transaccionService.guardar(transaccion);
        if(cuenta.getClienteByClienteId().getEmpresa() == null)
        {
            return "redirect:/cliente/?id=" + cuenta.getClienteByClienteId().getId();
        }else
        {
            return "redirect:/empresa/?id=" + volver;
        }

    }

    @GetMapping("/solicitar")
    public String doSolicitar(@RequestParam("id") Integer idcuenta,Model model){
        CuentaDTO cuenta = this.cuentaService.buscarPorIdCuenta(idcuenta);
        ClienteDTO cliente = cuenta.getClienteByClienteId();
        EmpleadoDTO empleado = cuenta.getEmpleadoByEmpleadoId();
        TipoSolicitudDTO tipo = this.tipoSolicitudService.activar();

        Timestamp date = new Timestamp(System.currentTimeMillis());
        SolicitudDTO solicitud = new SolicitudDTO();
        solicitud.setClienteByClienteId(cliente);
        solicitud.setFecha(date);
        solicitud.setTipoSolicitudByTipo(tipo);
        solicitud.setCuentaByCuentaId(cuenta);
        solicitud.setEmpleadoByEmpleadoId(empleado);

        this.solicitudService.guardar(solicitud,cliente,cuenta,empleado);
        return "redirect:/cliente/?id=" + cliente.getId();
    }

    @PostMapping("/filtrarop")
    public String doFiltrar(@ModelAttribute("filtro")FiltroOperaciones filtro, Model model,
                            HttpSession session){
        Integer idcliente = filtro.getClienteid();
        return procesarFiltrado(filtro,idcliente,model,session);
    }

    protected String procesarFiltrado(FiltroOperaciones filtro, Integer idcliente,Model model,HttpSession session) {
        ClienteDTO cliente = this.clienteService.buscarCliente(idcliente);
        ClienteDTO clienteSession = (ClienteDTO) session.getAttribute("clienteSession");
        List<TransaccionDTO> transacciones;

        String urlTo = "cliente";
        if(clienteSession == null || cliente.getId() != clienteSession.getId()){
            urlTo = "redirect:/logout";
        }else{
            List<ConversacionDTO> conversaciones = this.conversacionService.listarConversacionesAbiertasDeUnCliente(idcliente);
            List<CuentaDTO> cuentas = this.cuentaService.listarCuentasCliente(cliente.getId());
            if(filtro == null || (filtro.getCuentadestino().isEmpty() && filtro.getDate().isEmpty())){
                transacciones = this.cuentaService.findClienteTrans(cliente);
                filtro = new FiltroOperaciones();
            }else{
                if(filtro.getCuentadestino().isEmpty()){
                    transacciones = this.cuentaService.findDateTrans(cliente);
                }else if(filtro.getDate().isEmpty()){
                    transacciones = this.cuentaService.findDestTrans(cliente);
                }else{
                    transacciones = this.cuentaService.findDestDateTrans(cliente);
                }
            }
            model.addAttribute("transacciones",transacciones);
            model.addAttribute("conversaciones", conversaciones);
            model.addAttribute("cuentas", cuentas);
        }

        model.addAttribute("filtro",filtro);
        model.addAttribute("cliente", cliente);
        return urlTo;
    }

}
