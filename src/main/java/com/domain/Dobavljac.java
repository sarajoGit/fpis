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
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
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
@Table(name = "dobavljac")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Dobavljac.findAll", query = "SELECT d FROM Dobavljac d")
    , @NamedQuery(name = "Dobavljac.findByPIBDobavljaca", query = "SELECT d FROM Dobavljac d WHERE d.pIBDobavljaca = :pIBDobavljaca")
    , @NamedQuery(name = "Dobavljac.findByNazivDobavljaca", query = "SELECT d FROM Dobavljac d WHERE d.nazivDobavljaca = :nazivDobavljaca")
    , @NamedQuery(name = "Dobavljac.findByTelefonDobavljaca", query = "SELECT d FROM Dobavljac d WHERE d.telefonDobavljaca = :telefonDobavljaca")
    , @NamedQuery(name = "Dobavljac.findByEmailDobavljaca", query = "SELECT d FROM Dobavljac d WHERE d.emailDobavljaca = :emailDobavljaca")})
public class Dobavljac implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PIBDobavljaca")
    private Integer pIBDobavljaca;
    @Size(max = 200)
    @Column(name = "nazivDobavljaca")
    private String nazivDobavljaca;
    @Size(max = 20)
    @Column(name = "telefonDobavljaca")
    private String telefonDobavljaca;
    @Size(max = 100)
    @Column(name = "emailDobavljaca")
    private String emailDobavljaca;
    @JoinColumns({
        @JoinColumn(name = "postanskiBroj", referencedColumnName = "postanskiBroj")
        , @JoinColumn(name = "postanskiBroj", referencedColumnName = "postanskiBroj")
        , @JoinColumn(name = "postanskiBroj", referencedColumnName = "postanskiBroj")})
    @ManyToOne
    private Grad grad;
    @OneToMany(mappedBy = "pIBDobavljaca")
    private Collection<ZahtevZaKatalog> zahtevZaKatalogCollection;

    public Dobavljac() {
    }

    public Dobavljac(Integer pIBDobavljaca) {
        this.pIBDobavljaca = pIBDobavljaca;
    }

    public Integer getPIBDobavljaca() {
        return pIBDobavljaca;
    }

    public void setPIBDobavljaca(Integer pIBDobavljaca) {
        this.pIBDobavljaca = pIBDobavljaca;
    }

    public String getNazivDobavljaca() {
        return nazivDobavljaca;
    }

    public void setNazivDobavljaca(String nazivDobavljaca) {
        this.nazivDobavljaca = nazivDobavljaca;
    }

    public String getTelefonDobavljaca() {
        return telefonDobavljaca;
    }

    public void setTelefonDobavljaca(String telefonDobavljaca) {
        this.telefonDobavljaca = telefonDobavljaca;
    }

    public String getEmailDobavljaca() {
        return emailDobavljaca;
    }

    public void setEmailDobavljaca(String emailDobavljaca) {
        this.emailDobavljaca = emailDobavljaca;
    }

    public Grad getGrad() {
        return grad;
    }

    public void setGrad(Grad grad) {
        this.grad = grad;
    }

    @XmlTransient
    public Collection<ZahtevZaKatalog> getZahtevZaKatalogCollection() {
        return zahtevZaKatalogCollection;
    }

    public void setZahtevZaKatalogCollection(Collection<ZahtevZaKatalog> zahtevZaKatalogCollection) {
        this.zahtevZaKatalogCollection = zahtevZaKatalogCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pIBDobavljaca != null ? pIBDobavljaca.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dobavljac)) {
            return false;
        }
        Dobavljac other = (Dobavljac) object;
        if ((this.pIBDobavljaca == null && other.pIBDobavljaca != null) || (this.pIBDobavljaca != null && !this.pIBDobavljaca.equals(other.pIBDobavljaca))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nazivDobavljaca;
    }
    
}
