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
@Table(name = "tb_user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
    , @NamedQuery(name = "User.findByPkUser", query = "SELECT u FROM User u WHERE u.pkUser = :pkUser")
    , @NamedQuery(name = "User.findByUsername", query = "SELECT u FROM User u WHERE u.username = :username")
    , @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password")
    , @NamedQuery(name = "User.findByMail", query = "SELECT u FROM User u WHERE u.mail = :mail")
    , @NamedQuery(name = "User.findByCreationDate", query = "SELECT u FROM User u WHERE u.creationDate = :creationDate")})
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_user")
    private Integer pkUser;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "mail")
    private String mail;
    @Basic(optional = false)
    @NotNull
    @Column(name = "creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkUser")
    private Collection<Comment> commentCollection;
    @JoinColumn(name = "fk_role", referencedColumnName = "pk_role")
    @ManyToOne
    private Role fkRole;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkUser")
    private Collection<Poem> poemCollection;

    public User() {
    }

    public User(Integer pkUser) {
        this.pkUser = pkUser;
    }

    public User(Integer pkUser, String username, String password, String mail, Date creationDate) {
        this.pkUser = pkUser;
        this.username = username;
        this.password = password;
        this.mail = mail;
        this.creationDate = creationDate;
    }

    public Integer getPkUser() {
        return pkUser;
    }

    public void setPkUser(Integer pkUser) {
        this.pkUser = pkUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @XmlTransient
    public Collection<Comment> getCommentCollection() {
        return commentCollection;
    }

    public void setCommentCollection(Collection<Comment> commentCollection) {
        this.commentCollection = commentCollection;
    }

    public Role getFkRole() {
        return fkRole;
    }

    public void setFkRole(Role fkRole) {
        this.fkRole = fkRole;
    }

    @XmlTransient
    public Collection<Poem> getPoemCollection() {
        return poemCollection;
    }

    public void setPoemCollection(Collection<Poem> poemCollection) {
        this.poemCollection = poemCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkUser != null ? pkUser.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.pkUser == null && other.pkUser != null) || (this.pkUser != null && !this.pkUser.equals(other.pkUser))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "src.entities.User[ pkUser=" + pkUser + " ]";
    }
    
}
