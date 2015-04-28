package domain;

import domain.Cartracker;
import java.util.List;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-04-22T09:27:59")
@StaticMetamodel(Factuur.class)
public class Factuur_ { 

    public static volatile ListAttribute<Factuur, List> factuuronderdelen;
    public static volatile SingularAttribute<Factuur, Double> totaalBedrag;
    public static volatile SingularAttribute<Factuur, String> betaalStatus;
    public static volatile SingularAttribute<Factuur, Cartracker> cartraker;
    public static volatile SingularAttribute<Factuur, Integer> nummer;
    public static volatile SingularAttribute<Factuur, String> maand;

}