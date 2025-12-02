package Backend_Proyecto_Desarrollo_app_moviles.Repository;

import Backend_Proyecto_Desarrollo_app_moviles.Model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Integer> {
    Optional<Libro> findById(Integer id);
}
