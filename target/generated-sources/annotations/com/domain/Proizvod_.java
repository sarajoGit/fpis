package com.domain;

import com.domain.StavkeNarudzbenice;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-01-12T13:25:52")
@StaticMetamodel(Proizvod.class)
public class Proizvod_ { 

    public static volatile SingularAttribute<Proizvod, String> nazivProizvoda;
    public static volatile CollectionAttribute<Proizvod, StavkeNarudzbenice> stavkeNarudzbeniceCollection;
    public static volatile SingularAttribute<Proizvod, Long> cenaProizvoda;
    public static volatile SingularAttribute<Proizvod, String> sifraProizvoda;

}