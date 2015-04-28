package domain;

import domain.Auto;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-04-28T14:22:54")
@StaticMetamodel(Eigenaar.class)
public class Eigenaar_ { 

    public static volatile SingularAttribute<Eigenaar, Integer> id;
    public static volatile CollectionAttribute<Eigenaar, Auto> autos;
    public static volatile SingularAttribute<Eigenaar, String> adres;
    public static volatile SingularAttribute<Eigenaar, Boolean> website;
    public static volatile SingularAttribute<Eigenaar, String> woonplaats;
    public static volatile SingularAttribute<Eigenaar, String> naam;

}