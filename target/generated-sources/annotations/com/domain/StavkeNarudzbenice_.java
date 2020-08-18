package com.domain;

import com.domain.Narudzbenica;
import com.domain.Proizvod;
import com.domain.StavkeNarudzbenicePK;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-08-18T18:36:54")
@StaticMetamodel(StavkeNarudzbenice.class)
public class StavkeNarudzbenice_ { 

    public static volatile SingularAttribute<StavkeNarudzbenice, String> opisStavke;
    public static volatile SingularAttribute<StavkeNarudzbenice, StavkeNarudzbenicePK> stavkeNarudzbenicePK;
    public static volatile SingularAttribute<StavkeNarudzbenice, String> kolicina;
    public static volatile SingularAttribute<StavkeNarudzbenice, Narudzbenica> narudzbenica;
    public static volatile SingularAttribute<StavkeNarudzbenice, Proizvod> sifraProizvoda;

}