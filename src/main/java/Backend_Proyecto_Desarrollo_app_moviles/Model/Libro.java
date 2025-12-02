package Backend_Proyecto_Desarrollo_app_moviles.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "Libro")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String titulo;
    @Column(nullable = false)
    private String autor;
    @Column(nullable = false)
    private String idioma;
    @Column(nullable = false)
    private int paginas;
    @Column(nullable = false, length = 50)
    private String categoria;

    public Libro() {
    }

    public Libro(int id, String titulo, String autor, String idioma, int paginas, String categoria) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.idioma = idioma;
        this.paginas = paginas;
        this.categoria = categoria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPaginas() {
        return paginas;
    }

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCategoria() { 
        return categoria; 
    }
    
    public void setCategoria(String categoria) { 
        this.categoria = categoria; 
    }
}
