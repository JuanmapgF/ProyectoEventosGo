package es.taw.eventosgospring.controller;

import es.taw.eventosgospring.dto.EstudioDTO;
import es.taw.eventosgospring.dto.EventoDTO;
import es.taw.eventosgospring.dto.UsuarioDTO;
import es.taw.eventosgospring.entity.Estudio;
import es.taw.eventosgospring.entity.Usuario;
import es.taw.eventosgospring.service.EstudioService;
import es.taw.eventosgospring.vo.EstudioResultado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
    public String doCrearEstudio(Model model, HttpSession session) {
        EstudioResultado estudioResultado = new EstudioResultado();

        model.addAttribute("resultado", estudioResultado);

        return "crearEstudio";
    }

    @PostMapping("/almacenar")
    public String doAlmacenarEstudio(@ModelAttribute("resultado") EstudioResultado estudioResultado, Model model, HttpSession session) {
        UsuarioDTO usuarioDTO = (UsuarioDTO) session.getAttribute("usuario");
        EstudioDTO estudioDTO = this.estudioService.getEstudioDTOAlmacenado(estudioResultado.getTitulo(), (estudioResultado.getAnio() != null) ? String.valueOf(estudioResultado.getAnio()) : "-1", String.valueOf(estudioResultado.getEdad_min()), String.valueOf(estudioResultado.getEdad_max()), (estudioResultado.getMasculino() != null) ? String.valueOf(estudioResultado.getMasculino()) : "-1", (estudioResultado.getFemenino() != null) ? String.valueOf(estudioResultado.getFemenino()) : "-1", (estudioResultado.getOtro() != null) ? String.valueOf(estudioResultado.getOtro()) : "-1", estudioResultado.getCiudad(), usuarioDTO.getId());

        if (estudioResultado.getId() != -1) {
            estudioDTO.setId(estudioResultado.getId());
        }

        model.addAttribute("estudio", estudioDTO);
        model.addAttribute("lista", this.estudioService.getResultadoEstudio(estudioDTO.getResultado()));

        return "confirmarEstudio";
    }

    @GetMapping("/guardar/{titulo}/{emin}/{emax}/{ciudad}/{anio}/{masc}/{fem}/{otro}/{id}")
    public String doGuardarEstudio(Model model, @PathVariable("titulo") String titulo, @PathVariable("emin") String emin, @PathVariable("emax") String emax, @PathVariable("ciudad") String ciudad, @PathVariable("anio") String anio, @PathVariable("masc") String masc, @PathVariable("fem") String fem, @PathVariable("otro") String otro, @PathVariable("id") String id, HttpSession session) {

        String resultado = emin + ";" + emax + ";" + (ciudad == null || (ciudad.equals("null")) ? "" : ciudad) + ";" + anio + ";" + masc + ";" + fem + ";" + otro;

        UsuarioDTO usuarioDTO = (UsuarioDTO) session.getAttribute("usuario");

        EstudioDTO est = new EstudioDTO();
        est.setTitulo(titulo);
        est.setResultado(resultado);
        est.setUsuarioIdAnalista(usuarioDTO.getId());

        if (Integer.parseInt(id) != -1) {
            est.setId(Integer.parseInt(id));
        }

        this.estudioService.guardarEstudio(est);

        return "redirect:/estudios/";
    }

    @GetMapping("/editar/{id}")
    public String doEditarEstudio(Model model, @PathVariable("id") Integer id) {

        EstudioDTO estudioDTO = this.estudioService.getEstudioDTOById(id);

        int edadMinima;
        int edadMaxima;
        String ciudad;
        int anio;
        int masculino;
        int femenino;
        int otro;


        // Extraer valores de los filtros
        try (Scanner sc = new Scanner(estudioDTO.getResultado())) {
            sc.useDelimiter(";");
            edadMinima = (sc.hasNext()) ? Integer.parseInt(sc.next()) : -1;
            edadMaxima = (sc.hasNext()) ? Integer.parseInt(sc.next()) : -1;
            String auxsc = sc.next();
            ciudad = (sc.hasNext() && !auxsc.isEmpty()) ? auxsc : null;
            anio = (sc.hasNext()) ? Integer.parseInt(sc.next()) : -1;
            masculino = (sc.hasNext()) ? Integer.parseInt(sc.next()) : -1;
            femenino = (sc.hasNext()) ? Integer.parseInt(sc.next()) : -1;
            otro = (sc.hasNext()) ? Integer.parseInt(sc.next()) : -1;
        }

        model.addAttribute("idEstudio", estudioDTO.getId());
        model.addAttribute("titulo", estudioDTO.getTitulo());
        model.addAttribute("eMin", edadMinima);
        model.addAttribute("eMax", edadMaxima);
        model.addAttribute("anio", anio);
        model.addAttribute("ciudad", ciudad);
        model.addAttribute("masculino", masculino);
        model.addAttribute("femenino", femenino);
        model.addAttribute("otro", otro);

        EstudioResultado estudioResultado = new EstudioResultado();
        estudioResultado.setTitulo(estudioDTO.getTitulo());
        estudioResultado.setEdad_min(edadMinima);
        estudioResultado.setEdad_max(edadMaxima);
        estudioResultado.setAnio(anio);
        estudioResultado.setCiudad(ciudad);
        estudioResultado.setMasculino(masculino);
        estudioResultado.setFemenino(femenino);
        estudioResultado.setOtro(otro);
        estudioResultado.setId(estudioDTO.getId());


        model.addAttribute("resultado", estudioResultado);

        return "editarEstudio";
    }
}
