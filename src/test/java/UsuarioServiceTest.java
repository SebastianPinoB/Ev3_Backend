import Backend_Proyecto_Desarrollo_app_moviles.Model.Usuario;
import Backend_Proyecto_Desarrollo_app_moviles.Repository.UsuarioRepository;
import Backend_Proyecto_Desarrollo_app_moviles.Service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

// Mockito
@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @Mock // Simula un repositorio (BD)
    private UsuarioRepository usuarioRepository;

    @InjectMocks // Crea la instancia del Service e inyecta el mock anterior
    private UsuarioService usuarioService;

    // Usuario de prueba
    private Usuario usuarioPrueba;

    @BeforeEach
    void setUp() {
        // Inicialización de datos de prueba
        usuarioPrueba = new Usuario();
        usuarioPrueba.setId(1);
        usuarioPrueba.setNombreCompleto("Juan Test");
        usuarioPrueba.setCorreo("juan@duoc.cl");
        usuarioPrueba.setContrasena("clave123");
        usuarioPrueba.setTelefono("987654321");

        // Inicialización de generosFavoritos
        Set<String> generos = new HashSet<>();
        generos.add("Fantasia");
        generos.add("Terror");
        usuarioPrueba.setGenerosFavoritos(generos);
        usuarioPrueba.setFotoPerfil("foto.jpg");
    }

    @Test   // Deberia guardar y retornar usuarios con generos
    void crearUsuario() {

        // Cuando el repositorio reciba cualquier objeto Usuario, debe devolver el usuarioPrueba (con ID)
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuarioPrueba);

        // ACT (Ejecutar)
        Usuario resultado = usuarioService.crearUsuario(usuarioPrueba);

        // ASSERT (Verificar)
        assertNotNull(resultado);
        assertEquals(1, resultado.getId(), "El ID debe ser 1");
        assertEquals("juan@duoc.cl", resultado.getCorreo());

        // Verificación específica para tu nuevo campo:
        assertFalse(resultado.getGenerosFavoritos().isEmpty(), "El conjunto de géneros no debe estar vacío");
        assertTrue(resultado.getGenerosFavoritos().contains("Terror"));

        // Verifica que el repositorio fue llamado una sola vez
        verify(usuarioRepository, times(1)).save(usuarioPrueba);
    }


}
