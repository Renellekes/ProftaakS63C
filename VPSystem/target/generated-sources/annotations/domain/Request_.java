package domain;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-06-01T09:32:49")
@StaticMetamodel(Request.class)
public class Request_ { 

    public static volatile SingularAttribute<Request, Integer> id;
    public static volatile SingularAttribute<Request, Integer> cartrackerId;
    public static volatile SingularAttribute<Request, Date> dateOfRequest;
    public static volatile SingularAttribute<Request, String> requestedBy;

}