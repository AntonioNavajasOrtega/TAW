package es.taw.sampletaw.controller;

import es.taw.sampletaw.dao.*;
import es.taw.sampletaw.dto.*;
import es.taw.sampletaw.entity.*;
import es.taw.sampletaw.service.*;
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
    protected EmpresaService empresaService;

    @Autowired
    protected ClienteService clienteService;

    @Autowired
    protected TipoSolicitudService tipoSolicitudService;

    @Autowired
    protected EstadoCuentaService estadoCuentaService;

    @Autowired
    protected  TipoclienterelacionadoService tipoclienterelacionadoService;

    @Autowired
    protected  TipoClienteService tipoClienteService;

    @Autowired
    protected ConversacionService conversacionService;


    @GetMapping("/")
    public String doListar(@RequestParam("id") Integer idcliente, Model model, HttpSession session){

        ClienteDTO cliente = this.clienteService.buscarCliente(idcliente);
        ClienteDTO clienteSession = (ClienteDTO) session.getAttribute("clienteSession");
        if(clienteSession == null || cliente.getId() != clienteSession.getId()) {
            return  "redirect:/logout";
        }

        List<ConversacionDTO> conversaciones = this.conversacionService.listarConversacionesAbiertasDeUnCliente(idcliente);
        model.addAttribute("cliente", cliente);
        model.addAttribute("conversaciones", conversaciones);
        EmpresaDTO empresa = cliente.getEmpresa();
        model.addAttribute("empresa", empresa);
        List<ClienteDTO> lista =  clienteService.listarClientesPorEmpresa(cliente.getEmpresa());
        model.addAttribute("clientesSocios",lista);
        FiltroApellido filtroApellido = new FiltroApellido();
        FiltroOperaciones filtro = new FiltroOperaciones();
        model.addAttribute("filtro",filtro);
        filtroApellido.setIdEmpresa(empresa.getId());
        model.addAttribute("filtroApellido",filtroApellido);
        List<TransaccionDTO> transacciones;
        transacciones = cuentaService.buscarEmpresaTrans(empresa);
        model.addAttribute("transacciones",transacciones);
        model.addAttribute("lista",clienteService.listarSocios(cliente.getEmpresa()));
        TipoclienterelacionadoDTO x = tipoclienterelacionadoService.buscarPorCliente(cliente);
        model.addAttribute("tablaIntermedia",x);
        model.addAttribute("all",tipoclienterelacionadoService.listarTodos());



        return "empresa";
    }

    @GetMapping("/editarEmpresa")
    public String doEditarEmpresa(@RequestParam("id") Integer idcliente,Model modelo){
        ClienteDTO cliente = this.clienteService.buscarCliente(idcliente);
        EmpresaDTO empresa = cliente.getEmpresa();
        modelo.addAttribute("empresa",empresa);
        modelo.addAttribute("cliente",cliente);
        return "editarEmpresa";
    }

    @GetMapping("/anadirCliente")
    public String doAnadirCliente(@RequestParam("id") Integer idcliente,Model modelo){
        ClienteDTO cliente = this.clienteService.buscarCliente(idcliente);
        modelo.addAttribute("empresa",cliente.getEmpresa());
        modelo.addAttribute("cliente",new ClienteDTO());
        modelo.addAttribute("volver",cliente);
        return "anadirCliente";
    }

    @GetMapping("/editarCliente")
    public String doEditarCliente(@RequestParam("id") Integer idcliente,Model modelo){
        ClienteDTO cliente = this.clienteService.buscarCliente(idcliente);
        modelo.addAttribute("empresaID",cliente.getEmpresa());
        modelo.addAttribute("cliente",cliente);
        return "editarCliente";
    }


    @GetMapping("/nuevo")
    public String doNuevo (Model model) {
        return this.mostrarEditarONuevo(new EmpresaDTO(), model);
    }

    private String mostrarEditarONuevo(EmpresaDTO empresa, Model model) {

        model.addAttribute("empresa",empresa);

        return "registroEmpresa";
    }

    @GetMapping("/guardar")
    public String doGuardar(@ModelAttribute("empresa") EmpresaDTO empresa){
        empresaService.guardar(empresa);
        return "redirect:/empresa/nuevoCliente/?id=" + empresa.getId();
    }
    @GetMapping("/guardar2")
    public String doGuardar2(@ModelAttribute("empresa") EmpresaDTO empresa, @RequestParam("volver") int volver){

        empresaService.guardar(empresa);
        return "redirect:/empresa/?id=" + volver;

    }
    @GetMapping("/nuevoCliente")
    public String doNuevoCliente (Model model, @RequestParam("id") int idempresa) {
        return this.mostrarEditarONuevo2(new ClienteDTO(), model,idempresa);
    }

    private String mostrarEditarONuevo2(ClienteDTO cliente, Model model,int idempresa) {

        model.addAttribute("cliente",cliente);
        model.addAttribute("empresaID",empresaService.buscarPorId(idempresa));

        return "registroClienteEmpresa";
    }

    @GetMapping("/guardarCliente")
    public String doGuardarCliente(@ModelAttribute("cliente") ClienteDTO cliente,@RequestParam("empresa_id") int idempresa
    ,@RequestParam("tipoCliente") String str)
    {
        EmpresaDTO empresa = empresaService.buscarPorId(idempresa);
        cliente.setEmpresa(empresa);
        asociarCuenta(cliente,str);
        clienteService.guardar(cliente);
        return "redirect:/empresa/?id=" + cliente.getId();
    }

    @GetMapping("/guardarCliente2")
    public String doGuardarCliente2(@ModelAttribute("cliente") ClienteDTO cliente,@RequestParam("empresa_id") int idempresa
    ,@RequestParam("volver") int volver,@RequestParam("tipoCliente") String str)
    {
        EmpresaDTO empresa = empresaService.buscarPorId(idempresa);
        cliente.setEmpresa(empresa);

        asociarCuenta(cliente,str);
        clienteService.guardar(cliente);
        return "redirect:/empresa/?id=" + volver;
    }

    @GetMapping("/guardarClienteEditado")
    public String guardarClienteEditado(@ModelAttribute("cliente") ClienteDTO cliente,
                                        @RequestParam("idempresa") int id)
    {
        cliente.setEmpresa(empresaService.buscarPorId(id));
        clienteService.guardar(cliente);
        return "redirect:/empresa/?id=" + cliente.getId();
    }

    @PostMapping("/filtradoSocios")
    public String filtrar(@ModelAttribute("filtroApellido") FiltroApellido filtroApellido,Model model,@RequestParam("volver")int volver,HttpSession session)
    {
        List<ClienteDTO> lista;
        EmpresaDTO empresa = empresaService.buscarPorId(filtroApellido.getIdEmpresa());

        if(filtroApellido.getApellido().equals("NONE"))
        {
             lista =  clienteService.listarClientesPorEmpresa(empresa);
        }
        else
        {
            lista = clienteService.listarClientesPorEmpresaFiltroApellido(filtroApellido);
        }

        ClienteDTO cliente = this.clienteService.buscarCliente(volver);

        ClienteDTO clienteSession = (ClienteDTO) session.getAttribute("clienteSession");
        if(clienteSession == null || cliente.getId() != clienteSession.getId()) {
            return  "redirect:/logout";
        }

        List<ConversacionDTO> conversaciones = this.conversacionService.listarConversacionesAbiertasDeUnCliente(volver);
        model.addAttribute("cliente", cliente);
        model.addAttribute("conversaciones", conversaciones);
        model.addAttribute("empresa", empresa);
        model.addAttribute("clientesSocios",lista);
        FiltroOperaciones filtro = new FiltroOperaciones();
        model.addAttribute("filtro",filtro);
        filtroApellido.setIdEmpresa(empresa.getId());
        model.addAttribute("filtroApellido",filtroApellido);
        List<TransaccionDTO> transacciones;
        transacciones = cuentaService.buscarEmpresaTrans(empresa);
        model.addAttribute("transacciones",transacciones);
        model.addAttribute("lista",clienteService.listarSocios(cliente.getEmpresa()));
        TipoclienterelacionadoDTO x = tipoclienterelacionadoService.buscarPorCliente(cliente);
        model.addAttribute("tablaIntermedia",x);
        model.addAttribute("all",tipoclienterelacionadoService.listarTodos());

        return "empresa";
    }

    @GetMapping("/solicitar")
    public String doSolicitar(@RequestParam("id") Integer idcuenta,Model model
            , @RequestParam("idcliente") Integer id){
        CuentaDTO cuenta = cuentaService.buscarPorIdCuenta(idcuenta);
        ClienteDTO cliente = this.clienteService.buscarCliente(id);
        EmpresaDTO empresa = cliente.getEmpresa();
        TipoSolicitudDTO tipo =tipoSolicitudService.activar();

        Timestamp date = new Timestamp(System.currentTimeMillis());
        SolicitudDTO solicitud = new SolicitudDTO();
        solicitud.setClienteByClienteId(cliente);
        solicitud.setFecha(date);
        solicitud.setTipoSolicitudByTipo(tipo);
        solicitud.setCuentaByCuentaId(cuenta);
        solicitud.setEmpresaByEmpresaId(empresa);

        this.solicitudService.guardar(solicitud,cliente,cuenta,null);

        return "redirect:/empresa/?id=" + cliente.getId();
    }

    private void asociarCuenta(ClienteDTO cliente, String tipoCliente)
    {
        EmpresaDTO empresa = cliente.getEmpresa();

        clienteService.guardar(cliente);

        if(cuentaService.buscarPorEmpresa(empresa) == null)
            {
                SolicitudDTO solicitud = new SolicitudDTO();
                solicitud.setClienteByClienteId(cliente);
                solicitud.setFecha(new Timestamp(System.currentTimeMillis()));
                EmpleadoDTO gestor = empleadoService.buscarGestor();
                solicitud.setEmpleadoByEmpleadoId(gestor);
                TipoSolicitudDTO tipo = tipoSolicitudService.solicitarCuenta();
                solicitud.setTipoSolicitudByTipo(tipo);
                CuentaDTO c = new CuentaDTO();
                solicitud.setEmpresaByEmpresaId(empresa);

                c.setEmpresaByEmpresaId(cliente.getEmpresa());

                c.setIban("00000000");
                c.setSaldo(BigDecimal.valueOf(0));
                c.setClienteByClienteId(cliente);
                EstadoCuentaDTO estado = estadoCuentaService.bloqueada();
                c.setEstadoCuentaByEstado(estado);
                c.setSwift("342");
                c.setPais("-----");
                c.setEmpleadoByEmpleadoId(gestor);
                c.setEmpresaByEmpresaId(empresa);

                cuentaService.guardar(c);

                solicitud.setCuentaByCuentaId(c);


                solicitudService.guardar(solicitud,cliente,c,gestor);
            }

            CuentaDTO c = cuentaService.buscarPorEmpresa(empresa);

            TipoclienterelacionadoDTO tablaIntermedia = new TipoclienterelacionadoDTO();

           tablaIntermedia.setCuenta(c);
           tablaIntermedia.setCliente(cliente);

            tablaIntermedia.setBloqueado((byte) 1);

            TipoClienteDTO tipocliente = tipoClienteService.buscarTipo(tipoCliente);
            tablaIntermedia.setTipo(tipocliente);

            tipoclienterelacionadoService.guardar(tablaIntermedia,c.getId(),cliente.getId());

    }

    @GetMapping("/transaccion")
    public String doTransaccion(@RequestParam("id") Integer idcuenta,@RequestParam("volver") int volver, Model model){
        TransaccionDTO transaccion = new TransaccionDTO();
        TipoTransaccionDTO tipo = tipoTransaccionService.transaccion();
        transaccion.setTipoTransaccionByTipo(tipo);
        return this.pasarAOperacion(idcuenta,model,transaccion,volver);
    }

    @GetMapping("/cambio")
    public String doCambio(@RequestParam("id") Integer idcuenta, Model model,@RequestParam("volver") int volver){
        TransaccionDTO transaccion = new TransaccionDTO();
        TipoTransaccionDTO tipo = tipoTransaccionService.cambio();
        transaccion.setTipoTransaccionByTipo(tipo);
        return this.pasarAOperacion(idcuenta,model,transaccion,volver);
    }

    protected String pasarAOperacion(Integer idcuenta,Model model, TransaccionDTO transaccion, int volver){
        CuentaDTO cuenta = cuentaService.buscarPorIdCuenta(idcuenta);
        model.addAttribute("cuenta",cuenta);
        List<CuentaDTO> cuentas = cuentaService.buscarCuentas(idcuenta);
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
        ClienteDTO clienteDTO = clienteService.buscarCliente(bloquear);
        TipoclienterelacionadoDTO tipoclienterelacionado = tipoclienterelacionadoService.buscarPorCliente(clienteDTO);
        tipoclienterelacionado.setBloqueado((byte) 1);
        tipoclienterelacionadoService.guardar(tipoclienterelacionado,idcuenta,bloquear);
        return "redirect:/empresa/?id=" + volver ;
    }

    @PostMapping("/filtrarop")
    public String doFiltrar(@ModelAttribute("filtro")FiltroOperaciones filtro,Model model,HttpSession session){
        Integer idcliente = filtro.getClienteid();
        return procesarFiltrado(filtro,idcliente,model,session);
    }

    protected String procesarFiltrado(FiltroOperaciones filtro, Integer idcliente,Model model,HttpSession session) {
        ClienteDTO cliente = this.clienteService.buscarCliente(idcliente);
        List<ConversacionDTO> conversaciones = this.conversacionService.listarConversacionesAbiertasDeUnCliente(idcliente);
        List<TransaccionDTO> transacciones;


        if(filtro == null || (filtro.getCuentadestino().isEmpty() && filtro.getDate().isEmpty())){
            transacciones = cuentaService.findClienteTrans(cliente);
            filtro = new FiltroOperaciones();
        }else{
            if(filtro.getCuentadestino().isEmpty()){
                transacciones = cuentaService.findDateTrans(cliente);
            }else if(filtro.getDate().isEmpty()){
                transacciones = cuentaService.findDestTrans(cliente);
            }else{
                transacciones = cuentaService.findDestDateTrans(cliente);
            }
        }

        ClienteDTO clienteSession = (ClienteDTO) session.getAttribute("clienteSession");
        if(clienteSession == null || cliente.getId() != clienteSession.getId()) {
            return  "redirect:/logout";
        }

        EmpresaDTO empresa = cliente.getEmpresa();

        model.addAttribute("cliente", cliente);
        model.addAttribute("conversaciones", conversaciones);
        model.addAttribute("empresa", empresa);
        List<ClienteDTO> lista =  clienteService.listarClientesPorEmpresa(cliente.getEmpresa());
        model.addAttribute("clientesSocios",lista);
        FiltroApellido filtroApellido = new FiltroApellido();
        model.addAttribute("filtro",filtro);
        filtroApellido.setIdEmpresa(empresa.getId());
        model.addAttribute("filtroApellido",filtroApellido);
        transacciones = cuentaService.buscarEmpresaTrans(empresa);
        model.addAttribute("transacciones",transacciones);
        model.addAttribute("lista",clienteService.listarSocios(cliente.getEmpresa()));
        TipoclienterelacionadoDTO x = tipoclienterelacionadoService.buscarPorCliente(cliente);
        model.addAttribute("tablaIntermedia",x);
        model.addAttribute("all",tipoclienterelacionadoService.listarTodos());


        return "empresa";
    }

}
