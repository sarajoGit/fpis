/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.domain;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Sara Jovanovic
 */
@Embeddable
public class StavkeNarudzbenicePK implements Serializable {

    @Basic(optional = false)
    @Column(name = "rbStavke")
    private int rbStavke;
    @Basic(optional = false)
    @NotNull
    @Column(name = "brojNarudzbenice")
    private int brojNarudzbenice;

    public StavkeNarudzbenicePK() {
    }

    public StavkeNarudzbenicePK(int rbStavke, int brojNarudzbenice) {
        this.rbStavke = rbStavke;
        this.brojNarudzbenice = brojNarudzbenice;
    }

    public int getRbStavke() {
        return rbStavke;
    }

    public void setRbStavke(int rbStavke) {
        this.rbStavke = rbStavke;
    }

    public int getBrojNarudzbenice() {
        return brojNarudzbenice;
    }

    public void setBrojNarudzbenice(int brojNarudzbenice) {
        this.brojNarudzbenice = brojNarudzbenice;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) rbStavke;
        hash += (int) brojNarudzbenice;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StavkeNarudzbenicePK)) {
            return false;
        }
        StavkeNarudzbenicePK other = (StavkeNarudzbenicePK) object;
        if (this.rbStavke != other.rbStavke) {
            return false;
        }
        if (this.brojNarudzbenice != other.brojNarudzbenice) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.domain.StavkeNarudzbenicePK[ rbStavke=" + rbStavke + ", brojNarudzbenice=" + brojNarudzbenice + " ]";
    }
    
}
