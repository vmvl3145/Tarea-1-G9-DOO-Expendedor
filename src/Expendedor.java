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
    private Deposito<Moneda> montoVuelto;

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
        for (int i = 0; i < n; i++) {
            cocacola.add(new Cocacola());
            sprite.add(new Sprite());
            fanta.add(new Fanta());
            snickers.add(new Snickers());
            super8.add(new Super8());
        }
    }
 // get deposito
    private Deposito<Producto> getDeposito(Enumeracion seleccion) {
    switch (seleccion) {
        case COCA_COLA: return cocacola;
        case SPRITE:    return sprite;
        case FANTA:     return fanta;
        case SNICKERS:  return snickers;
        case SUPER8:    return super8;
        default: throw new IllegalArgumentException("Producto no reconocido.");
    }
}

// comprar producto. revisa excepciones, devuelve vuelto en monedas de 100 y retorna el producto.
public Producto comprarProducto(Moneda moneda, Enumeracion seleccion)
        throws PagoIncorrectoException, NoHayProductoException, PagoInsuficienteException {
    if (moneda == null) {
        throw new PagoIncorrectoException("No se ha pagado.");
    }
    if (moneda.getValor() < seleccion.getPrecio()) {
        throw new PagoInsuficienteException("Pago insuficiente para " + seleccion.getNombre());
    }
//tras chequear dinero, ponemos el producto en el array.
    Deposito<Producto> deposito = getDeposito(seleccion);

    Producto producto = deposito.get();
    if (producto == null) {
        throw new NoHayProductoException("No queda " + seleccion.getNombre());
    }
//confirmado que se compro, devolvemos vuelto.
    int vuelto = moneda.getValor() - seleccion.getPrecio();

    while (vuelto >= 100) {
        montoVuelto.add(new Moneda100());
        vuelto -= 100;
    }

    return producto;
}
//Vuelto
public Moneda getVuelto() {
    return montoVuelto.get();
}
}