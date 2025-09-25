package it.pizzeriawebapp.spring_la_mia_pizzeria_crud.model;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "pizze")
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @NotBlank (message="Nome obbligatorio!")
    private String nome;

    @NotNull
    @NotBlank (message="Fornire un minimo di descrizione!")
    private String descrizione;

    private String urlFoto;

    @NotNull (message="Inserire un prezzo!")
    @Min(value=0, message="Inserire un prezzo valido!")
    private double prezzo;


    // Setters and Getters
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    // conversione in valuta dell'attributo prezzo
    public String PrezzoFormatter (){
        BigDecimal prezzoConvertito = new BigDecimal(this.prezzo);
        NumberFormat formatterCurrencyTemplate = NumberFormat.getCurrencyInstance(Locale.ITALY);
        String PrezzoFormattato = formatterCurrencyTemplate.format(prezzoConvertito);
        return PrezzoFormattato;
    }
}
