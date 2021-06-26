package es.taw.eventosgospring.controller;

import es.taw.eventosgospring.dto.EntradaDTO;
import es.taw.eventosgospring.dto.EventoDTO;
import es.taw.eventosgospring.dto.UsuarioEventoDTO;
import es.taw.eventosgospring.service.EntradaService;
import es.taw.eventosgospring.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("entrada")
public class EntradaController {
    private EntradaService entradaService;
    private EventoService eventoService;

    @Autowired
    public void setEventoService(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @Autowired
    public void setEntradaService(EntradaService entradaService) {
        this.entradaService = entradaService;
    }

    @GetMapping("/eliminarEntradas/{idEvento}")
    public String doEliminarEvento(@PathVariable("idEvento") Integer idEvento, Model model, HttpSession sesion){

        EventoDTO evento = this.eventoService.buscarEventoId(idEvento);
        UsuarioEventoDTO usuario = (UsuarioEventoDTO) sesion.getAttribute("usuarioEvento");

        this.entradaService.borrarEntradas(evento, usuario);

        return "redirect:/evento/listarEventosAsistidos";
    }

}
