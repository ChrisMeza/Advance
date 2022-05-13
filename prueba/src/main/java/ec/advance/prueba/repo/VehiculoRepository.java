package ec.advance.prueba.repo;

import ec.advance.prueba.model.Vehiculo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VehiculoRepository extends MongoRepository<Vehiculo, String> {

}
