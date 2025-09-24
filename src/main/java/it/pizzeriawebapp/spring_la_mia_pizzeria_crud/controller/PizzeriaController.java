package it.pizzeriawebapp.spring_la_mia_pizzeria_crud.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping ("/PizzeriaSpring/Detail/{id}")
    public String index(@PathVariable("id") Integer id, Model model){
        Optional<Pizza> optionalPizza = repository.findById(id);
        if(optionalPizza.isPresent()){
            model.addAttribute("selectedPizza", optionalPizza.get());
            model.addAttribute("isSelectedPizza", true);
        }else{
            model.addAttribute("isSelectedPizza", false);

        }
        return "showDetail";
    }
}
