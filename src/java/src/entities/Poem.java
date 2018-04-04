/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author anthony.fleury
 */
@Entity
@Table(name = "tb_poem")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Poem.findAll", query = "SELECT p FROM Poem p")
    , @NamedQuery(name = "Poem.findByPkPoem", query = "SELECT p FROM Poem p WHERE p.pkPoem = :pkPoem")
    , @NamedQuery(name = "Poem.findByTitle", query = "SELECT p FROM Poem p WHERE p.title = :title")
    , @NamedQuery(name = "Poem.findByTag", query = "SELECT p FROM Poem p INNER JOIN p.tagCollection tag WHERE tag.name = :tag")
    , @NamedQuery(name = "Poem.findByCreationDate", query = "SELECT p FROM Poem p WHERE p.creationDate = :creationDate")
    , @NamedQuery(name = "Poem.findByValidated", query = "SELECT p FROM Poem p WHERE p.validated = :validated")
    , @NamedQuery(name = "Poem.findByValidatedCount", query = "SELECT COUNT(p.pkPoem) FROM Poem p WHERE p.validated = :validated")
    , @NamedQuery(name = "Poem.findByFkUser", query = "SELECT p FROM Poem p WHERE p.fkUser = :fkUser")})
public class Poem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_poem")
    private Integer pkPoem;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "title")
    private String title;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "content")
    private String content;
    @Basic(optional = false)
    @NotNull
    @Column(name = "creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "validated")
    private char validated;
    @JoinTable(name = "tb_poem_tag", joinColumns = {
        @JoinColumn(name = "pk_fk_poem", referencedColumnName = "pk_poem")}, inverseJoinColumns = {
        @JoinColumn(name = "pk_fk_tag", referencedColumnName = "pk_tag")})
    @ManyToMany
    private Collection<Tag> tagCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkPoem")
    private Collection<Comment> commentCollection;
    @JoinColumn(name = "fk_user", referencedColumnName = "pk_user")
    @ManyToOne(optional = false)
    private User fkUser;

    public Poem() {
        System.out.println("POEM");
    }

    public Poem(Integer pkPoem) {
        System.out.println("POEM INT");
        this.pkPoem = pkPoem;
    }

    public Poem(Integer pkPoem, String title, String content, Date creationDate, char validated) {
        System.out.println("POEM EVERYTHING");
        this.pkPoem = pkPoem;
        this.title = title;
        this.content = content;
        this.creationDate = creationDate;
        this.validated = validated;
    }

    public Integer getPkPoem() {
        return pkPoem;
    }

    public void setPkPoem(Integer pkPoem) {
        this.pkPoem = pkPoem;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public char getValidated() {
        return validated;
    }

    public void setValidated(char validated) {
        this.validated = validated;
    }

    @XmlTransient
    public Collection<Tag> getTagCollection() {
        return tagCollection;
    }

    public void setTagCollection(Collection<Tag> tagCollection) {
        this.tagCollection = tagCollection;
    }

    @XmlTransient
    public Collection<Comment> getCommentCollection() {
        return commentCollection;
    }

    public void setCommentCollection(Collection<Comment> commentCollection) {
        this.commentCollection = commentCollection;
    }

    public User getFkUser() {
        return fkUser;
    }

    public void setFkUser(User fkUser) {
        this.fkUser = fkUser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkPoem != null ? pkPoem.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Poem)) {
            return false;
        }
        Poem other = (Poem) object;
        if ((this.pkPoem == null && other.pkPoem != null) || (this.pkPoem != null && !this.pkPoem.equals(other.pkPoem))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "src.entities.Poem[ pkPoem=" + pkPoem + " " + this.title + " ]";
    }
    
}
