package domain;

import domain.Auto;
import domain.Factuur;
import java.util.List;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-04-22T09:27:59")
@StaticMetamodel(Cartracker.class)
public class Cartracker_ { 

    public static volatile SingularAttribute<Cartracker, Auto> auto;
    public static volatile SingularAttribute<Cartracker, Boolean> website;
    public static volatile SingularAttribute<Cartracker, List> fileInfo;
    public static volatile SingularAttribute<Cartracker, Integer> id;
    public static volatile ListAttribute<Cartracker, Factuur> facturen;

}