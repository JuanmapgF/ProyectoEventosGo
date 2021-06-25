package es.taw.eventosgospring.controller;

import es.taw.eventosgospring.dto.EstudioDTO;
import es.taw.eventosgospring.dto.EventoDTO;
import es.taw.eventosgospring.dto.UsuarioDTO;
import es.taw.eventosgospring.entity.Estudio;
import es.taw.eventosgospring.entity.Usuario;
import es.taw.eventosgospring.service.EstudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("estudios")
public class EstudioController {

    private EstudioService estudioService;

    @Autowired
    public void setEstudioService(EstudioService estudioService) {
        this.estudioService = estudioService;
    }

    @GetMapping("/")
    public String  doListarEstudios(Model model, HttpSession session) {
        return this.doListarEstudiosMostrar(model, session, 1);
    }

    @GetMapping("/{pagina}")
    public String doListarEstudiosMostrar(Model model, HttpSession session, @PathVariable("pagina") Integer pagina) {
        UsuarioDTO usuario = (UsuarioDTO) session.getAttribute("usuario");
        List<EstudioDTO> estudioDTOList = this.estudioService.getEstudioDTOList(usuario.getId());
        List<Integer> resultIntegers = new ArrayList<>();
        // Calcular el resultado de cada uno de los estudios
        for(EstudioDTO estudioDTO : estudioDTOList) {
            resultIntegers.add(this.estudioService.getResultadoEstudio(estudioDTO.getResultado()).size());
        }
        model.addAttribute("listaEstudios", estudioDTOList);
        model.addAttribute("pagina", pagina);
        model.addAttribute("resultadoEstudios", resultIntegers);
        return "estudios";
    }

    @GetMapping("/borrar/{id}")
    public String doBorrarEstudio(Model model, HttpSession session, @PathVariable("id") Integer id) {
        UsuarioDTO usuarioDTO = (UsuarioDTO) session.getAttribute("usuario");
        session.setAttribute("usuario", this.estudioService.deleteEstudioById(id, usuarioDTO.getId()));
        return doListarEstudios(model, session);
    }

    @GetMapping("/info/{id}")
    public String doVerEstudio(Model model, HttpSession session, @PathVariable("id") Integer id) {
        EstudioDTO estudioDTO = this.estudioService.getEstudioDTOById(id);
        model.addAttribute("resultado", this.estudioService.getResultadoEstudio(estudioDTO.getResultado()));
        model.addAttribute("estudio", estudioDTO);
        return "verEstudio";
    }

    @GetMapping("/crear")
    public String doCrearEstudio(Model model) {  return "crearEstudio";   }

    @PostMapping("/almacenar/")
    public String doAlmacenarEstudio(Model model, @RequestParam("titulo") String titulo, @RequestParam("anio") String anio, @RequestParam("edad_min") String edad_min, @RequestParam("edad_max") String edad_max, @RequestParam("masculino") String masc, @RequestParam("femenino") String fem, @RequestParam("otros") String otro, @RequestParam("ciudad") String ciudad, HttpSession session) {
        UsuarioDTO usuarioDTO = (UsuarioDTO) session.getAttribute("usuario");
        EstudioDTO estudioDTO = this.estudioService.getEstudioDTOAlmacenado(titulo, (anio != null && !anio.isEmpty()) ? anio : "-1", edad_min, edad_max, (masc!=null) ? masc : "-1", (fem != null) ? fem : "-1", (otro != null) ? otro : "-1", (ciudad != null && !ciudad.isEmpty()) ? ciudad : "", usuarioDTO.getId());

        model.addAttribute("estudio", estudioDTO);
        model.addAttribute("lista", this.estudioService.getResultadoEstudio(estudioDTO.getResultado()));

        return "confirmarEstudio";
    }

    @PostMapping("/guardar")
    public String doGuardarEstudio(Model model, @RequestParam("titulo") String titulo, @RequestParam("anio") String anio, @RequestParam("edad_min") String edad_min, @RequestParam("edad_max") String edad_max, @RequestParam("masculino") String masc, @RequestParam("femenino") String fem, @RequestParam("otros") String otro, @RequestParam("ciudad") String ciudad, HttpSession session) {

        Estudio est = new Estudio();
        est.setTitulo(titulo);
        est.setUsuarioByIdAnalista( (Usuario) session.getAttribute("usuario"));

        return "";
    }
}
