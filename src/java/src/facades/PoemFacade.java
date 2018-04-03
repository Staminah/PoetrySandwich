/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.facades;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import src.entities.Poem;

/**
 *
 * @author anthony.fleury
 */
@Stateless
public class PoemFacade extends AbstractFacade<Poem> {

    @PersistenceContext(unitName = "PoetrySandwichPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PoemFacade() {
        super(Poem.class);
        System.out.println("POEM FACADE");
    }
    
    public List<Poem> getPoemsByTitle (String title){
        return em.createNamedQuery("Poem.findByTitle").setParameter("title", title).getResultList();
    }
    
    public List<Poem> getPoemsByTag (String tag){
        return em.createNamedQuery("Poem.findByTag").setParameter("tag", tag).getResultList();
    }
    
}
