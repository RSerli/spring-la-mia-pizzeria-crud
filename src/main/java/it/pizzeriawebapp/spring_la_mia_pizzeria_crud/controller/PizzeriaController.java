package it.pizzeriawebapp.spring_la_mia_pizzeria_crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.pizzeriawebapp.spring_la_mia_pizzeria_crud.model.Pizza;
import it.pizzeriawebapp.spring_la_mia_pizzeria_crud.repository.PizzeriaRepository;

@Controller
@RequestMapping("/")
public class PizzeriaController {

    @Autowired
    private PizzeriaRepository repository;

    @GetMapping ("/PizzeriaSpring")
    public String main(Model model){

        return "main";
    }

    @GetMapping ("/PizzeriaSpring/index")
    public String index(Model model){
        List<Pizza> result = repository.findAll();
        model.addAttribute("listaPizze", result);
        return "index";
    }
}
