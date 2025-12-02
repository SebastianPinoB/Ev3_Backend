package Backend_Proyecto_Desarrollo_app_moviles.Service;

import Backend_Proyecto_Desarrollo_app_moviles.Model.Libro;
import Backend_Proyecto_Desarrollo_app_moviles.Repository.LibroRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class LibroService {
    private final LibroRepository libroRepository;

    public LibroService(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    public Libro crearLibro(Libro libro){
        return libroRepository.save(libro);
    }

    public List<Libro> obtenerTodos(){
        return libroRepository.findAll();
    }

    public Libro obtenerPorId(int id){
        return libroRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Libro no encontrado con ID: " + id));
    }

    public Libro actualizarLibro(int id, Libro libroNuevo){
        Optional<Libro> libroOptional = libroRepository.findById(id);

        if (libroOptional.isPresent()) {
            Libro libroExistente = libroOptional.get();

            // 1. Actualiza los campos
            libroExistente.setTitulo(libroNuevo.getTitulo());
            libroExistente.setAutor(libroNuevo.getAutor());
            libroExistente.setIdioma(libroNuevo.getIdioma());
            libroExistente.setPaginas(libroNuevo.getPaginas());
            libroExistente.setCategoria(libroNuevo.getCategoria());

            // 2. Guarda el objeto modificado
            return libroRepository.save(libroExistente);

        } else {
            // 3. Lanza la excepción que definimos más abajo.
            throw new ResourceNotFoundException("Libro con ID " + id + " no encontrado para actualizar.");
        }
    }

    public void eliminarLibro(int id){
        if (!libroRepository.existsById(id)){
            throw new NoSuchElementException("Usuario con ID " + id + " no encontrado.");
        }
        libroRepository.deleteById(id);
    }


    /**
     * Excepción personalizada para recursos no encontrados (código 404).
     * Se puede definir aquí mismo, aunque se recomienda un archivo separado.
     */
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public static class ResourceNotFoundException extends RuntimeException {

        // Se usa un ID de serialización estándar
        private static final long serialVersionUID = 1L;
        public ResourceNotFoundException(String mensaje) {
            super(mensaje);
        }
    }

}
