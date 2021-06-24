package es.taw.eventosgospring.controller;

import es.taw.eventosgospring.dto.UsuarioDTO;
import es.taw.eventosgospring.dto.UsuarioEventoDTO;
import es.taw.eventosgospring.service.UsuarioEventoService;
import es.taw.eventosgospring.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Controller
public class UsuarioController {
    private UsuarioService usuarioService;
    private UsuarioEventoService usuarioEventoService;

    @Autowired
    public void setUsuarioEventoService(UsuarioEventoService usuarioEventoService) {
        this.usuarioEventoService = usuarioEventoService;
    }

    @Autowired
    public void setUsuarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/registrar")
    public String doRegistrarUsuario(Model model){
        UsuarioDTO usuario = new UsuarioDTO();
        UsuarioEventoDTO usuarioEvento = new UsuarioEventoDTO();
        usuarioEvento.setId(usuario.getId());
        model.addAttribute("usuario", usuario);
        model.addAttribute("usuarioEvento", usuarioEvento);
        return "registro";
    }

    @PostMapping("/guardar")
    public String doGuardarUsuario(@RequestParam("nombre") String nombre,
                                   @RequestParam("apellidos")String apellidos,
                                   @RequestParam("correo")String correo,
                                   @RequestParam("pass1") String pass1,
                                   @RequestParam("pass2") String pass2,
                                   @RequestParam("Sexo") String sexo,
                                   @RequestParam("ciudad") String ciudad,
                                   @RequestParam("domicilio") String domicilio,
                                   @RequestParam("fechaNacimiento") String fechaNacimiento,
                                   Model model){
        String strError="", strTo="";
        SimpleDateFormat formatFecha = new SimpleDateFormat("yyyy-MM-dd");


        if(!pass1.equals(pass2)){
            strError = "Las contraseñas no coinciden";
            model.addAttribute("error", strError);
            strTo="registro";
        } else{
            UsuarioDTO nuevoUsuarioDTO = new UsuarioDTO();
            UsuarioEventoDTO nuevoUsuarioEventoDTO = new UsuarioEventoDTO();
            strTo="redirect:/listarEventos";

            nuevoUsuarioDTO.setRol(4);
            nuevoUsuarioDTO.setNombre(nombre);
            nuevoUsuarioDTO.setCorreo(correo);
            nuevoUsuarioDTO.setContrasena(pass1);

            Integer id = this.usuarioService.guardarUsuario(nuevoUsuarioDTO);

            nuevoUsuarioEventoDTO.setId(id);
            nuevoUsuarioEventoDTO.setApellidos(apellidos);
            nuevoUsuarioEventoDTO.setSexo(new Integer(sexo));
            nuevoUsuarioEventoDTO.setDomicilio(domicilio);
            nuevoUsuarioEventoDTO.setCiudad(ciudad);
            try {
                nuevoUsuarioEventoDTO.setFechaNacimiento(formatFecha.parse(fechaNacimiento));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            this.usuarioEventoService.guardarUsuarioEvento(nuevoUsuarioEventoDTO);

        }

        return strTo;
    }


}
