package com.domain;

import com.domain.Dobavljac;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-08-18T18:36:54")
@StaticMetamodel(Grad.class)
public class Grad_ { 

    public static volatile CollectionAttribute<Grad, Dobavljac> dobavljacCollection;
    public static volatile SingularAttribute<Grad, Integer> postanskiBroj;
    public static volatile SingularAttribute<Grad, String> nazivGrada;

}