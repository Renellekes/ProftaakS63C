package domain;

import domain.Road;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-06-01T09:32:49")
@StaticMetamodel(Road.class)
public class Road_ { 

    public static volatile SingularAttribute<Road, Integer> id;
    public static volatile SingularAttribute<Road, Double> startX;
    public static volatile SingularAttribute<Road, Double> startY;
    public static volatile SingularAttribute<Road, Double> endX;
    public static volatile SingularAttribute<Road, Integer> maxSpeed;
    public static volatile SingularAttribute<Road, String> name;
    public static volatile SingularAttribute<Road, Integer> roadLength;
    public static volatile SingularAttribute<Road, Double> endY;
    public static volatile ListAttribute<Road, Road> connectedTo;

}