package src.controllers;

import src.entities.Poem;
import src.entities.User;
import src.entities.Comment;

import src.controllers.util.JsfUtil;
import src.controllers.util.PaginationHelper;
import src.facades.PoemFacade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

import javax.faces.context.ExternalContext;
import javax.servlet.http.HttpServletRequest;

import java.util.Date;
import java.util.List;

@Named("poemController")
@SessionScoped
public class PoemController implements Serializable {

    private Poem current;
    private DataModel items = null;
    @EJB
    private src.facades.PoemFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    private String tempComment = "";
    private String searchContent = "";
    private String searchFilter = "";
    private List<Poem> searchedPoems;
    private ArrayList<Comment> sortedCommentCollection;

    public PoemController() {
    }

    public Poem getSelected() {
        if (current == null) {
            current = new Poem();
            selectedItemIndex = -1;
        }
        return current;
    }

    private PoemFacade getFacade() {
        System.out.println("POEM CONTROLLER FACADE");
        return ejbFacade;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(5) {

                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        recreateModel();
        return "/faces/templates/poem/List.xhtml";
    }
    
    public DataModel getPoemListByUser(){
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest)context.getRequest();
        User currentUser = getFacade().getCurrentUser(request.getRemoteUser());
        
        return new ListDataModel(getFacade().getPoemByUser(currentUser));
        
    }

    public String prepareView() {
        current = (Poem) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "/faces/templates/poem/View.xhtml";
    }
    
    public String prepareViewForItem(Poem item) {
        current = item;
        sortedCommentCollection = new ArrayList<>(current.getCommentCollection());
        sortedCommentCollection.sort(Comparator.comparing(Comment::getCreationDate).reversed());
        return "/faces/templates/poem/View.xhtml";
    }

    public DataModel getPoemByValidationState(char state){
        return new ListDataModel(getFacade().getPoemByValidationState(state)); 
    }
    
    public String updateValidationState(Poem poem, char value) {        
        
        poem.setValidated(value);
        System.out.println("---------------POPOPO----------------------");
        System.out.println(poem);
        System.out.println(value);
        System.out.println(poem.getValidated());
        System.out.println("---------------POPOPO----------------------");
        
        try {
            getFacade().edit(poem);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("PoemValidationStateUpdated"));
            return "/faces/Editor/poem/List.xhtml";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareCreate() {
        current = new Poem();
        // Creation Date
        current.setCreationDate(new Date());
        // User 
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest)context.getRequest();
        User currentUser = getFacade().getCurrentUser(request.getRemoteUser());
        
        char validated = 'v';
        
        if(!request.isUserInRole("ADMIN")){
            current.setFkUser(currentUser); 
            validated = 'p';
        }
        // Poem Validation
        current.setValidated(validated);

        
        selectedItemIndex = -1;
        return "/faces/Author/poem/Create.xhtml";
    }

    public String create() {
        try {
            System.out.println("POEM CONTROLLER CREATE");
            
            System.out.println(current.getTagCollection());
            System.out.println(current.getFkUser());
            getFacade().create(current);
            
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("PoemCreated"));
            return prepareCreate();
        } catch (Exception e) {
            System.out.println("POEM CONTROLLER FAIL");
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (Poem) getItems().getRowData();
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest)context.getRequest();
        User currentUser = getFacade().getCurrentUser(request.getRemoteUser());
        
        char validated = 'v';
        
        if(!request.isUserInRole("ADMIN")){
            current.setFkUser(currentUser); 
            validated = 'p';
        }
        // Poem Validation
        current.setValidated(validated);
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "/faces/Author/poem/Edit.xhtml";
    }
    
    public String prepareEditForItem(Poem item) {
        current = item;
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest)context.getRequest();
        User currentUser = getFacade().getCurrentUser(request.getRemoteUser());
        
        char validated = 'v';
        
        if(!request.isUserInRole("ADMIN")){
            current.setFkUser(currentUser); 
            validated = 'p';
        }
        // Poem Validation
        current.setValidated(validated);
        return "/faces/Author/poem/Edit.xhtml";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("PoemUpdated"));
            return "/faces/Author/poem/List.xhtml";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }
    
    public void removeComment(Comment comment){
        
        try {      
            sortedCommentCollection.remove(comment);
            getFacade().removeComment(comment);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("CommentDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }
    
    public void addComment(String username)
    {
        Comment comment = new Comment();
        
        comment.setFkPoem(current);
        current.addComment(comment);
        
        comment.setFkUser(getFacade().getCurrentUser(username));
        comment.setContent(tempComment);
        comment.setCreationDate(new Date());
        
        sortedCommentCollection.add(0, comment);        
  
        tempComment = "";

        try {
            getFacade().createComment(comment);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("CommentCreated"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }

    }

    public String destroy() {
        current = (Poem) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "/faces/Author/poem/List.xhtml";
    }
    
    public String destroyItem(Poem item) {
        current = item;
        performDestroy();
        recreatePagination();
        recreateModel();
        return "/faces/Author/poem/List.xhtml";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "/faces/templates/poem/View.xhtml";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "/faces/templates/poem/List.xhtml";
        }
    }
    
    public String searchPoem() {
        searchedPoems = null;
        
        if (searchFilter.equals("") || searchContent.equals("") || searchFilter.equals("Filter")) {
            searchedPoems = null;
        }
        else {
            if (searchFilter.equals("Title")) {
                searchedPoems = getFacade().getValidatedPoemsByTitle("%" + searchContent + "%");
            }
            else if (searchFilter.equals("Tag")) {
                searchedPoems = getFacade().getValidatedPoemsByTag("%" + searchContent + "%");
            }
            else {
                searchedPoems = getFacade().getValidatedPoemsByUsername("%" + searchContent + "%");
            }
        }
        
        return "/faces/search.xhtml";
    }
    
    public DataModel getSearchedPoems() {
        if (searchedPoems != null) {
            return new ListDataModel(searchedPoems);
        }
        else {
            return null;
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("PoemDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

    public DataModel getItems() {
        recreateModel();
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "/index.xhtml"; // "/faces/templates/poem/List.xhtml";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "/index.xhtml"; // "/faces/templates/poem/List.xhtml";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    public Poem getPoem(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = Poem.class)
    public static class PoemControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PoemController controller = (PoemController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "poemController");
            return controller.getPoem(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Poem) {
                Poem o = (Poem) object;
                return getStringKey(o.getPkPoem());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Poem.class.getName());
            }
        }

    }
    
    public String getTempComment() {
        return tempComment;
    }

    public void setTempComment(String comment) {
        this.tempComment = comment;
    }
    
    public ArrayList<Comment> getSortedCommentCollection() {
        return sortedCommentCollection;
    }

    public void setSortedCommentCollection(ArrayList<Comment> list) {
        this.sortedCommentCollection = list;
    }
    
    public String getSearchContent() {
        return searchContent;
    }
    
    public void setSearchContent(String content) {
        this.searchContent = content;
    }

    public String getSearchFilter() {
        return searchFilter;
    }
    
    public void setSearchFilter(String filter) {
        this.searchFilter = filter;
    }
}
