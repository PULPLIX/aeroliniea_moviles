package logic;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import logic.Ciudad;
import logic.Horario;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-03-24T16:18:01")
@StaticMetamodel(Ruta.class)
public class Ruta_ { 

    public static volatile SingularAttribute<Ruta, Double> precio;
    public static volatile SingularAttribute<Ruta, Double> porcentajeDescuento;
    public static volatile SingularAttribute<Ruta, Ciudad> ciudadOrigen;
    public static volatile SingularAttribute<Ruta, Integer> id;
    public static volatile SingularAttribute<Ruta, Ciudad> ciudadDestino;
    public static volatile SingularAttribute<Ruta, Horario> horarioId;

}