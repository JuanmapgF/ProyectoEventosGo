package es.taw.eventosgospring.controller;

import es.taw.eventosgospring.dto.ConversacionDTO;
import es.taw.eventosgospring.dto.MensajeDTO;
import es.taw.eventosgospring.dto.UsuarioDTO;
import es.taw.eventosgospring.entity.Mensaje;
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
import java.util.Optional;

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

    @GetMapping("/{error}")
    public String doMisConversaciones(@PathVariable("error") String error, Model model, HttpSession session) {
        UsuarioDTO usuarioDTO = (UsuarioDTO) session.getAttribute("usuario");
        List<ConversacionDTO> lista = this.conversacionService.getListaConversacionesUsuario(usuarioDTO);

        List<UsuarioDTO> usuarios = this.conversacionService.getListaUsuariosConversacion(usuarioDTO, lista);

        model.addAttribute("listaConversaciones", lista);
        model.addAttribute("user", usuarioDTO);
        model.addAttribute("listaUsuarios", usuarios);

        if (Integer.parseInt(error) != 0) {
            model.addAttribute("error", "error");
        } else {
            model.addAttribute("error", null);
        }

        return "misConversaciones";
    }

    @GetMapping("/chat/{id}")
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

        UsuarioDTO aux = this.usuarioService.buscarUsuarioId(conversacionDTO.getUsuarioByIdTeleoperador());

        if (usuarioDTO.getId() == conversacionDTO.getUsuarioByIdUsuario() || usuarioDTO.getId() == conversacionDTO.getUsuarioByIdTeleoperador()) {
            this.mensajeService.setVisto(mensajes, usuarioDTO);
        }

        MensajeDTO mensajeDTO = new MensajeDTO();
        mensajeDTO.setConversacionByIdConversacion(conversacionDTO.getId());
        mensajeDTO.setUsuarioByIdUsuario(usuarioDTO.getId());
        mensajeDTO.setTexto("");

        model.addAttribute("mensajes", mensajes);
        model.addAttribute("user", usuarioDTO);
        model.addAttribute("conversacion", conversacionDTO);
        model.addAttribute("otro", otro);
        model.addAttribute("mensajeNuevo", mensajeDTO);
        model.addAttribute("aux", aux);

        return "chatConversacion";
    }

    @PostMapping("/enviar/msg")
    public String doMensajeEnviar(@ModelAttribute("mensajeNuevo") MensajeDTO mensajeDTO, Model model, HttpSession session) {
        mensajeDTO.setFecha(new Date());
        mensajeDTO.setHora(new Date());
        mensajeDTO.setVisto(0);

        UsuarioDTO usuarioDTO = (UsuarioDTO) session.getAttribute("usuario");

        this.conversacionService.enviarMensaje(mensajeDTO, usuarioDTO);

        return "redirect:/conversacion/chat/" + mensajeDTO.getConversacionByIdConversacion();
    }

    @GetMapping("/asignar/tlp")
    public String doAsignarTeleoperador(@RequestParam("asunto") String asunto, Model model, HttpSession session) {

        UsuarioDTO usuarioDTO = (UsuarioDTO) session.getAttribute("usuario");

        Integer error = this.conversacionService.asignarTeleoperador(asunto, usuarioDTO);

        return "redirect:/conversacion/" + error;
    }

    @GetMapping("/historial/tlp")
    public String doHistorialTeleoperador(Model model, HttpSession session) {
        List<ConversacionDTO> conversaciones = this.conversacionService.getTodasLasConversaciones();
        model.addAttribute("listaConversaciones", conversaciones);

        UsuarioDTO usuarioDTO = (UsuarioDTO) session.getAttribute("usuario");
        model.addAttribute("idUsuario", usuarioDTO.getId());

        model.addAttribute("usuarios", this.conversacionService.getTodosLosUsuarios());

        return "historialConversaciones";
    }
}
