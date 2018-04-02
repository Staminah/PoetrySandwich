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
import src.entities.User;
import javax.persistence.Query;

/**
 *
 * @author anthony.fleury
 */
@Stateless
public class UserFacade extends AbstractFacade<User> {

    @PersistenceContext(unitName = "PoetrySandwichPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(User.class);
    }
    
    public List<User> findUserByName(String s){
        Query queryAllValues = em.createNamedQuery("User.findByUsername");
        queryAllValues.setParameter("username", s);
        return queryAllValues.getResultList();
    }
    
}
