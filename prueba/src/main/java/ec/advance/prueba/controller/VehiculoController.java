package ec.advance.prueba.controller;

import ec.advance.prueba.model.Vehiculo;
import ec.advance.prueba.repo.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Calendar;

@CrossOrigin
@RestController
@RequestMapping("/vehiculo")
public class VehiculoController {

    @Autowired
    VehiculoRepository vehiculoRepository;

    @GetMapping("")
    List<Vehiculo> index() {
        return vehiculoRepository.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    Vehiculo create(@RequestBody Vehiculo vehiculo) {
        return vehiculoRepository.save(vehiculo);
    }

    @PutMapping("{id}")
    Vehiculo update(@PathVariable String id, @RequestBody Vehiculo vehiculo) {
        Vehiculo vehiculoFromDb = vehiculoRepository.findById(id)
                .orElseThrow(RuntimeException::new);

        vehiculoFromDb.setChasis(vehiculo.getChasis());
        vehiculoFromDb.setColor(vehiculo.getColor());
        vehiculoFromDb.setMarca(vehiculo.getMarca());
        vehiculoFromDb.setModelo(vehiculo.getModelo());
        vehiculoFromDb.setPlaca(vehiculo.getPlaca());

        return vehiculoRepository.save(vehiculoFromDb);

    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    void delete(@PathVariable String id) {
        Vehiculo vehiculo = vehiculoRepository.findById(id)
                .orElseThrow(RuntimeException::new);

        vehiculoRepository.delete(vehiculo);
    }

    @PostMapping ("")
    boolean verificarPlaca(@PathVariable String placa, @PathVariable Date fecha) {
        Calendar c = Calendar.getInstance();
        c.setTime(fecha);
        int dia = c.get(Calendar.DAY_OF_WEEK);

        String ultimoDigito = placa.substring(placa.length() - 1);

        if (dia.equals(1) && (ultimoDigito == '1' || ultimoDigito == '2')) {
            return true;
        } else if (dia.equals(2) && (ultimoDigito == '3' || ultimoDigito == '4')) {
            return true;
        } else if (dia.equals(3) && (ultimoDigito == '5' || ultimoDigito == '6')) {
            return true;
        } else if (dia.equals(4) && (ultimoDigito == '7' || ultimoDigito == '8')) {
            return true;
        } else if (dia.equals(5) && (ultimoDigito == '9' || ultimoDigito == '0')) {
            return true;
        } else if (dia.equals(6) || dia.equals(7)) {
            return true;
        }
        return false;
    }

    @PostMapping ("")
    boolean verificarFecha(@PathVariable Date fecha) {
        Calendar calendar = Calendar.getInstance();
        Date dateObj = calendar.getTime();
        
        if (fecha.after(dateObj)) {
            return true;
        }
        return false;
    }
}
