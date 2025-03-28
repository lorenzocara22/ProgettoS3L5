package it.epicode.biblioteca.dao;

import it.epicode.biblioteca.model.Prestito;
import it.epicode.biblioteca.util.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.time.LocalDate;
import java.util.List;

public class PrestitoDAO {

    public void save(Prestito prestito) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(prestito);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public List<Prestito> findByNumeroTessera(String numeroTessera) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Prestito> query = em.createQuery(
                    "SELECT p FROM Prestito p WHERE p.utente.numeroTessera = :numero", Prestito.class);
            query.setParameter("numero", numeroTessera);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Prestito> findPrestitiScadutiNonRestituiti() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Prestito> query = em.createQuery(
                    "SELECT p FROM Prestito p WHERE p.dataRestituzionePrevista < :oggi AND p.dataRestituzioneEffettiva IS NULL",
                    Prestito.class);
            query.setParameter("oggi", LocalDate.now());
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}
