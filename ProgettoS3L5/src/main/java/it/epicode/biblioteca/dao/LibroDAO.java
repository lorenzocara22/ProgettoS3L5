package it.epicode.biblioteca.dao;

import it.epicode.biblioteca.model.Libro;
import it.epicode.biblioteca.util.JPAUtil;
import jakarta.persistence.EntityManager;

public class LibroDAO {

    public void save(Libro libro) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(libro);
        em.getTransaction().commit();
        em.close();
    }
}
