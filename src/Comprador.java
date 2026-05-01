/**
 * - Atributos: String (sabor consumido) e int (vuelto total retirado).
 * - Constructor: Recibe Moneda, Enumeracion y Expendedor. Ejecuta la compra y retira TODO el vuelto.
 * - No puede guardar la moneda ni el objeto Producto como propiedades.
 */
import Moneda.*;
import Excepciones.*;

public class Comprador {
    private String saborConsumido;
    private int vueltoTotal;

    public Comprador(Moneda moneda, int seleccion, Expendedor expendedor)
            throws PagoIncorrectoException, NoHayProductoException, PagoInsuficienteException {

        //ejecutar compra y consumir producto haciendo una cadena (no se guarda como atributo)
        this.saborConsumido = expendedor.comprarProducto(moneda, seleccion).consumir();

        //retirar todo el vuelto y sumar el total
        this.vueltoTotal = 0;
        Moneda monedaVuelto = expendedor.getVuelto();
        while (monedaVuelto != null) {
            this.vueltoTotal += monedaVuelto.getValor();
            monedaVuelto = expendedor.getVuelto();
        }
    }

    public String getSaborConsumido() { return saborConsumido; }
    public int getVueltoTotal() { return vueltoTotal; }
}