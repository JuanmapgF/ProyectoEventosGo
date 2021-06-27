package es.taw.eventosgospring.controller;

import es.taw.eventosgospring.dto.ConversacionDTO;
import es.taw.eventosgospring.dto.MensajeDTO;
import es.taw.eventosgospring.dto.UsuarioDTO;
import es.taw.eventosgospring.entity.Usuario;
import es.taw.eventosgospring.service.ConversacionService;
import es.taw.eventosgospring.service.MensajeService;
import es.taw.eventosgospring.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("conversacion")
public class ConversacionController {

    private ConversacionService conversacionService;
    private MensajeService mensajeService;
    private UsuarioService usuarioService;

    @Autowired
    public void setConversacionService(ConversacionService conversacionService) {
        this.conversacionService = conversacionService;
    }

    @Autowired
    public void setMensajeService(MensajeService mensajeService) {
        this.mensajeService = mensajeService;
    }

    @Autowired
    public void setUsuarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
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
    public String doMensajeCargar(@PathVariable("id") Integer id, Model model, HttpSession session) {

        List<MensajeDTO> mensajes = this.mensajeService.getMensajeDTOByIDConversacion(id);
        UsuarioDTO usuarioDTO = (UsuarioDTO) session.getAttribute("usuario");
        ConversacionDTO conversacionDTO = this.conversacionService.getConversacionDTO(id);
        UsuarioDTO otro;
        if (usuarioDTO.getRol() == 4) {
            otro = this.usuarioService.buscarUsuarioId(conversacionDTO.getUsuarioByIdTeleoperador());
        } else {
            otro = this.usuarioService.buscarUsuarioId(conversacionDTO.getUsuarioByIdUsuario());
        }

        MensajeDTO mensajeDTO = new MensajeDTO();
        mensajeDTO.setConversacionByIdConversacion(conversacionDTO.getId());
        mensajeDTO.setUsuarioByIdUsuario(usuarioDTO.getId());
        mensajeDTO.setTexto("");

        model.addAttribute("mensajes", mensajes);
        model.addAttribute("user", usuarioDTO);
        model.addAttribute("conversacion", conversacionDTO);
        model.addAttribute("otro", otro);
        model.addAttribute("mensajeNuevo", new MensajeDTO());

        return "chatConversacion";
    }

    @PostMapping("/enviar")
    public String doMensajeEnviar(@ModelAttribute("mensajeNuevo") MensajeDTO mensajeDTO, Model model, HttpSession session) {
        mensajeDTO.setFecha(new Date());
        mensajeDTO.setHora(new Date());
        mensajeDTO.setVisto(0);

        return "";
    }
}
