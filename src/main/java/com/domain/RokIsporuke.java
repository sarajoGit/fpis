/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.domain;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Sara Jovanovic
 */
@Entity
@Table(name = "rok_isporuke")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RokIsporuke.findAll", query = "SELECT r FROM RokIsporuke r")
    , @NamedQuery(name = "RokIsporuke.findByRokIsporukeID", query = "SELECT r FROM RokIsporuke r WHERE r.rokIsporukeID = :rokIsporukeID")
    , @NamedQuery(name = "RokIsporuke.findByBrojDana", query = "SELECT r FROM RokIsporuke r WHERE r.brojDana = :brojDana")})
public class RokIsporuke implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "rokIsporukeID")
    private Integer rokIsporukeID;
    @Size(max = 200)
    @Column(name = "brojDana")
    private String brojDana;
    @OneToMany(mappedBy = "rokIsporuke")
    private Collection<Narudzbenica> narudzbenicaCollection;

    public RokIsporuke() {
    }

    public RokIsporuke(Integer rokIsporukeID) {
        this.rokIsporukeID = rokIsporukeID;
    }

    public Integer getRokIsporukeID() {
        return rokIsporukeID;
    }

    public void setRokIsporukeID(Integer rokIsporukeID) {
        this.rokIsporukeID = rokIsporukeID;
    }

    public String getBrojDana() {
        return brojDana;
    }

    public void setBrojDana(String brojDana) {
        this.brojDana = brojDana;
    }

    @XmlTransient
    public Collection<Narudzbenica> getNarudzbenicaCollection() {
        return narudzbenicaCollection;
    }

    public void setNarudzbenicaCollection(Collection<Narudzbenica> narudzbenicaCollection) {
        this.narudzbenicaCollection = narudzbenicaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rokIsporukeID != null ? rokIsporukeID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RokIsporuke)) {
            return false;
        }
        RokIsporuke other = (RokIsporuke) object;
        if ((this.rokIsporukeID == null && other.rokIsporukeID != null) || (this.rokIsporukeID != null && !this.rokIsporukeID.equals(other.rokIsporukeID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return brojDana;
    }
    
}
