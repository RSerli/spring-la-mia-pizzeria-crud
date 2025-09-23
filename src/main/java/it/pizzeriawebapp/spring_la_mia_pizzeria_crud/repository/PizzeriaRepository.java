package it.pizzeriawebapp.spring_la_mia_pizzeria_crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.pizzeriawebapp.spring_la_mia_pizzeria_crud.model.Pizza;

public interface PizzeriaRepository extends JpaRepository<Pizza, Integer>{

}
