package Backend_Proyecto_Desarrollo_app_moviles.Model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String nombreCompleto;
    @Column(nullable = false)
    private String correo;
    @Column(nullable = false)
    private String contrasena;
    private String telefono;

    // --- MANEJO DE GENEROS LITERARIOS ---
    // Crea una tabla separada (ej: usuario_generos) para almacenar los Strings.
    @ElementCollection(fetch = FetchType.LAZY)

    // Le dice a JPA cómo nombrar y cómo unir la nueva tabla que contendrá los valores.
    // Crea la tabla generos_literarios y define la columna que une esta tabla con la tabla principal
    @CollectionTable(name = "usuario_generos", joinColumns = @JoinColumn(name = "usuario_id"))

    // Define el nombre de la columna donde se almacenará el valor del género dentro de la tabla
    @Column(name = "genero")
    private Set<String> generosFavoritos = new HashSet<>();

    private String fotoPerfil;

    public Usuario(Object o, String mail, String pass, String image) {
    }
    public Usuario(String fotoPerfil, Set<String> generosFavoritos, String telefono, String contrasena, String correo, String nombreCompleto) {
        this.fotoPerfil = fotoPerfil;
        this.generosFavoritos = generosFavoritos;
        this.telefono = telefono;
        this.contrasena = contrasena;
        this.correo = correo;
        this.nombreCompleto = nombreCompleto;
    }

    public Usuario() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Set<String> getGenerosFavoritos() {
        return generosFavoritos;
    }

    public void setGenerosFavoritos(Set<String> generosFavoritos) {
        this.generosFavoritos = generosFavoritos;
    }

    public String getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(String fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }
}
