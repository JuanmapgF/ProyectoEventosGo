package es.taw.eventosgospring.controller;

import es.taw.eventosgospring.dto.EventoDTO;
import es.taw.eventosgospring.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class AdminController {
    private EventoService eventoService;

    @Autowired
    public void setEventoService(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @GetMapping("/UsuariosCargarAdmin")
    public String UsuariosCargarAdmin(Model model, HttpSession session){

        return "adminUsuarios";
    }

    @GetMapping("/EventosCargarAdmin")
    public String EventosCargarAdmin(Model model, HttpSession session){
        List<EventoDTO> listaEventos = this.eventoService.findAll();
        model.addAttribute("listaEventos",listaEventos);
        return "adminEventos";
    }

    @GetMapping("/EventoVerAdmin/{id}")
    public String doVerEvento(@PathVariable("id") Integer id, Model model){
        String strTo = "verEvento";

        EventoDTO evento = this.eventoService.buscarEventoId(id);
        model.addAttribute("evento", evento);

        return strTo;
    }
}

