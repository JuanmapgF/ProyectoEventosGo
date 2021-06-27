package es.taw.eventosgospring.controller;

import es.taw.eventosgospring.dto.EventoDTO;
import es.taw.eventosgospring.dto.UsuarioDTO;
import es.taw.eventosgospring.dto.UsuarioEventoDTO;
import es.taw.eventosgospring.service.EventoService;
import es.taw.eventosgospring.service.UsuarioEventoService;
import es.taw.eventosgospring.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class AutenticarController {
    private UsuarioService usuarioService;
    private UsuarioEventoService usuarioEventoService;
    private EventoService eventoService;

    @Autowired
    public void setUsuarioEventoService(UsuarioEventoService usuarioEventoService) {
        this.usuarioEventoService = usuarioEventoService;
    }

    @Autowired
    public void setEventoService(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @Autowired
    public void setUsuarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/iniciarSesion")
    public String doIniciarSesion(){
        return "inicioSesion";
    }

    @PostMapping("/autenticar")
    public String doAutenticar (@RequestParam ("correo") String correo,
                                @RequestParam ("password") String pass,
                                Model model, HttpSession sesion){

        String strTo="", strError="";

        if(correo == null || correo.isEmpty() || pass == null || pass.isEmpty()){
            strError="Error de autentificación: alguno de los campos está vacío.";
            model.addAttribute("error", strError);
            strTo = "inicioSesion";
        } else{
            UsuarioDTO user = this.usuarioService.comprobarCredenciales(correo, pass);

            if(user == null) {
                strError = "Error de autentificación: credenciales incorrectas.";
                model.addAttribute("error", strError);
                strTo = "inicioSesion";
            } else{

                sesion.setAttribute("usuario", user);

                switch (user.getRol()){
                    case 0: strTo="adminPrincipal";
                            break;

                    case 1: strTo="redirect:/evento/listarEventosCreados";
                            break;

                    case 2: UsuarioDTO usuarioDTO = this.usuarioService.buscarUsuarioId(user.getId());
                            sesion.setAttribute("usuario", usuarioDTO);
                            strTo = "redirect:/conversacion/0";
                            break;

                    case 3: UsuarioDTO usuarioDTO1 = this.usuarioService.buscarUsuarioId(user.getId());
                            sesion.setAttribute("usuario", usuarioDTO1);
                            strTo = "redirect:/estudios/";
                            break;

                    case 4: UsuarioEventoDTO userEvento = this.usuarioEventoService.buscarUsuarioEventoId(user.getId());
                            sesion.setAttribute("usuarioEvento", userEvento);
                            strTo="redirect:/evento/listarEventosDisponibles";
                            break;

                    default: break;
                }

            }
        }



        return strTo;
    }


    @GetMapping ("/cerrarSesion")
    public String doCerrarSesion(HttpSession sesion){
        sesion.invalidate();
        return "redirect:/";
    }
}
