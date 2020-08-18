package com.domain;

import com.domain.Narudzbenica;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-08-18T18:36:54")
@StaticMetamodel(RokIsporuke.class)
public class RokIsporuke_ { 

    public static volatile SingularAttribute<RokIsporuke, String> brojDana;
    public static volatile SingularAttribute<RokIsporuke, Integer> rokIsporukeID;
    public static volatile CollectionAttribute<RokIsporuke, Narudzbenica> narudzbenicaCollection;

}