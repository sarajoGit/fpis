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
@Table(name = "zaposleni")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Zaposleni.findAll", query = "SELECT z FROM Zaposleni z")
    , @NamedQuery(name = "Zaposleni.findByJMBGZaposlenog", query = "SELECT z FROM Zaposleni z WHERE z.jMBGZaposlenog = :jMBGZaposlenog")
    , @NamedQuery(name = "Zaposleni.findByImePrezime", query = "SELECT z FROM Zaposleni z WHERE z.imePrezime = :imePrezime")})
public class Zaposleni implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "JMBGZaposlenog")
    private Integer jMBGZaposlenog;
    @Size(max = 100)
    @Column(name = "imePrezime")
    private String imePrezime;
    @OneToMany(mappedBy = "jMBGZaposlenog")
    private Collection<ZahtevZaKatalog> zahtevZaKatalogCollection;
    @OneToMany(mappedBy = "zaposleni")
    private Collection<Narudzbenica> narudzbenicaCollection;

    public Zaposleni() {
    }

    public Zaposleni(Integer jMBGZaposlenog) {
        this.jMBGZaposlenog = jMBGZaposlenog;
    }

    public Integer getJMBGZaposlenog() {
        return jMBGZaposlenog;
    }

    public void setJMBGZaposlenog(Integer jMBGZaposlenog) {
        this.jMBGZaposlenog = jMBGZaposlenog;
    }

    public String getImePrezime() {
        return imePrezime;
    }

    public void setImePrezime(String imePrezime) {
        this.imePrezime = imePrezime;
    }

    @XmlTransient
    public Collection<ZahtevZaKatalog> getZahtevZaKatalogCollection() {
        return zahtevZaKatalogCollection;
    }

    public void setZahtevZaKatalogCollection(Collection<ZahtevZaKatalog> zahtevZaKatalogCollection) {
        this.zahtevZaKatalogCollection = zahtevZaKatalogCollection;
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
        hash += (jMBGZaposlenog != null ? jMBGZaposlenog.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Zaposleni)) {
            return false;
        }
        Zaposleni other = (Zaposleni) object;
        if ((this.jMBGZaposlenog == null && other.jMBGZaposlenog != null) || (this.jMBGZaposlenog != null && !this.jMBGZaposlenog.equals(other.jMBGZaposlenog))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return imePrezime;
    }
    
}
