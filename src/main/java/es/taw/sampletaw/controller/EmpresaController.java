package es.taw.sampletaw.controller;

import es.taw.sampletaw.dao.*;
import es.taw.sampletaw.entity.*;
import es.taw.sampletaw.ui.FiltroApellido;
import es.taw.sampletaw.ui.FiltroOperaciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/empresa")
public class EmpresaController {

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
    protected EmpresaRepository empresaRepository;

    @Autowired
    protected ClienteRepository clienteRepository;

    @Autowired
    protected TipoSolicitudRepository tipoSolicitudRepository;

    @Autowired
    protected EstadoCuentaRepository estadoCuentaRepository;

    @Autowired
    protected  TipoclienterelacionadoRepository tipoclienterelacionadoRepository;

    @Autowired
    protected  TipoClienteRepository tipoClienteRepository;


    @GetMapping("/")
    public String doListar(@RequestParam("id") Integer idcliente, Model model, HttpSession session){

        Cliente cliente = this.clienteRepository.findById(idcliente).orElse(null);
        Cliente clienteSession = (Cliente) session.getAttribute("clienteSession");
        if(clienteSession == null || cliente.getId() != clienteSession.getId()) {
            return  "redirect:/logout";
        }


        List<Conversacion> conversaciones = cliente.getConversacionsById().stream().filter(conversacion -> conversacion.getAbierta()==1).collect(Collectors.toList());
        model.addAttribute("cliente", cliente);
        model.addAttribute("conversaciones", conversaciones);
        Empresa empresa = cliente.getEmpresaByEmpresaId();
        model.addAttribute("empresa", empresa);
        List<Cliente> lista =  empresa.getClientesById().stream().collect(Collectors.toList());
        model.addAttribute("clientesSocios",lista);
        FiltroApellido filtroApellido = new FiltroApellido();
        FiltroOperaciones filtro = new FiltroOperaciones();
        model.addAttribute("filtro",filtro);
        filtroApellido.setIdEmpresa(empresa.getId());
        model.addAttribute("filtroApellido",filtroApellido);
        List<Transaccion> transacciones;
        transacciones = this.cuentaRepository.findEmpresaTrans(empresa);
        model.addAttribute("transacciones",transacciones);
        model.addAttribute("lista",clienteRepository.clientesSocios(empresa));
        Tipoclienterelacionado x = tipoclienterelacionadoRepository.findByCliente(cliente.getId());
        model.addAttribute("tablaIntermedia",x);
        model.addAttribute("all",tipoclienterelacionadoRepository.findAll());



        return "empresa";
    }

    @GetMapping("/editarEmpresa")
    public String doEditarEmpresa(@RequestParam("id") Integer idcliente,Model modelo){
        Cliente cliente = this.clienteRepository.findById(idcliente).orElse(null);
        Empresa empresa = cliente.getEmpresaByEmpresaId();
        modelo.addAttribute("empresa",empresa);
        modelo.addAttribute("cliente",cliente);
        return "editarEmpresa";
    }

    @GetMapping("/anadirCliente")
    public String doAnadirCliente(@RequestParam("id") Integer idcliente,Model modelo){
        Cliente cliente = this.clienteRepository.findById(idcliente).orElse(null);
        modelo.addAttribute("empresa",cliente.getEmpresaByEmpresaId());
        modelo.addAttribute("cliente",new Cliente());
        modelo.addAttribute("volver",cliente);
        return "anadirCliente";
    }

    @GetMapping("/editarCliente")
    public String doEditarCliente(@RequestParam("id") Integer idcliente,Model modelo){
        Cliente cliente = this.clienteRepository.findById(idcliente).orElse(null);
        modelo.addAttribute("empresaID",cliente.getEmpresaByEmpresaId());
        modelo.addAttribute("cliente",cliente);
        return "editarCliente";
    }


    @GetMapping("/nuevo")
    public String doNuevo (Model model) {
        return this.mostrarEditarONuevo(new Empresa(), model);
    }

    private String mostrarEditarONuevo(Empresa empresa, Model model) {

        model.addAttribute("empresa",empresa);

        return "registroEmpresa";
    }

    @GetMapping("/guardar")
    public String doGuardar(@ModelAttribute("empresa") Empresa empresa){
        this.empresaRepository.save(empresa);
        return "redirect:/empresa/nuevoCliente/?id=" + empresa.getId();
    }
    @GetMapping("/guardar2")
    public String doGuardar2(@ModelAttribute("empresa") Empresa empresa, @RequestParam("volver") int volver){

        this.empresaRepository.save(empresa);
        Empresa empresa2 = this.empresaRepository.getById(empresa.getId());

        return "redirect:/empresa/?id=" + volver;

    }
    @GetMapping("/nuevoCliente")
    public String doNuevoCliente (Model model, @RequestParam("id") int idempresa) {
        return this.mostrarEditarONuevo2(new Cliente(), model,idempresa);
    }

    private String mostrarEditarONuevo2(Cliente cliente, Model model,int idempresa) {

        model.addAttribute("cliente",cliente);
        model.addAttribute("empresaID",this.empresaRepository.findById(idempresa).get());

        return "registroClienteEmpresa";
    }

    @GetMapping("/guardarCliente")
    public String doGuardarCliente(@ModelAttribute("cliente") Cliente cliente,@RequestParam("empresa_id") int idempresa
    ,@RequestParam("tipoCliente") String str)
    {
        Empresa empresa = this.empresaRepository.findById(idempresa).get();
        cliente.setEmpresaByEmpresaId(empresa);
        asociarCuenta(cliente,str);
        this.clienteRepository.save(cliente);
        return "redirect:/empresa/?id=" + cliente.getId();
    }

    @GetMapping("/guardarCliente2")
    public String doGuardarCliente2(@ModelAttribute("cliente") Cliente cliente,@RequestParam("empresa_id") int idempresa
    ,@RequestParam("volver") int volver,@RequestParam("tipoCliente") String str)
    {
        Empresa empresa = this.empresaRepository.findById(idempresa).get();
        cliente.setEmpresaByEmpresaId(empresa);

        asociarCuenta(cliente,str);
        this.clienteRepository.save(cliente);
        return "redirect:/empresa/?id=" + volver;
    }

    @GetMapping("/guardarClienteEditado")
    public String guardarClienteEditado(@ModelAttribute("cliente") Cliente cliente)
    {
        this.clienteRepository.save(cliente);
        return "redirect:/empresa/?id=" + cliente.getId();
    }

    @PostMapping("/filtradoSocios")
    public String filtrar(@ModelAttribute("filtroApellido") FiltroApellido filtroApellido,Model model,@RequestParam("volver")int volver,HttpSession session)
    {
        List<Cliente> lista;
        Empresa empresa = empresaRepository.findById(filtroApellido.getIdEmpresa()).orElse(null);

        if(filtroApellido.getApellido().equals("NONE"))
        {
             lista =  empresa.getClientesById().stream().collect(Collectors.toList());
        }
        else
        {
             lista =  empresa.getClientesById().stream().filter(cliente -> cliente.getApellido()
                            .equalsIgnoreCase(filtroApellido.getApellido())).collect(Collectors.toList());
        }

        model.addAttribute("clientesSocios",lista);

        Cliente cliente = clienteRepository.findById(volver).orElse(null);
        List<Conversacion> conversaciones = cliente.getConversacionsById().stream().filter(conversacion -> conversacion.getAbierta()==1).collect(Collectors.toList());
        model.addAttribute("cliente", cliente);
        model.addAttribute("conversaciones", conversaciones);
        model.addAttribute("empresa", empresa);
        model.addAttribute("filtroApellido",filtroApellido);
        List<Transaccion> transacciones;
        transacciones = this.cuentaRepository.findEmpresaTrans(empresa);
        model.addAttribute("transacciones",transacciones);
        FiltroOperaciones filtro = new FiltroOperaciones();
        model.addAttribute("filtro",filtro);
        model.addAttribute("lista",clienteRepository.clientesSocios(empresa));
        Tipoclienterelacionado x = tipoclienterelacionadoRepository.findByCliente(cliente.getId());
        model.addAttribute("tablaIntermedia",x);
        model.addAttribute("all",tipoclienterelacionadoRepository.findAll());

        Cliente clienteSession = (Cliente) session.getAttribute("clienteSession");
        if(clienteSession == null || cliente.getId() != clienteSession.getId()) {
            return  "redirect:/logout";
        }

        return "empresa";
    }

    @GetMapping("/solicitar")
    public String doSolicitar(@RequestParam("id") Integer idcuenta,Model model
            , @RequestParam("idcliente") Integer id){
        Cuenta cuenta = this.cuentaRepository.findById(idcuenta).orElse(null);
        Cliente cliente = clienteRepository.findById(id).orElse(null);
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
        return "redirect:/empresa/?id=" + cliente.getId();
    }

    private void asociarCuenta(Cliente cliente, String tipoCliente)
    {
        Empresa empresa = cliente.getEmpresaByEmpresaId();

        this.clienteRepository.save(cliente);


        if(cuentaRepository.findByEmpresa(empresa) == null)
            {
                Solicitud solicitud = new Solicitud();
                solicitud.setClienteByClienteId(cliente);
                solicitud.setFecha(new Timestamp(System.currentTimeMillis()));
                Empleado gestor = this.empleadoRepository.findGestor();
                solicitud.setEmpleadoByEmpleadoId(gestor);
                TipoSolicitud tipo = this.tipoSolicitudRepository.findSolicitarCuent();
                solicitud.setTipoSolicitudByTipo(tipo);
                Cuenta c = new Cuenta();
                c.setEmpresaByEmpresaId(cliente.getEmpresaByEmpresaId());
                c.setIban("00000000");
                c.setSaldo(BigDecimal.valueOf(0));
                c.setClienteByClienteId(cliente);
                EstadoCuenta estado = this.estadoCuentaRepository.findAct();
                c.setEstadoCuentaByEstado(estado);
                c.setSwift("342");
                c.setPais("-----");
                c.setEmpleadoByEmpleadoId(gestor);
                this.cuentaRepository.save(c);
                solicitud.setCuentaByCuentaId(c);

                List<Solicitud> sol = new ArrayList<Solicitud>();
                sol.add(solicitud);
                cliente.setSolicitudsById(sol);
                this.solicitudRepository.save(solicitud);
            }

            Cuenta c = cuentaRepository.findByEmpresa(empresa);

            Tipoclienterelacionado tablaIntermedia = new Tipoclienterelacionado();

            TipoclienterelacionadoPK pk = new TipoclienterelacionadoPK();
            pk.setCuentaId(c.getId());
            pk.setClienteId(cliente.getId());

            tablaIntermedia.setTipoclienterelacionadoPK(pk);
            tablaIntermedia.setCuentaByCuentaId(c);
            tablaIntermedia.setClienteByClienteId(cliente);
            tablaIntermedia.setBloqueado((byte) 1);

            TipoCliente tipocliente = tipoClienteRepository.findTipo(tipoCliente);
            tablaIntermedia.setTipoClienteByTipo(tipocliente);

            tipoclienterelacionadoRepository.save(tablaIntermedia);

    }

    @GetMapping("/transaccion")
    public String doTransaccion(@RequestParam("id") Integer idcuenta,@RequestParam("volver") int volver, Model model){
        Transaccion transaccion = new Transaccion();
        TipoTransaccion tipo = this.tipoTransaccionRepository.findTrans();
        transaccion.setTipoTransaccionByTipo(tipo);
        return this.pasarAOperacion(idcuenta,model,transaccion,volver);
    }

    @GetMapping("/cambio")
    public String doCambio(@RequestParam("id") Integer idcuenta, Model model,@RequestParam("volver") int volver){
        Transaccion transaccion = new Transaccion();
        TipoTransaccion tipo = this.tipoTransaccionRepository.findCambio();
        transaccion.setTipoTransaccionByTipo(tipo);
        return this.pasarAOperacion(idcuenta,model,transaccion,volver);
    }

    protected String pasarAOperacion(Integer idcuenta,Model model, Transaccion transaccion, int volver){
        Cuenta cuenta = this.cuentaRepository.findById(idcuenta).orElse(null);
        model.addAttribute("cuenta",cuenta);
        List<Cuenta> cuentas = this.cuentaRepository.findCuentas(cuenta.getId());
        model.addAttribute("cuentas",cuentas);
        transaccion.setCuentaByCuentaOrigenId(cuenta);
        model.addAttribute("trans",transaccion);
        model.addAttribute("volver",volver);

        if(transaccion.getTipoTransaccionByTipo().getTipo().equals("Pago")){
            return "transaccion";
        }else{
            return "cambioDivisas";
        }
    }

    @GetMapping("/bloquear")
    public String bloquear(@RequestParam("idcuenta") Integer idcuenta, Model model, @RequestParam("volver") int volver
    ,@RequestParam("bloquear") int bloquear){
        Tipoclienterelacionado tipoclienterelacionado = tipoclienterelacionadoRepository.findByCliente(bloquear);
        tipoclienterelacionado.setBloqueado((byte) 1);
        tipoclienterelacionadoRepository.save(tipoclienterelacionado);
        return "redirect:/empresa/?id=" + volver ;
    }

    @PostMapping("/filtrarop")
    public String doFiltrar(@ModelAttribute("filtro")FiltroOperaciones filtro,Model model,HttpSession session){
        Integer idcliente = filtro.getClienteid();

        return procesarFiltrado(filtro,idcliente,model,session);
    }

    protected String procesarFiltrado(FiltroOperaciones filtro, Integer idcliente,Model model,HttpSession session) {
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

        Empresa empresa = cliente.getEmpresaByEmpresaId();
        List<Cliente> lista =  empresa.getClientesById().stream().collect(Collectors.toList());
        model.addAttribute("clientesSocios",lista);
        model.addAttribute("empresa",empresa);

        model.addAttribute("transacciones",transacciones);
        model.addAttribute("filtro",filtro);
        model.addAttribute("cliente", cliente);
        model.addAttribute("conversaciones", conversaciones);
        FiltroApellido filtroApellido = new FiltroApellido();
        model.addAttribute("filtroApellido",filtroApellido);
        model.addAttribute("lista",clienteRepository.clientesSocios(empresa));
        Tipoclienterelacionado x = tipoclienterelacionadoRepository.findByCliente(cliente.getId());
        model.addAttribute("tablaIntermedia",x);
        model.addAttribute("all",tipoclienterelacionadoRepository.findAll());


        Cliente clienteSession = (Cliente) session.getAttribute("clienteSession");
        if(clienteSession == null || cliente.getId() != clienteSession.getId()) {
            return  "redirect:/logout";
        }

        return "empresa";
    }

}
