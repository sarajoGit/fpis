/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.domain;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Sara Jovanovic
 */
@Entity
@Table(name = "zahtev_za_katalog")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ZahtevZaKatalog.findAll", query = "SELECT z FROM ZahtevZaKatalog z")
    , @NamedQuery(name = "ZahtevZaKatalog.findByBrojKataloga", query = "SELECT z FROM ZahtevZaKatalog z WHERE z.brojKataloga = :brojKataloga")
    , @NamedQuery(name = "ZahtevZaKatalog.findByImeKataloga", query = "SELECT z FROM ZahtevZaKatalog z WHERE z.imeKataloga = :imeKataloga")})
public class ZahtevZaKatalog implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "brojKataloga")
    private Integer brojKataloga;
    @Size(max = 30)
    @Column(name = "imeKataloga")
    private String imeKataloga;
    @JoinColumn(name = "PIBDobavljaca", referencedColumnName = "PIBDobavljaca")
    @ManyToOne
    private Dobavljac pIBDobavljaca;
    @JoinColumn(name = "JMBGZaposlenog", referencedColumnName = "JMBGZaposlenog")
    @ManyToOne
    private Zaposleni jMBGZaposlenog;

    public ZahtevZaKatalog() {
    }

    public ZahtevZaKatalog(Integer brojKataloga) {
        this.brojKataloga = brojKataloga;
    }

    public Integer getBrojKataloga() {
        return brojKataloga;
    }

    public void setBrojKataloga(Integer brojKataloga) {
        this.brojKataloga = brojKataloga;
    }

    public String getImeKataloga() {
        return imeKataloga;
    }

    public void setImeKataloga(String imeKataloga) {
        this.imeKataloga = imeKataloga;
    }

    public Dobavljac getPIBDobavljaca() {
        return pIBDobavljaca;
    }

    public void setPIBDobavljaca(Dobavljac pIBDobavljaca) {
        this.pIBDobavljaca = pIBDobavljaca;
    }

    public Zaposleni getJMBGZaposlenog() {
        return jMBGZaposlenog;
    }

    public void setJMBGZaposlenog(Zaposleni jMBGZaposlenog) {
        this.jMBGZaposlenog = jMBGZaposlenog;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (brojKataloga != null ? brojKataloga.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ZahtevZaKatalog)) {
            return false;
        }
        ZahtevZaKatalog other = (ZahtevZaKatalog) object;
        if ((this.brojKataloga == null && other.brojKataloga != null) || (this.brojKataloga != null && !this.brojKataloga.equals(other.brojKataloga))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.domain.ZahtevZaKatalog[ brojKataloga=" + brojKataloga + " ]";
    }
    
    
 public void copy(ZahtevZaKatalog zzk) {
        imeKataloga = zzk.imeKataloga;
        pIBDobavljaca = zzk.pIBDobavljaca;
        jMBGZaposlenog = zzk.jMBGZaposlenog;
    }
    
}
