package it.pizzeriawebapp.spring_la_mia_pizzeria_crud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.pizzeriawebapp.spring_la_mia_pizzeria_crud.model.Pizza;

public interface PizzeriaRepository extends JpaRepository<Pizza, Integer>{

    // query  Custom sul nome della pizza
    public List<Pizza> findByNomeContainingIgnoreCase(String nome);

}
