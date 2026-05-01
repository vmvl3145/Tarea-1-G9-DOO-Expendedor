/** Nota para creación de main
 * - Atributos: Depósitos para cada producto y un depósito para el vuelto (Moneda)
 * - Constructor: Recibe un int (cantidad inicial de stock para todos los productos)
 * - comprarProducto: lanza las 3 excepciones. Maneja el pago, verifica stock y calcula vuelto
 * - El vuelto debe ser de monedas de $100 creadas al momento de la compra
 */
import Moneda.*;
import Productos.*;
import Productos.Bebidas.*;
import Productos.Dulce.*;
import Excepciones.*;

/** Clase para manejo y gestión de depósitos junto almacenamiento de máquina Expendedora */
public class Expendedor {
    private Deposito<Producto> cocacola;
    private Deposito<Producto> sprite;
    private Deposito<Producto> fanta;
    private Deposito<Producto> snickers;
    private Deposito<Producto> super8;
    /** Depósito para monedas que luego se retornan en el vuelto*/
    private Deposita<Moneda> montoVuelto;

    /** Constructor para inicializar depósitos y stock de la máquina
     * @param n Cantidad de productos de cada tipo que tendrá la máquina en el comienzo
     */
    public Expendedor(int n) {
        /** Inicio de depósitos genéricos */
        this.cocacola = new Deposito<>();
        this.sprite = new Deposito<>();
        this.fanta = new Deposito<>();
        this.snickers = new Deposito<>();
        this.super8 = new Deposito<>();
        this.montoVuelto = new Deposito<>();

        /** Llenado de máquina con n productos por cada clase */
        for (int = 0; i < n; i++) {
            cocacola.add(new Cocacola());
            sprite.add(new Cocacola());
            fanta.add(new Cocacola());
            snickers.add(new Cocacola());
            super8.add(new Cocacola());
        }
    }
    // comprarProducto, generarVuelto, getVuelto, bla bla bla
}