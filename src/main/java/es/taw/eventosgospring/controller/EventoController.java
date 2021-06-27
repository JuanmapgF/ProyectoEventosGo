package es.taw.eventosgospring.controller;

import es.taw.eventosgospring.dto.EntradaDTO;
import es.taw.eventosgospring.dto.EtiquetaDTO;
import es.taw.eventosgospring.dto.EventoDTO;
import es.taw.eventosgospring.dto.EventoEtiquetaDTO;
import es.taw.eventosgospring.dto.UsuarioDTO;
import es.taw.eventosgospring.dto.UsuarioEventoDTO;
import es.taw.eventosgospring.entity.Evento;
import es.taw.eventosgospring.entity.Usuario;
import es.taw.eventosgospring.entity.*;
import es.taw.eventosgospring.service.EtiquetaService;
import es.taw.eventosgospring.service.EventoEtiquetaService;
import es.taw.eventosgospring.service.EventoService;
import es.taw.eventosgospring.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("evento")
public class EventoController {
    private EventoService eventoService;
    private EventoEtiquetaService eventoEtiquetaService;
    private UsuarioService usuarioService;
    private EtiquetaService etiquetaService;

    @Autowired
    public void setEventoService(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @Autowired
    public void setEventoEtiquetaService(EventoEtiquetaService eventoEtiquetaService) {
        this.eventoEtiquetaService = eventoEtiquetaService;
    }

    @Autowired
    public void setUsuarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Autowired
    public void setEtiquetaService(EtiquetaService etiquetaService) {
        this.etiquetaService = etiquetaService;
    }

    private String comprobarUsuarioNoAutentificado (HttpSession sesion){
        String strTo = null;
        UsuarioDTO user = (UsuarioDTO) sesion.getAttribute("usuario");
        if(user == null){
            strTo = "inicioSesion";
        }
        return strTo;
    }

    @GetMapping("/")
    public String doInit(Model model){
        return doListarEventosDisponibles(model);
    }

    @GetMapping("/listarEventosDisponibles")
    public String doListarEventosDisponibles(Model model){

        List<EventoDTO> eventos = this.eventoService.listarEventosDisponibles();
        model.addAttribute("eventosDisponibles", eventos);
        String strTo="paginaInicioWeb";

        return strTo;
    }

    @GetMapping  ("/listarEventosCreados")
    public String doListarEventosCreador(@RequestParam(value = "filtro", required = false) String filtro, Model model, HttpSession sesion){
        UsuarioDTO creador = (UsuarioDTO) sesion.getAttribute("usuario");
        Integer creadorid = creador.getId();
        List<EventoDTO> eventos;

        if(filtro == null || filtro.isEmpty()){
            eventos = this.eventoService.listarEventosCreador(creadorid);
        } else{
            eventos = this.eventoService.listarEventosCreadorFiltro(creadorid, filtro);
        }

        model.addAttribute("eventos", eventos);
        String strTo="creadorInicio";

        return strTo;
    }

    @GetMapping("/verEvento/{id}")
    public String doVerEvento(@PathVariable("id") Integer id, Model model){
        String strTo = "verEvento";

        EventoDTO evento = this.eventoService.buscarEventoId(id);
        model.addAttribute("evento", evento);

        return strTo;
    }

    @GetMapping("/editarEvento/{id}")
    public String doEditarEvento(@PathVariable("id") Integer id, Model model){
        String strTo = "eventoEditar";

        EventoDTO eventoDTO = this.eventoService.buscarEventoId(id);
        String etiquetas = "";

        EventoEtiquetaDTO evetdto = this.eventoEtiquetaService.buscarEventoEtiquetaId(eventoDTO.getEventoEtiquetasById().get(0));
        EtiquetaDTO et = this.etiquetaService.findById(evetdto.getEtiquetaByIdEtiqueta());
        String nombreEtiqueta = et.getNombre();
        etiquetas += nombreEtiqueta;
        int i=1;
        while(i<eventoDTO.getEventoEtiquetasById().size()-1) {
            evetdto = this.eventoEtiquetaService.buscarEventoEtiquetaId(eventoDTO.getEventoEtiquetasById().get(i));
            et = this.etiquetaService.findById(evetdto.getEtiquetaByIdEtiqueta());
            nombreEtiqueta = et.getNombre();
            etiquetas += ", " + nombreEtiqueta;
            i++;
        }
        evetdto = this.eventoEtiquetaService.buscarEventoEtiquetaId(eventoDTO.getEventoEtiquetasById().get(i));
        et = this.etiquetaService.findById(evetdto.getEtiquetaByIdEtiqueta());
        nombreEtiqueta = et.getNombre();
        etiquetas += ", " + nombreEtiqueta;

        model.addAttribute("evento", eventoDTO);
        model.addAttribute("etiquetas",etiquetas);

        return strTo;
    }

    @GetMapping("/eliminarEvento/{id}")
    public String doEliminarEvento(@PathVariable("id") Integer id, Model model, HttpSession sesion){

        UsuarioDTO usuario = (UsuarioDTO) sesion.getAttribute("usuario");

        this.eventoService.eliminarEvento(id);

        String filtro="";
        if(usuario.getRol() == 1){
            return this.doListarEventosCreador(filtro, model, sesion);
        } else if(usuario.getRol() == 0){
            return this.doListarEventosDisponibles(model);
        }

        return this.doListarEventosDisponibles(model);
    }

    @GetMapping("/listarEventosAsistidos")
    public String doListarEventosAsistidos(@RequestParam(value = "filtroEvento", required = false) String filtro, Model model, HttpSession sesion){

        UsuarioEventoDTO user = (UsuarioEventoDTO) sesion.getAttribute("usuarioEvento");
        Map<EventoDTO, Integer> eventos;

        if(filtro == null || filtro.isEmpty()){
            eventos = this.eventoService.listarEventosAsistidos(user.getId());
        } else{
            eventos = this.eventoService.listarEventosAsistidosFiltro(filtro, user.getId());
        }
        model.addAttribute("eventos", eventos);
        return "usuarioEventos";

    }

    @GetMapping("/crearEvento")
    public String doCrearEvento(Model model, HttpSession sesion){
        return "crearEvento";
    }


    @PostMapping("/guardarEvento")
    public String doGuardarEvento(@RequestParam(value = "id", required = false)Integer id,
                                  @RequestParam("titulo")String titulo,
                                  @RequestParam("fechaEntradas")String fechaEn,
                                  @RequestParam("coste")Double coste,
                                  @RequestParam("descripcion")String descripcion,
                                  @RequestParam("fechaEvento")String fechaEv,
                                  @RequestParam("aforo")Integer aforo,
                                  @RequestParam("entradas")Integer maxEntradas,
                                  @RequestParam("etiquetas")String strEtiquetas,
                                  Model model, HttpSession sesion) throws ParseException {

        UsuarioDTO usuario = (UsuarioDTO) sesion.getAttribute("usuario");

        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

        Date fechaEvento = formato.parse(fechaEv);
        Date fechaEntradas = formato.parse(fechaEn);

        List<EtiquetaDTO> listaEtiquetas = new ArrayList<>();
        List<Integer> listaEtiquetasId = new ArrayList<>();
        String[] etiquetas = strEtiquetas.split("\\W");

        for(String s : etiquetas){
            if(!s.isEmpty()){
                EtiquetaDTO et = this.etiquetaService.findByName(s);

                if(et == null){
                    et = new EtiquetaDTO();
                    et.setNombre(s);
                    this.etiquetaService.crearEtiqueta(et);
                    et = this.etiquetaService.findByName(s);
                }

                listaEtiquetasId.add(et.getId());
                listaEtiquetas.add(et);
            }
        }

        EventoDTO nuevoEvento;
        if (id != null){
            nuevoEvento = this.eventoService.buscarEventoId(id);  // Editar evento existente
        } else{
            nuevoEvento = new EventoDTO();                        // Nuevo evento
        }

        List<Integer> listaEntradas;

        if(nuevoEvento.getEntradasById() != null && !nuevoEvento.getEntradasById().isEmpty()){
            listaEntradas = nuevoEvento.getEntradasById();
        } else{
            listaEntradas = new ArrayList<>();
        }


        nuevoEvento.setTitulo(titulo);
        nuevoEvento.setDescripcion(descripcion);
        nuevoEvento.setCoste(coste);
        nuevoEvento.setAforo(aforo);
        nuevoEvento.setMaximoEntradasUsuario(maxEntradas);
        nuevoEvento.setFechaEvento(fechaEvento);
        nuevoEvento.setFechaFinReservas(fechaEntradas);
        nuevoEvento.setUsuarioByIdCreador(usuario.getId());
        nuevoEvento.setEventoEtiquetasById(listaEtiquetasId);
        nuevoEvento.setEntradasById(listaEntradas);

        Integer idevento = this.eventoService.guardarEvento(nuevoEvento,usuario);

        List<EventoEtiquetaDTO>  listaEventoEtiqueta = new ArrayList<>();

        for(EtiquetaDTO et : listaEtiquetas){
            EventoEtiquetaDTO evet = new EventoEtiquetaDTO();
            evet.setEventoByIdEvento(this.eventoService.buscarEventoId(idevento).getId());
            evet.setEtiquetaByIdEtiqueta(et.getId());
            this.eventoEtiquetaService.guardarEventoEtiqueta(evet);
        }


        if(usuario.getRol() == 0){
            return "redirect:/EventosCargarAdmin";
        }else{
            return "redirect:/evento/listarEventosCreados";
        }

    }


}
