package Productos;
/** Clase abstracta que muestra un producto genérica referencia de la máquina */
public abstract class Producto {
    public Producto() {
    }
    /** Método abstracto para asignar a cada producto decir qué es
     * @return El nombre o sabor de producto
     */
    public abstract String consumir();
}