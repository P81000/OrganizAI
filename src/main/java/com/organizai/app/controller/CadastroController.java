package com.organizai.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CadastroController {
    @RequestMapping("/cadastro")
    public String login() {
        //System.out.println("teste");
        return "cadastro";
    }
}
