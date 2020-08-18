/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.domain;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Sara Jovanovic
 */
@Entity
@Table(name = "stavke_narudzbenice")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StavkeNarudzbenice.findAll", query = "SELECT s FROM StavkeNarudzbenice s")
    , @NamedQuery(name = "StavkeNarudzbenice.findByRbStavke", query = "SELECT s FROM StavkeNarudzbenice s WHERE s.stavkeNarudzbenicePK.rbStavke = :rbStavke")
    , @NamedQuery(name = "StavkeNarudzbenice.findByBrojNarudzbenice", query = "SELECT s FROM StavkeNarudzbenice s WHERE s.stavkeNarudzbenicePK.brojNarudzbenice = :brojNarudzbenice")
    , @NamedQuery(name = "StavkeNarudzbenice.findByOpisStavke", query = "SELECT s FROM StavkeNarudzbenice s WHERE s.opisStavke = :opisStavke")
    , @NamedQuery(name = "StavkeNarudzbenice.findByKolicina", query = "SELECT s FROM StavkeNarudzbenice s WHERE s.kolicina = :kolicina")})
public class StavkeNarudzbenice implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected StavkeNarudzbenicePK stavkeNarudzbenicePK;
    @Size(max = 200)
    @Column(name = "opisStavke")
    private String opisStavke;
    @Size(max = 200)
    @Column(name = "kolicina")
    private String kolicina;
    @JoinColumn(name = "brojNarudzbenice", referencedColumnName = "brojNarudzbenice", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Narudzbenica narudzbenica;
    @JoinColumn(name = "sifraProizvoda", referencedColumnName = "sifraProizvoda")
    @ManyToOne
    private Proizvod sifraProizvoda;
    @Transient
    private Status status;

    public StavkeNarudzbenice() {
        status = Status.NEW;
    }

    public StavkeNarudzbenice(StavkeNarudzbenicePK stavkeNarudzbenicePK) {
        this.stavkeNarudzbenicePK = stavkeNarudzbenicePK;
    }

    public StavkeNarudzbenice(int rbStavke, int brojNarudzbenice) {
        this.stavkeNarudzbenicePK = new StavkeNarudzbenicePK(rbStavke, brojNarudzbenice);
    }

    public StavkeNarudzbenicePK getStavkeNarudzbenicePK() {
        return stavkeNarudzbenicePK;
    }

    public void setStavkeNarudzbenicePK(StavkeNarudzbenicePK stavkeNarudzbenicePK) {
        this.stavkeNarudzbenicePK = stavkeNarudzbenicePK;
    }

    public String getOpisStavke() {
        return opisStavke;
    }

    public void setOpisStavke(String opisStavke) {
        this.opisStavke = opisStavke;
    }

    public String getKolicina() {
        return kolicina;
    }

    public void setKolicina(String kolicina) {
        this.kolicina = kolicina;
    }

    public Narudzbenica getNarudzbenica() {
        return narudzbenica;
    }

    public void setNarudzbenica(Narudzbenica narudzbenica) {
        this.narudzbenica = narudzbenica;
    }

    public Proizvod getSifraProizvoda() {
        return sifraProizvoda;
    }

    public void setSifraProizvoda(Proizvod sifraProizvoda) {
        this.sifraProizvoda = sifraProizvoda;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (stavkeNarudzbenicePK != null ? stavkeNarudzbenicePK.hashCode() : 0);
        return hash;
    }

//    @Override
//    public boolean equals(Object object) {
//        // TODO: Warning - this method won't work in the case the id fields are not set
//        if (!(object instanceof StavkeNarudzbenice)) {
//            return false;
//        }
//        StavkeNarudzbenice other = (StavkeNarudzbenice) object;
//        if ((this.stavkeNarudzbenicePK == null && other.stavkeNarudzbenicePK != null) || (this.stavkeNarudzbenicePK != null && !this.stavkeNarudzbenicePK.equals(other.stavkeNarudzbenicePK))) {
//            return false;
//        }
//        return true;
//    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final StavkeNarudzbenice other = (StavkeNarudzbenice) obj;
        if (!Objects.equals(this.opisStavke, other.opisStavke)) {
            return false;
        }
        if (!Objects.equals(this.kolicina, other.kolicina)) {
            return false;
        }
        if (!Objects.equals(this.stavkeNarudzbenicePK, other.stavkeNarudzbenicePK)) {
            return false;
        }
        return true;
    }

    
    
    @Override
    public String toString() {
        return "com.domain.StavkeNarudzbenice[ stavkeNarudzbenicePK=" + stavkeNarudzbenicePK + " ]";
    }
    
    public void copy(StavkeNarudzbenice sn)
    {
        opisStavke = sn.opisStavke;
        kolicina = sn.kolicina;
        sifraProizvoda = sn.sifraProizvoda;
        status = sn.status;
                
    }
    
    public void copy2(StavkeNarudzbenice sn)
    {
        stavkeNarudzbenicePK = sn.stavkeNarudzbenicePK;
        opisStavke = sn.opisStavke;
        kolicina = sn.kolicina;
        sifraProizvoda = sn.sifraProizvoda;
        status = sn.status;
                
    }
    
}
