/** Enumeración que contiene precio de los productos y nombres correspondientemente */
public enum Enumeracion {
    COCA_COLA(1000, "CocaCola"),
    SPRITE(900, "Sprite"),
    FANTA(900, "Fanta"),
    SNICKERS(500, "Snickers"),
    SUPER8(300, "Super8");

    private final int precio;
    private final String nombre;

    Enumeracion(int precio, String nombre) {
        this.precio = precio;
        this.nombre = nombre;
    }
    public int getPrecio() { return precio; }
    public String getNombre() { return nombre; }
}