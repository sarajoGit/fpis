/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.domain;

import com.db.DBBroker;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import pomocna.StavkaZaPrikaz;

/**
 *
 * @author Sara Jovanovic
 */
@Entity
@Table(name = "narudzbenica")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Narudzbenica.findAll", query = "SELECT n FROM Narudzbenica n")
    , @NamedQuery(name = "Narudzbenica.findByBrojNarudzbenice", query = "SELECT n FROM Narudzbenica n WHERE n.brojNarudzbenice = :brojNarudzbenice")
    , @NamedQuery(name = "Narudzbenica.findByUkupnoNaruceno", query = "SELECT n FROM Narudzbenica n WHERE n.ukupnoNaruceno = :ukupnoNaruceno")})
public class Narudzbenica implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "brojNarudzbenice")
    private Integer brojNarudzbenice;
    @Size(max = 200)
    @Column(name = "ukupnoNaruceno")
    private String ukupnoNaruceno;
    @JoinColumns({
        @JoinColumn(name = "rokIsporukeID", referencedColumnName = "rokIsporukeID")
        , @JoinColumn(name = "rokIsporukeID", referencedColumnName = "rokIsporukeID")})
    @ManyToOne
    private RokIsporuke rokIsporuke;
    @JoinColumns({
        @JoinColumn(name = "JMBGZaposlenog", referencedColumnName = "JMBGZaposlenog")
        , @JoinColumn(name = "JMBGZaposlenog", referencedColumnName = "JMBGZaposlenog")})
    @ManyToOne
    private Zaposleni zaposleni;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "narudzbenica")
    private Collection<StavkeNarudzbenice> stavkeNarudzbeniceCollection;

    public Narudzbenica() {
        stavkeNarudzbeniceCollection = new ArrayList<StavkeNarudzbenice>();
    }

    public Narudzbenica(Integer brojNarudzbenice) {
        this.brojNarudzbenice = brojNarudzbenice;
    }

    public Integer getBrojNarudzbenice() {
        return brojNarudzbenice;
    }

    public void setBrojNarudzbenice(Integer brojNarudzbenice) {
        this.brojNarudzbenice = brojNarudzbenice;
    }

    public String getUkupnoNaruceno() {
        return ukupnoNaruceno;
    }

    public void setUkupnoNaruceno(String ukupnoNaruceno) {
        this.ukupnoNaruceno = ukupnoNaruceno;
    }

    public RokIsporuke getRokIsporuke() {
        return rokIsporuke;
    }

    public void setRokIsporuke(RokIsporuke rokIsporuke) {
        this.rokIsporuke = rokIsporuke;
    }

    public Zaposleni getZaposleni() {
        return zaposleni;
    }

    public void setZaposleni(Zaposleni zaposleni) {
        this.zaposleni = zaposleni;
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
        hash += (brojNarudzbenice != null ? brojNarudzbenice.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Narudzbenica)) {
            return false;
        }
        Narudzbenica other = (Narudzbenica) object;
        if ((this.brojNarudzbenice == null && other.brojNarudzbenice != null) || (this.brojNarudzbenice != null && !this.brojNarudzbenice.equals(other.brojNarudzbenice))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.domain.Narudzbenica[ brojNarudzbenice=" + brojNarudzbenice + " ]";
    }
    
    public void copy(Narudzbenica n) {
        ukupnoNaruceno = n.ukupnoNaruceno;
        rokIsporuke = n.rokIsporuke;
        zaposleni =n.zaposleni;

    }
    
    public void dodajStavku(StavkaZaPrikaz stavka, int br) {
        
        int rbStavke = stavka.getRbStavke();
        Narudzbenica brojNarudzbenice = stavka.getBrojNarudzbenice();
        String opisStavke = stavka.getOpisStavke();
        Proizvod sifraProizvoda = stavka.getSifraProizvoda();
        String kolicina = stavka.getKolicina();
        Status status = stavka.getStatus();
        
        StavkeNarudzbenice sn = new StavkeNarudzbenice();
        StavkeNarudzbenicePK pk = new StavkeNarudzbenicePK();
        if(br!=0){
             pk = new StavkeNarudzbenicePK(rbStavke, br);
        } else {
             pk = new StavkeNarudzbenicePK(rbStavke, 0);
        }
        
        sn.setStavkeNarudzbenicePK(pk);
        sn.setNarudzbenica(brojNarudzbenice);
        sn.setKolicina(kolicina);
        sn.setOpisStavke(opisStavke);
        sn.setSifraProizvoda(sifraProizvoda);
        //sn.setStatus(status);
          
        stavkeNarudzbeniceCollection.add(sn);
    }
    
    public boolean azurirajStavku(StavkaZaPrikaz stavka, int br) {
        
        
        int rbStavke = stavka.getRbStavke();
        Narudzbenica brojNarudzbenice = stavka.getBrojNarudzbenice();
        String opisStavke = stavka.getOpisStavke();
        Proizvod sifraProizvoda = stavka.getSifraProizvoda();
        String kolicina = stavka.getKolicina();
        Status status = stavka.getStatus();

        
        StavkeNarudzbenice sn = new StavkeNarudzbenice();
        StavkeNarudzbenicePK pk = new StavkeNarudzbenicePK(rbStavke, br);

        sn.setStavkeNarudzbenicePK(pk);
        sn.setNarudzbenica(brojNarudzbenice);
        sn.setKolicina(kolicina);
        sn.setOpisStavke(opisStavke);
        sn.setSifraProizvoda(sifraProizvoda);
        sn.setStatus(status);
        
        for(StavkeNarudzbenice s: stavkeNarudzbeniceCollection) {
            
            System.out.println("st "+s.getStatus());
            if( s.getStavkeNarudzbenicePK().equals(sn.getStavkeNarudzbenicePK()) && s.getStavkeNarudzbenicePK().getBrojNarudzbenice()!=0 && s.getStavkeNarudzbenicePK().getRbStavke()!=0) {
                System.out.println("Azuriranje stavke iz baze");
                s.copy(sn);
                s.setSifraProizvoda((Proizvod) DBBroker.getInstance().get(Proizvod.class, stavka.getSifraProizvoda().getSifraProizvoda()));
                s.setStatus(Status.UPDATE);
                System.out.println("Podaci za azuriranu stavku: "+s.getOpisStavke()+s.getKolicina()+s.getStatus());
                return true;
            }
            
            if( s.getStatus().equals(Status.UPDATENEW)) {
                System.out.println("Azuriranje nove stavke");
                s.copy(sn);
                s.setSifraProizvoda((Proizvod) DBBroker.getInstance().get(Proizvod.class, stavka.getSifraProizvoda().getSifraProizvoda()));
                s.setStatus(Status.UPDATE);
                System.out.println("Podaci za azuriranu stavku: "+s.getOpisStavke()+s.getKolicina()+s.getStatus());
                return true;
            }
            

        }
        return false;
    }


    
}
