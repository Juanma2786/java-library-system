import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private List<Libro> libros;
    private List<Usuario> usuarios;
    private final String librosFile = "data/libros.csv";
    private final String usuariosFile = "data/usuarios.csv";

    public Biblioteca() {
        libros = new ArrayList<>();
        usuarios = new ArrayList<>();
        cargarLibros();
        cargarUsuarios();
    }

    // ---------------- Libros ----------------
    public void agregarLibro(String titulo, String autor) {
        libros.add(new Libro(titulo, autor));
        System.out.println("✅ Libro agregado: " + titulo);
    }

    public void listarLibros() {
        if(libros.isEmpty()) {
            System.out.println("📭 No hay libros registrados.");
            return;
        }
        for(int i=0; i<libros.size(); i++) {
            System.out.println((i+1) + ". " + libros.get(i));
        }
    }

    public void prestarLibro(int libroIndex) {
        if(libroIndex >= 0 && libroIndex < libros.size()) {
            Libro l = libros.get(libroIndex);
            if(!l.isPrestado()) {
                l.setPrestado(true);
                System.out.println("✅ Libro prestado: " + l.getTitulo());
            } else {
                System.out.println("❌ El libro ya está prestado.");
            }
        } else {
            System.out.println("❌ Índice inválido.");
        }
    }

    public void devolverLibro(int libroIndex) {
        if(libroIndex >= 0 && libroIndex < libros.size()) {
            Libro l = libros.get(libroIndex);
            if(l.isPrestado()) {
                l.setPrestado(false);
                System.out.println("✅ Libro devuelto: " + l.getTitulo());
            } else {
                System.out.println("❌ El libro no estaba prestado.");
            }
        } else {
            System.out.println("❌ Índice inválido.");
        }
    }

    // ---------------- Usuarios ----------------
    public void agregarUsuario(int id, String nombre) {
        usuarios.add(new Usuario(id, nombre));
        System.out.println("✅ Usuario agregado: " + nombre);
    }

    public void listarUsuarios() {
        if(usuarios.isEmpty()) {
            System.out.println("📭 No hay usuarios registrados.");
            return;
        }
        for(Usuario u : usuarios) {
            System.out.println(u);
        }
    }

    // ---------------- Persistencia ----------------
    public void guardarLibros() {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(librosFile))) {
            for(Libro l : libros) {
                bw.write(l.getTitulo() + ";" + l.getAutor() + ";" + l.isPrestado());
                bw.newLine();
            }
        } catch(IOException e) {
            System.out.println("❌ Error al guardar libros.");
        }
    }

    public void guardarUsuarios() {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(usuariosFile))) {
            for(Usuario u : usuarios) {
                bw.write(u.getId() + ";" + u.getNombre());
                bw.newLine();
            }
        } catch(IOException e) {
            System.out.println("❌ Error al guardar usuarios.");
        }
    }

    public void cargarLibros() {
        File file = new File(librosFile);
        if(!file.exists()) return;
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                Libro l = new Libro(parts[0], parts[1]);
                l.setPrestado(Boolean.parseBoolean(parts[2]));
                libros.add(l);
            }
        } catch(IOException e) {
            System.out.println("❌ Error al cargar libros.");
        }
    }

    public void cargarUsuarios() {
        File file = new File(usuariosFile);
        if(!file.exists()) return;
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                Usuario u = new Usuario(Integer.parseInt(parts[0]), parts[1]);
                usuarios.add(u);
            }
        } catch(IOException e) {
            System.out.println("❌ Error al cargar usuarios.");
        }
    }

    public void guardarTodo() {
        guardarLibros();
        guardarUsuarios();
    }
}