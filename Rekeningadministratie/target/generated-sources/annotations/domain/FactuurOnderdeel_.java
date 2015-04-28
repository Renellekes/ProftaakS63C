package domain;

import domain.Kilometertarief;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-04-28T17:34:53")
@StaticMetamodel(FactuurOnderdeel.class)
public class FactuurOnderdeel_ { 

    public static volatile SingularAttribute<FactuurOnderdeel, Double> bedrag;
    public static volatile SingularAttribute<FactuurOnderdeel, Integer> FactuurOnderdeelID;
    public static volatile SingularAttribute<FactuurOnderdeel, Long> aantalKilometers;
    public static volatile SingularAttribute<FactuurOnderdeel, Kilometertarief> kilometertarief;
    public static volatile SingularAttribute<FactuurOnderdeel, Integer> CartrakerID;
    public static volatile SingularAttribute<FactuurOnderdeel, Date> eindTijd;
    public static volatile SingularAttribute<FactuurOnderdeel, String> maand;
    public static volatile SingularAttribute<FactuurOnderdeel, Date> beginTijd;

}