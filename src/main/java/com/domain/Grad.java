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
@Table(name = "grad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Grad.findAll", query = "SELECT g FROM Grad g")
    , @NamedQuery(name = "Grad.findByPostanskiBroj", query = "SELECT g FROM Grad g WHERE g.postanskiBroj = :postanskiBroj")
    , @NamedQuery(name = "Grad.findByNazivGrada", query = "SELECT g FROM Grad g WHERE g.nazivGrada = :nazivGrada")})
public class Grad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "postanskiBroj")
    private Integer postanskiBroj;
    @Size(max = 255)
    @Column(name = "nazivGrada")
    private String nazivGrada;
    @OneToMany(mappedBy = "grad")
    private Collection<Dobavljac> dobavljacCollection;

    public Grad() {
    }

    public Grad(Integer postanskiBroj) {
        this.postanskiBroj = postanskiBroj;
    }

    public Integer getPostanskiBroj() {
        return postanskiBroj;
    }

    public void setPostanskiBroj(Integer postanskiBroj) {
        this.postanskiBroj = postanskiBroj;
    }

    public String getNazivGrada() {
        return nazivGrada;
    }

    public void setNazivGrada(String nazivGrada) {
        this.nazivGrada = nazivGrada;
    }

    @XmlTransient
    public Collection<Dobavljac> getDobavljacCollection() {
        return dobavljacCollection;
    }

    public void setDobavljacCollection(Collection<Dobavljac> dobavljacCollection) {
        this.dobavljacCollection = dobavljacCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (postanskiBroj != null ? postanskiBroj.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Grad)) {
            return false;
        }
        Grad other = (Grad) object;
        if ((this.postanskiBroj == null && other.postanskiBroj != null) || (this.postanskiBroj != null && !this.postanskiBroj.equals(other.postanskiBroj))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.domain.Grad[ postanskiBroj=" + postanskiBroj + " ]";
    }
    
}
