package domain;

import domain.Eigenaar;
import java.io.File;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-04-22T09:27:59")
@StaticMetamodel(Auto.class)
public class Auto_ { 

    public static volatile SingularAttribute<Auto, String> voertuig;
    public static volatile SingularAttribute<Auto, String> kenteken;
    public static volatile SingularAttribute<Auto, Boolean> gestolen;
    public static volatile SingularAttribute<Auto, File> fileInfo;
    public static volatile SingularAttribute<Auto, Integer> id;
    public static volatile SingularAttribute<Auto, String> eersteKleur;
    public static volatile SingularAttribute<Auto, Eigenaar> eigenaar;
    public static volatile SingularAttribute<Auto, Integer> zitplaatsen;

}