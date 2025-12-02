package Backend_Proyecto_Desarrollo_app_moviles.Repository;

import Backend_Proyecto_Desarrollo_app_moviles.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
        Optional<Usuario> findById(Integer id);
        Optional<Usuario> findByCorreo(String correo);

}
