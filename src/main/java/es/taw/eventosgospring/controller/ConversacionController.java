package es.taw.eventosgospring.controller;

import es.taw.eventosgospring.dto.ConversacionDTO;
import es.taw.eventosgospring.dto.MensajeDTO;
import es.taw.eventosgospring.dto.UsuarioDTO;
import es.taw.eventosgospring.entity.Usuario;
import es.taw.eventosgospring.service.ConversacionService;
import es.taw.eventosgospring.service.MensajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("conversacion")
public class ConversacionController {

    private ConversacionService conversacionService;
    private MensajeService mensajeService;

    @Autowired
    public void setConversacionService(ConversacionService conversacionService) {
        this.conversacionService = conversacionService;
    }

    @Autowired
    public void setMensajeService(MensajeService mensajeService) {
        this.mensajeService = mensajeService;
    }

    @GetMapping("/")
    public String doMisConversaciones(Model model, HttpSession session) {
        UsuarioDTO usuarioDTO = (UsuarioDTO) session.getAttribute("usuario");
        List<ConversacionDTO> lista = this.conversacionService.getListaConversacionesUsuario(usuarioDTO);

        List<UsuarioDTO> usuarios = this.conversacionService.getListaUsuariosConversacion(usuarioDTO, lista);

        model.addAttribute("listaConversaciones", lista);
        model.addAttribute("user", usuarioDTO);
        model.addAttribute("listaUsuarios", usuarios);

        return "misConversaciones";
    }

    @GetMapping("/{id}")
    public String doMensajeCargar(@PathVariable("id") Integer id) {
        return "";
    }
}
