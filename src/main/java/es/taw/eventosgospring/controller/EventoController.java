package es.taw.eventosgospring.controller;

import es.taw.eventosgospring.dto.EventoDTO;
import es.taw.eventosgospring.dto.UsuarioDTO;
import es.taw.eventosgospring.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
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
    public String doInit(Model model, HttpSession sesion){
        return doListarEventosDisponibles(model, sesion);
    }

    @GetMapping("/listarEventos")
    public String doListarEventosDisponibles(Model model, HttpSession sesion){

        List<EventoDTO> eventos = this.eventoService.listarEventosDisponibles();
        model.addAttribute("eventosDisponibles", eventos);
        String strTo="paginaInicioWeb";

        return strTo;
    }
}
