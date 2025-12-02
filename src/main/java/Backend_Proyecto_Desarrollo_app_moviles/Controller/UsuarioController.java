package Backend_Proyecto_Desarrollo_app_moviles.Controller;

import Backend_Proyecto_Desarrollo_app_moviles.Model.Usuario;
import Backend_Proyecto_Desarrollo_app_moviles.Service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;

    // Constructor
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<Usuario> obtenerTodos() {
        return usuarioService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerPorId(@PathVariable int id) {
        try{
            Usuario usuario = usuarioService.obtenerPorId(id);
            // Retorna el cuerpo de la encomienda
            return ResponseEntity.ok(usuario);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> crearUsuario(@RequestBody Usuario usuario) {
        Usuario nuevoUsuario = usuarioService.crearUsuario(usuario);
        return ResponseEntity.status(201).body(nuevoUsuario);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credenciales) {

        // Obtenemos los valores del Map que Spring mapea desde el JSON
        String correo = credenciales.get("correo");
        String contrasena = credenciales.get("contrasena");

        Usuario usuarioAutenticado = usuarioService.autenticar(correo, contrasena);

        if (usuarioAutenticado != null) {
            return ResponseEntity.ok(usuarioAutenticado);
        } else {
            return ResponseEntity.status(401).body("Correo o clave inválida");
        }
    }

    @PutMapping("/{id}") // <--- RUTA PUT AÑADIENDO EL ID
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable int id, @RequestBody Usuario usuario) {
        try {
            // Lógica interna (que no causa el 405)
            Usuario usuarioActualizado = usuarioService.actualizarUsuario(id, usuario);
            return ResponseEntity.ok(usuarioActualizado);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable int id) {
        try {
            usuarioService.eliminarUsuario(id);
            // 204 No Content - Indica que la acción fue exitosa y no hay contenido para retornar.
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            // Se lanza si el usuario con el ID especificado no existe
            return ResponseEntity.notFound().build();
        }
    }
}
