package es.taw.eventosgospring.controller;

import es.taw.eventosgospring.dto.EventoDTO;
import es.taw.eventosgospring.dto.UsuarioDTO;
import es.taw.eventosgospring.entity.Evento;
import es.taw.eventosgospring.entity.Usuario;
import es.taw.eventosgospring.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("evento")
public class EventoController {
    private EventoService eventoService;

    @Autowired
    public void setEventoService(EventoService eventoService) {
        this.eventoService = eventoService;
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

    @GetMapping("/eliminarEvento/{id}")
    public String doEliminarEvento(@PathVariable("id") Integer id, Model model, HttpSession sesion){

        UsuarioDTO usuario = (UsuarioDTO) sesion.getAttribute("usuario");

        this.eventoService.eliminarEvento(id);

        String filtro="";
        if(usuario.getRol() == 1){
            return doListarEventosCreador(filtro, model, sesion);
        } else if(usuario.getRol() == 4){

        }

       return doListarEventosCreador(filtro,model, sesion);
    }

}
