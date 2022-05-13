/**
 * 
 */
package ec.advance.prueba.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Walter Meza
 *
 */
@Data
@Document
public class Vehiculo {
    @Id
    private String id;
    private String placa;
    private String marca;
    private String modelo;
    private String chasis;
    private String color;


}
