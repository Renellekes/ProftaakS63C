package domain;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-06-01T09:32:49")
@StaticMetamodel(CartrackerMovement.class)
public class CartrackerMovement_ { 

    public static volatile SingularAttribute<CartrackerMovement, Integer> position;
    public static volatile SingularAttribute<CartrackerMovement, String> cartrackerId;
    public static volatile SingularAttribute<CartrackerMovement, Date> dateOfMovement;
    public static volatile SingularAttribute<CartrackerMovement, Integer> speed;
    public static volatile SingularAttribute<CartrackerMovement, Integer> movementId;

}