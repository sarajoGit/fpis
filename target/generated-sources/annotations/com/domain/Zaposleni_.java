package com.domain;

import com.domain.Narudzbenica;
import com.domain.ZahtevZaKatalog;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-01-12T13:25:52")
@StaticMetamodel(Zaposleni.class)
public class Zaposleni_ { 

    public static volatile CollectionAttribute<Zaposleni, ZahtevZaKatalog> zahtevZaKatalogCollection;
    public static volatile SingularAttribute<Zaposleni, String> imePrezime;
    public static volatile SingularAttribute<Zaposleni, Integer> jMBGZaposlenog;
    public static volatile CollectionAttribute<Zaposleni, Narudzbenica> narudzbenicaCollection;

}