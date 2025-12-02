package Backend_Proyecto_Desarrollo_app_moviles.Controller;

import Backend_Proyecto_Desarrollo_app_moviles.Model.Libro;
import Backend_Proyecto_Desarrollo_app_moviles.Service.LibroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/libros")
public class LibroController {
    private final LibroService libroService;

    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    @GetMapping
    public List<Libro> obtenerTodos(){
        return libroService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Libro> obtenerPorId(@PathVariable int id){
        try{
            Libro libro = libroService.obtenerPorId(id);
            return ResponseEntity.ok(libro);
        }catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> crearLibro(@RequestBody Libro libro){
        Libro nuevoLibro = libroService.crearLibro(libro);
        return ResponseEntity.status(201).body(nuevoLibro);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Libro> actualizarLibro(@PathVariable int id, @RequestBody Libro libro){
        try {
            Libro libroActualizado = libroService.actualizarLibro(id, libro);
            return ResponseEntity.ok(libroActualizado);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarLibro(@PathVariable int id){
        try{
            libroService.eliminarLibro(id);
            return ResponseEntity.noContent().build();
        }catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }
    }

}
