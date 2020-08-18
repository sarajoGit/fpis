/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pomocna;

import com.domain.Narudzbenica;
import com.domain.Proizvod;
import com.domain.Status;

/**
 *
 * @author Sara Jovanovic
 */
public class StavkaZaPrikaz {
    
    private int rbStavke;
    private Narudzbenica brojNarudzbenice;
    private String opisStavke;
    private String kolicina;
    private Proizvod sifraProizvoda;
    private Status status;

    public StavkaZaPrikaz() {
    }

    public StavkaZaPrikaz(int rbStavke, Narudzbenica brojNarudzbenice) {
        this.rbStavke = rbStavke;
        this.brojNarudzbenice = brojNarudzbenice;
    }
    
    

    public int getRbStavke() {
        return rbStavke;
    }

    public void setRbStavke(int rbStavke) {
        this.rbStavke = rbStavke;
    }

    public Narudzbenica getBrojNarudzbenice() {
        return brojNarudzbenice;
    }

    public void setBrojNarudzbenice(Narudzbenica brojNarudzbenice) {
        this.brojNarudzbenice = brojNarudzbenice;
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
    public String toString() {
        return "Rb stavke "+rbStavke+ " broj narudzbenice "+brojNarudzbenice+" opis stavke "+opisStavke+" kolicina "+kolicina+" proizvod "+sifraProizvoda;
    }
    
    
    
     
}
