package es.taw.eventosgospring.controller;

import es.taw.eventosgospring.dto.EtiquetaDTO;
import es.taw.eventosgospring.dto.EventoDTO;
import es.taw.eventosgospring.dto.EventoEtiquetaDTO;
import es.taw.eventosgospring.dto.UsuarioDTO;
import es.taw.eventosgospring.entity.*;
import es.taw.eventosgospring.service.EventoEtiquetaService;
import es.taw.eventosgospring.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("evento")
public class EventoController {
    private EventoService eventoService;
    private EventoEtiquetaService eventoEtiquetaService;

    @Autowired
    public void setEventoService(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @Autowired
    public void setEventoEtiquetaService(EventoEtiquetaService eventoEtiquetaService) {
        this.eventoEtiquetaService = eventoEtiquetaService;
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
        Evento evento = this.eventoService.buscarEvento(id);
        String etiquetas = "";
        for(EventoEtiqueta e :evento.getEventoEtiquetasById()) {
            etiquetas += e.getEtiquetaByIdEtiqueta().getNombre();
        }
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
        } else if(usuario.getRol() == 4){

        }

       return this.doListarEventosCreador(filtro,model, sesion);
    }

    @GetMapping("/crearEvento")
    public String doCrearEvento(Model model, HttpSession sesion){
        return "crearEvento";
    }

    @GetMapping("/guardarEvento")
    public String doGuardarEvento(@RequestParam("titulo")String titulo,@RequestParam("descripcion")String descripcion,
        @RequestParam("fechaEvento")Date fechaEvento,@RequestParam("fechaEntradas")Date fechaEntradas,
        @RequestParam("coste")Double coste,@RequestParam("aforo")Integer aforo,@RequestParam("entradas")Integer entradas,
        @RequestParam("etiquetas")String etiquetas,Model model, HttpSession sesion){
        UsuarioDTO usuario = (UsuarioDTO) sesion.getAttribute("usuario");



        return "";
    }

}
