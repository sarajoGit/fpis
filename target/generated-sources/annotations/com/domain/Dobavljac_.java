package com.domain;

import com.domain.Grad;
import com.domain.ZahtevZaKatalog;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-08-18T18:36:54")
@StaticMetamodel(Dobavljac.class)
public class Dobavljac_ { 

    public static volatile SingularAttribute<Dobavljac, Integer> pIBDobavljaca;
    public static volatile SingularAttribute<Dobavljac, String> telefonDobavljaca;
    public static volatile CollectionAttribute<Dobavljac, ZahtevZaKatalog> zahtevZaKatalogCollection;
    public static volatile SingularAttribute<Dobavljac, String> emailDobavljaca;
    public static volatile SingularAttribute<Dobavljac, Grad> grad;
    public static volatile SingularAttribute<Dobavljac, String> nazivDobavljaca;

}