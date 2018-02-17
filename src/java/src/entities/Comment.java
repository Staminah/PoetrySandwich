/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author anthony.fleury
 */
@Entity
@Table(name = "tb_comment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Comment.findAll", query = "SELECT c FROM Comment c")
    , @NamedQuery(name = "Comment.findByPkComment", query = "SELECT c FROM Comment c WHERE c.pkComment = :pkComment")})
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_comment")
    private Integer pkComment;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "content")
    private String content;
    @JoinColumn(name = "fk_user", referencedColumnName = "pk_user")
    @ManyToOne(optional = false)
    private User fkUser;
    @JoinColumn(name = "fk_poem", referencedColumnName = "pk_poem")
    @ManyToOne(optional = false)
    private Poem fkPoem;

    public Comment() {
    }

    public Comment(Integer pkComment) {
        this.pkComment = pkComment;
    }

    public Comment(Integer pkComment, String content) {
        this.pkComment = pkComment;
        this.content = content;
    }

    public Integer getPkComment() {
        return pkComment;
    }

    public void setPkComment(Integer pkComment) {
        this.pkComment = pkComment;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getFkUser() {
        return fkUser;
    }

    public void setFkUser(User fkUser) {
        this.fkUser = fkUser;
    }

    public Poem getFkPoem() {
        return fkPoem;
    }

    public void setFkPoem(Poem fkPoem) {
        this.fkPoem = fkPoem;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkComment != null ? pkComment.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Comment)) {
            return false;
        }
        Comment other = (Comment) object;
        if ((this.pkComment == null && other.pkComment != null) || (this.pkComment != null && !this.pkComment.equals(other.pkComment))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "src.entities.Comment[ pkComment=" + pkComment + " ]";
    }
    
}
