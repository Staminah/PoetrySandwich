/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.facades;

import java.util.List;
import javax.ejb.Stateless;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import src.entities.Poem;
import src.entities.User;

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
    }
    
    public List<Poem> getPoemByUser(User fkUser)
    {
        return getEntityManager().createNamedQuery("Poem.findByFkUser").setParameter("fkUser", fkUser).getResultList();
    }
    
    @Override
    public List<Poem> findRange(int[] range) {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest)context.getRequest();
                
        javax.persistence.Query q;
        
        if(!request.isUserInRole("ADMIN")){
            q = getEntityManager().createNamedQuery("Poem.findByValidated").setParameter("validated", 'v');
            q.setMaxResults(range[1] - range[0] + 1);
            q.setFirstResult(range[0]);
            return q.getResultList();
        }
 
        return super.findRange(range);

    }
    
    @Override
    public int count() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest)context.getRequest();
        
        javax.persistence.Query q;
        
        if(!request.isUserInRole("ADMIN")){
            q = getEntityManager().createNamedQuery("Poem.findByValidatedCount").setParameter("validated", 'v');
            return ((Long) q.getSingleResult()).intValue();
        }

        return super.count();
    }
    
}
