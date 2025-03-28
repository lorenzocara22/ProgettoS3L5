package it.epicode.biblioteca.app;

import it.epicode.biblioteca.dao.*;
import it.epicode.biblioteca.model.*;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        UtenteDAO utenteDAO = new UtenteDAO();
        LibroDAO libroDAO = new LibroDAO();
        RivistaDAO rivistaDAO = new RivistaDAO();
        PrestitoDAO prestitoDAO = new PrestitoDAO();


        Utente utente = new Utente("Mario", "Rossi", LocalDate.of(1985, 5, 10), "ABC123");
        utenteDAO.save(utente);


        Libro libro = new Libro("123456789", "Il Signore degli Anelli", 1954, 1200, "Tolkien", "Fantasy");
        libroDAO.save(libro);


        Rivista rivista = new Rivista("987654321", "Focus", 2023, 50, Periodicita.MENSILE);
        rivistaDAO.save(rivista);


        Prestito prestito = new Prestito(utente, libro, LocalDate.now());
        prestitoDAO.save(prestito);


        System.out.println("Prestiti per tessera ABC123:");
        prestitoDAO.findByNumeroTessera("ABC123").forEach(System.out::println);


        System.out.println("Prestiti scaduti non restituiti:");
        prestitoDAO.findPrestitiScadutiNonRestituiti().forEach(System.out::println);
    }
}
