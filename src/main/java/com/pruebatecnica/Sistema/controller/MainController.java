package com.pruebatecnica.Sistema.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String home() {
        return "index"; 
    }

    @GetMapping("/nit.html")
    public String nit() {
        return "nit"; 
    }

    @GetMapping("/index.html")
    public String index() {
        return "index"; 
    }

    @GetMapping("/articulo.html")
    public String articulo() {
        return "articulo"; 
    }

    @GetMapping("/factura.html")
    public String factura() {
        return "factura";
    }

}
