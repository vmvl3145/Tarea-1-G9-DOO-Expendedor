/**
 * - Atributos: String (sabor consumido) e int (vuelto total retirado).
 * - Constructor: Recibe Moneda, int y Expendedor. Ejecuta la compra y retira TODO el vuelto.
 * - No puede guardar la moneda ni el objeto Producto como propiedades.
 */
import Moneda.*;
import Excepciones.*;

public class Comprador {
    private String saborConsumido;
    private int vueltoTotal;

    public Comprador(Moneda moneda, int seleccion, Expendedor expendedor)
            throws PagoIncorrectoException, NoHayProductoException, PagoInsuficienteException {

        this.saborConsumido = null;
        this.vueltoTotal = 0;

        try {
            //ejecutar compra y consumir producto haciendo una cadena (no se guarda como atributo)
            this.saborConsumido = expendedor.comprarProducto(moneda, seleccion).consumir();
        } catch (NoHayProductoException | PagoInsuficienteException e) {
            //si fallo, retirar la moneda devuelta y relanzar al main
            Moneda monedaVuelto = expendedor.getVuelto();
            while (monedaVuelto != null) {
                this.vueltoTotal += monedaVuelto.getValor();
                monedaVuelto = expendedor.getVuelto();
            }
            throw e;
        }

        //retirar todo el vuelto y sumar el total
        Moneda monedaVuelto = expendedor.getVuelto();
        while (monedaVuelto != null) {
            this.vueltoTotal += monedaVuelto.getValor();
            monedaVuelto = expendedor.getVuelto();
        }
    }

    public String getSaborConsumido() { return saborConsumido; }
    public int getVueltoTotal() { return vueltoTotal; }
}