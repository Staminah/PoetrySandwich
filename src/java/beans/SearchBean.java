/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import src.entities.Poem;
import src.facades.PoemFacade;

 /**
 *
 * @author marc.schnaebe
 */
@Named(value = "searchBean")
//@ManagedBean
@SessionScoped
public class SearchBean implements Serializable{

    private String keyword;
    private String searchBy;
    @EJB
    private src.facades.PoemFacade poemFacade;
    

    public SearchBean() {
    }

    public void setKeyword(String keyword) {
    this.keyword = keyword;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setSearchBy(String searchBy) {
        this.searchBy = searchBy;
    }

    public String getSearchBy() {
        return searchBy;
    }

    public List<Poem> getPoems() {
        List<Poem> poems;
        System.out.println("POEMS");
        if(null == keyword || "".equals(keyword)) {
            poems = new ArrayList<Poem>();
        } else {
            if(searchBy.equals("PoemTitle")){
                
                poems = poemFacade.getPoemsByTitle(keyword);
            }
            else{
                poems = poemFacade.getPoemsByTag(keyword);
            }
            
        }
        
        
        return poems;
    }
}

