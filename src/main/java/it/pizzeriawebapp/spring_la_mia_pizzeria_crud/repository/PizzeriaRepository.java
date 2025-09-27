package it.pizzeriawebapp.spring_la_mia_pizzeria_crud.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.pizzeriawebapp.spring_la_mia_pizzeria_crud.model.Pizza;

public interface PizzeriaRepository extends JpaRepository<Pizza, Integer>{

    // query  Custom sul nome della pizza anche con non nome completo
    public List<Pizza> findByNomeContainingIgnoreCase(String nome);

    // query  Custom sul nome COMPLETO della pizza
    public Optional<Pizza> findByNome(String nome);
}