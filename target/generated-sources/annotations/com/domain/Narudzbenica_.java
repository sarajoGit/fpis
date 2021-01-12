package com.domain;

import com.domain.RokIsporuke;
import com.domain.StavkeNarudzbenice;
import com.domain.Zaposleni;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-01-12T13:25:52")
@StaticMetamodel(Narudzbenica.class)
public class Narudzbenica_ { 

    public static volatile SingularAttribute<Narudzbenica, Integer> brojNarudzbenice;
    public static volatile SingularAttribute<Narudzbenica, String> ukupnoNaruceno;
    public static volatile SingularAttribute<Narudzbenica, Zaposleni> zaposleni;
    public static volatile SingularAttribute<Narudzbenica, RokIsporuke> rokIsporuke;
    public static volatile CollectionAttribute<Narudzbenica, StavkeNarudzbenice> stavkeNarudzbeniceCollection;

}