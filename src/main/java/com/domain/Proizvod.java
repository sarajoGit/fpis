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
@Table(name = "proizvod")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Proizvod.findAll", query = "SELECT p FROM Proizvod p")
    , @NamedQuery(name = "Proizvod.findBySifraProizvoda", query = "SELECT p FROM Proizvod p WHERE p.sifraProizvoda = :sifraProizvoda")
    , @NamedQuery(name = "Proizvod.findByCenaProizvoda", query = "SELECT p FROM Proizvod p WHERE p.cenaProizvoda = :cenaProizvoda")
    , @NamedQuery(name = "Proizvod.findByNazivProizvoda", query = "SELECT p FROM Proizvod p WHERE p.nazivProizvoda = :nazivProizvoda")})
public class Proizvod implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "sifraProizvoda")
    private String sifraProizvoda;
    @Column(name = "cenaProizvoda")
    private Long cenaProizvoda;
    @Size(max = 200)
    @Column(name = "nazivProizvoda")
    private String nazivProizvoda;
    @OneToMany(mappedBy = "sifraProizvoda")
    private Collection<StavkeNarudzbenice> stavkeNarudzbeniceCollection;

    public Proizvod() {
    }

    public Proizvod(String sifraProizvoda) {
        this.sifraProizvoda = sifraProizvoda;
    }

    public String getSifraProizvoda() {
        return sifraProizvoda;
    }

    public void setSifraProizvoda(String sifraProizvoda) {
        this.sifraProizvoda = sifraProizvoda;
    }

    public Long getCenaProizvoda() {
        return cenaProizvoda;
    }

    public void setCenaProizvoda(Long cenaProizvoda) {
        this.cenaProizvoda = cenaProizvoda;
    }

    public String getNazivProizvoda() {
        return nazivProizvoda;
    }

    public void setNazivProizvoda(String nazivProizvoda) {
        this.nazivProizvoda = nazivProizvoda;
    }

    @XmlTransient
    public Collection<StavkeNarudzbenice> getStavkeNarudzbeniceCollection() {
        return stavkeNarudzbeniceCollection;
    }

    public void setStavkeNarudzbeniceCollection(Collection<StavkeNarudzbenice> stavkeNarudzbeniceCollection) {
        this.stavkeNarudzbeniceCollection = stavkeNarudzbeniceCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sifraProizvoda != null ? sifraProizvoda.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proizvod)) {
            return false;
        }
        Proizvod other = (Proizvod) object;
        if ((this.sifraProizvoda == null && other.sifraProizvoda != null) || (this.sifraProizvoda != null && !this.sifraProizvoda.equals(other.sifraProizvoda))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nazivProizvoda;
    }

    
}
