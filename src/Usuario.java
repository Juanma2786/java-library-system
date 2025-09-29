public class Usuario {
    private String nombre;
    private int id;

    public Usuario(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return id + ": " + nombre;
    }
}
