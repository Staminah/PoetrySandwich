/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author anthony.fleury
 */
@Entity
@Table(name = "tb_tag")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tag.findAll", query = "SELECT t FROM Tag t")
    , @NamedQuery(name = "Tag.findByPkTag", query = "SELECT t FROM Tag t WHERE t.pkTag = :pkTag")
    , @NamedQuery(name = "Tag.findByName", query = "SELECT t FROM Tag t WHERE t.name = :name")})
public class Tag implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_tag")
    private Integer pkTag;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "name")
    private String name;
    @JoinTable(name = "tb_poem_tag", joinColumns = {
        @JoinColumn(name = "pk_fk_tag", referencedColumnName = "pk_tag")}, inverseJoinColumns = {
        @JoinColumn(name = "pk_fk_poem", referencedColumnName = "pk_poem")})
    @ManyToMany
    private Collection<Poem> poemCollection;

    public Tag() {
    }

    public Tag(Integer pkTag) {
        this.pkTag = pkTag;
    }

    public Tag(Integer pkTag, String name) {
        this.pkTag = pkTag;
        this.name = name;
    }

    public Integer getPkTag() {
        return pkTag;
    }

    public void setPkTag(Integer pkTag) {
        this.pkTag = pkTag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        hash += (pkTag != null ? pkTag.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tag)) {
            System.out.println("equals not");
            return false;
        }
        Tag other = (Tag) object;
        if ((this.pkTag == null && other.pkTag != null) || (this.pkTag != null && !this.pkTag.equals(other.pkTag))) {
            System.out.println("equals maybe");
            return false;
        }
        System.out.println("equals");
        return true;
    }

    @Override
    public String toString() {
        return "src.entities.Tag[ pkTag=" + pkTag + " ]";
    }
    
}
