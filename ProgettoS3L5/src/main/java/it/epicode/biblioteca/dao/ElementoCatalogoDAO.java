package it.epicode.biblioteca.dao;

import it.epicode.biblioteca.model.ElementoCatalogo;
import it.epicode.biblioteca.model.Libro;
import it.epicode.biblioteca.util.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class ElementoCatalogoDAO {

    public void save(ElementoCatalogo elemento) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(elemento);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public ElementoCatalogo findByIsbn(String isbn) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(ElementoCatalogo.class, isbn);
        } finally {
            em.close();
        }
    }

    public void delete(String isbn) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            ElementoCatalogo elemento = em.find(ElementoCatalogo.class, isbn);
            if (elemento != null) {
                em.getTransaction().begin();
                em.remove(elemento);
                em.getTransaction().commit();
            }
        } finally {
            em.close();
        }
    }

    public List<ElementoCatalogo> findByAnno(int anno) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<ElementoCatalogo> query = em.createQuery(
                    "SELECT e FROM ElementoCatalogo e WHERE e.annoPubblicazione = :anno",
                    ElementoCatalogo.class);
            query.setParameter("anno", anno);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Libro> findByAutore(String autore) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Libro> query = em.createQuery(
                    "SELECT l FROM Libro l WHERE l.autore = :autore",
                    Libro.class);
            query.setParameter("autore", autore);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<ElementoCatalogo> findByTitolo(String titolo) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<ElementoCatalogo> query = em.createQuery(
                    "SELECT e FROM ElementoCatalogo e WHERE LOWER(e.titolo) LIKE LOWER(CONCAT('%', :titolo, '%'))",
                    ElementoCatalogo.class);
            query.setParameter("titolo", titolo);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}
