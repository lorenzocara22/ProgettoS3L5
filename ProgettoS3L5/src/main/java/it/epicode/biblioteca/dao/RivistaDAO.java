package it.epicode.biblioteca.dao;

import it.epicode.biblioteca.model.Rivista;
import it.epicode.biblioteca.util.JPAUtil;
import jakarta.persistence.EntityManager;

public class RivistaDAO {

    public void save(Rivista rivista) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(rivista);
        em.getTransaction().commit();
        em.close();
    }
}
