package it.pizzeriawebapp.spring_la_mia_pizzeria_crud.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.pizzeriawebapp.spring_la_mia_pizzeria_crud.model.Pizza;
import it.pizzeriawebapp.spring_la_mia_pizzeria_crud.repository.PizzeriaRepository;
import jakarta.validation.Valid;


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
    public String index(@RequestParam(name="keywordName", required=false) String keywordName, Model model){
        List<Pizza> result;
        if (keywordName == null || keywordName.isBlank()) {
            result = repository.findAll();
        }else{
            result = repository.findByNomeContainingIgnoreCase(keywordName);
        }
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

    @GetMapping("/PizzeriaSpring/NewPizza")
    public String AddPizza(Model model) {

        model.addAttribute("newPizza", new Pizza());

        return "addNewPizzaForm";
    }
    
    @PostMapping("/PizzeriaSpring/NewPizza")
    public String save(@Valid @ModelAttribute("newPizza") Pizza userInput, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        
        // controllo se non viene inserito un nuovo elemento con lo stesso nome
        Optional<Pizza> optionalPizza = repository.findByNome(userInput.getNome());
        if (optionalPizza.isPresent())
        {
            bindingResult.addError(new ObjectError("errorDuplictedName", "Pizza con lo stesso nome già presente!"));
            model.addAttribute("warningAlertMessage", " Pizza con lo stesso nome già presente!");
        }

        if(bindingResult.hasErrors()){
            return "addNewPizzaForm";
        }

        repository.save(userInput);
        redirectAttributes.addFlashAttribute("successAlertMessage", "Nuova pizza inerita correttamente!");
        return "redirect:/PizzeriaSpring/index";
    }

    @GetMapping("/PizzeriaSpring/ModificaPizza/{id}")
    public String ModPizza(@PathVariable("id") Integer id, Model model) {

        model.addAttribute("modPizza", repository.findById(id).get());

        return "modNewPizzaForm";
    }

    @PostMapping("/PizzeriaSpring/ModificaPizza/{id}")
    public String saveMod(@Valid @ModelAttribute("modPizza") Pizza userInput, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {

        // Controllo in BackEnd che il nome della pizza non venga modificato
        Pizza oldPizzaData = repository.findById(userInput.getId()).get();
        if(!oldPizzaData.getNome().equals(userInput.getNome())){
            bindingResult.addError(new ObjectError("errorModifiedName", "Non si può modificare il nome!"));
            model.addAttribute("warningAlertMessage", "Non si può modificare il nome!");
            // ritorniamo a mettere il vecchio nome
            userInput.setNome(oldPizzaData.getNome());
        }

        if(bindingResult.hasErrors()){
            return "modNewPizzaForm";
        }

        repository.save(userInput);
        redirectAttributes.addFlashAttribute("successAlertMessage", "Pizza modificata in modo corretto!");
        return "redirect:/PizzeriaSpring/index";
    }

    @PostMapping("/deletePizza/{id}")
    public String deletePizza(@PathVariable("id") Integer id){

        repository.deleteById(id);

        return "redirect:/PizzeriaSpring/index";
    }
    
}
