package es.taw.eventosgospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class AdminController {

    @GetMapping("/UsuariosCargar")
    public String UsuariosCargar(Model model, HttpSession session){

        return "adminUsuarios";
    }

}

