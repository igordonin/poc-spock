package com.pareto.spock.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    public RotaService rotaService;

    @Autowired
    public HomeController(RotaService rotaService) {
        this.rotaService = rotaService;
    }

    @RequestMapping("/")
    public ModelAndView index() {
        Iterable<RotaResultado> rotas = this.rotaService.obterRotasRaiz();

        Model model = new ExtendedModelMap();
        model.addAttribute("rotas", rotas);

        return new ModelAndView("index", model.asMap());
    }
}
