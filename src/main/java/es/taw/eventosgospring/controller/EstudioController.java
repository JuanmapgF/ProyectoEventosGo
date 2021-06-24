package es.taw.eventosgospring.controller;

import es.taw.eventosgospring.dto.EstudioDTO;
import es.taw.eventosgospring.entity.Estudio;
import es.taw.eventosgospring.entity.Usuario;
import es.taw.eventosgospring.service.EstudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/estudios")
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

    @PostMapping("/{pagina}")
    public String doListarEstudiosMostrar(Model model, HttpSession session, @PathVariable("pagina") Integer pagina) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        List<EstudioDTO> estudioDTOList = this.estudioService.getEstudioDTOList(usuario);
        model.addAttribute("listaEstudios", estudioDTOList);
        model.addAttribute("pagina", pagina);

        return "estudios";
    }


    @GetMapping("/crear")
    public String doCrearEstudio(Model model) {
        return "crearEstudio";
    }

    @PostMapping("/guardar")
    public String doGuardarEstudio(Model model, @RequestParam("titulo") String titulo, @RequestParam("anio") String anio, @RequestParam("edad_min") String edad_min, @RequestParam("edad_max") String edad_max, @RequestParam("masculino") String masc, @RequestParam("femenino") String fem, @RequestParam("otros") String otro, @RequestParam("ciudad") String ciudad, HttpSession session) {

        Estudio est = new Estudio();
        est.setTitulo(titulo);
        est.setUsuarioByIdAnalista( (Usuario) session.getAttribute("usuario"));

        return "";
    }
}
