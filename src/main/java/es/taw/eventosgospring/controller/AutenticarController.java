package es.taw.eventosgospring.controller;

import es.taw.eventosgospring.dto.UsuarioDTO;
import es.taw.eventosgospring.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class AutenticarController {
    private UsuarioService usuarioService;

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

            if(user == null){
                strError = "Error de autentificación: credenciales incorrectas.";
                model.addAttribute("error", strError);
                strTo = "inicioSesion";
            } else{
                sesion.setAttribute("usuario", user);
                strTo="redirect:/listarEventos";
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
