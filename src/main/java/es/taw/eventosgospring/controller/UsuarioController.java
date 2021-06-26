package es.taw.eventosgospring.controller;

import es.taw.eventosgospring.dto.EntradaDTO;
import es.taw.eventosgospring.dto.EventoDTO;
import es.taw.eventosgospring.dto.UsuarioDTO;
import es.taw.eventosgospring.dto.UsuarioEventoDTO;
import es.taw.eventosgospring.service.EntradaService;
import es.taw.eventosgospring.service.EventoService;
import es.taw.eventosgospring.service.UsuarioEventoService;
import es.taw.eventosgospring.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Controller
@RequestMapping("usuario")
public class UsuarioController {
    private UsuarioService usuarioService;
    private UsuarioEventoService usuarioEventoService;
    private EventoService eventoService;
    private EntradaService entradaService;

    @Autowired
    public void setEntradaService(EntradaService entradaService) {
        this.entradaService = entradaService;
    }

    @Autowired
    public void setEventoService(EventoService eventoService) {
        this.eventoService = eventoService;
    }

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
            strTo="redirect:/evento/listarEventos";

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

    @GetMapping("/reservarEntrada/{idEvento}")
    public String doReservarEntrada(@PathVariable("idEvento") Integer id, Model model, HttpSession sesion){

        EventoDTO evento = this.eventoService.buscarEventoId(id);
        UsuarioEventoDTO usuario = (UsuarioEventoDTO) sesion.getAttribute("usuarioEvento");

        Integer entradasCompradas = this.entradaService.findByIdEvento(evento.getId()).size();
        Integer entradasDisponibles = evento.getAforo() - entradasCompradas;

        model.addAttribute("evento", evento);
        model.addAttribute("entradasDisponibles", entradasDisponibles);
        model.addAttribute("entradasCompradas", entradasCompradas);


        return "comprarEntradas";
    }

    @GetMapping ("/comprarEntradas")
    public String doComprarEntradas(@RequestParam("idEvento") Integer idEvento,
                                    @RequestParam("idUsuario") Integer idUsuario,
                                    @RequestParam("entradasDisponibles") Integer entradasDisponibles,
                                    @RequestParam("entradasCompradasUsuario") Integer entradasCompradasUsuario,
                                    @RequestParam("entradas") Integer entradas,
                                    Model model){

        EventoDTO evento = this.eventoService.buscarEventoId(idEvento);
        UsuarioEventoDTO userEvento = this.usuarioEventoService.buscarUsuarioEventoId(idUsuario);
        EntradaDTO entrada;
        String strError="", strTo="redirect:/evento/listarEventosAsistidos";

        int i = 0;
        if(entradasDisponibles - entradas < 0){
            strError = "El numero de entradas seleccionada hace superar el aforo máximo de este evento.";
            model.addAttribute("error", strError);

        } else if(entradas + entradasCompradasUsuario > evento.getMaximoEntradasUsuario()){
            strError = "El numero de entradas seleccionadas hace superar el numero maximo de entradas por usuario para este evento.";
            model.addAttribute("error", strError);
            strTo = "comprarEntradas";
        }else {
            while (i < entradas) {
                entrada = new EntradaDTO();
                entrada.setEventoByIdEvento(evento.getId());
                entrada.setUsuarioEventoByIdUsuario(userEvento.getId());
                // entrada.setEntradaAforoById();
                this.entradaService.guardarEntrada(entrada);
                i++;
            }
        }
        return strTo;
    }

}
