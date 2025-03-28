package it.epicode.biblioteca.dao;

import it.epicode.biblioteca.model.Utente;
import it.epicode.biblioteca.util.JPAUtil;
import jakarta.persistence.EntityManager;

public class UtenteDAO {

    public void save(Utente utente) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(utente);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public Utente findByNumeroTessera(String numeroTessera) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(Utente.class, numeroTessera);
        } finally {
            em.close();
        }
    }

    public void delete(String numeroTessera) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            Utente utente = em.find(Utente.class, numeroTessera);
            if (utente != null) {
                em.getTransaction().begin();
                em.remove(utente);
                em.getTransaction().commit();
            }
        } finally {
            em.close();
        }
    }
}
