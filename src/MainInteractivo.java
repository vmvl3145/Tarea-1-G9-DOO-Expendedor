import Excepciones.*;
import Moneda.*;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Clase Principal interactiva que simula el uso de una maquina expendedora
 * <p>
 *     Esta clase proporciona una interfaz de consola (CLI) donde el usuario
 *     puede seleccionar el tipo de moneda a ingresar, elegir un producto a comprar
 *     y visualizar el resultado de su transaccion, incluyendo vuelto o consumo.
 *     Ademas, maneja excepciones generadas por errores en la compra
 * </p>
 *
 * @author Joaquín Navarrete
 * @version 1.0
 */

public class MainInteractivo {

    /**
     *  Metodo principal que inicia el bucle de interaccion con el usuario
     *  <p>
     *      Gestiona la entrada por teclado mediante {@link Scanner},
     *      instanciando un expendedor con stock inicial e iterando
     *      hasta que el usuario decida salirse del programa.
     *
     *      En cada iteracion, captura y maneja de forma segura
     *      las excepciones del tipo {@link PagoIncorrectoException}
     *      {@link PagoInsuficienteException}, y
     *      {@link NoHayProductoException}.
     *  </p>
     *
     * @param args Argumentos de la linea de comandos (no se utilizan en esta aplicacion)
     */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Instancia un expendedor con 5 unidades en stock
        Expendedor expendedor = new Expendedor(5);

        // Creamos billetera
        ArrayList<Moneda> miBilletera = new ArrayList<>();
        miBilletera.add(new Moneda1000());
        miBilletera.add(new Moneda500());
        miBilletera.add(new Moneda500());
        miBilletera.add(new Moneda1000());

        boolean maquinaFuncionando = true;

        System.out.println("¡BIENVENIDO A LA MAQUINA EXPENDEDORA!");
        System.out.println("=========================================");

        while(maquinaFuncionando) {
            System.out.println("\nMENU PRINCIPAL");
            System.out.println("1. Comprar Producto.");
            System.out.println("2. Ver mi billetera");
            System.out.println("0. Salir.");

            System.out.println("\nElige una opcion: ");

            int opcionMenu = scanner.nextInt();

            if(opcionMenu == 0) {
                System.out.println("Muchas gracias por haber usado nuestros servicios");
                System.out.println("Apagando maquina...");
                maquinaFuncionando = false;
            } else if(opcionMenu == 2) {
                System.out.println("=====CONTENIDO DE TU BILLETERA=====");
                if(miBilletera.isEmpty()) {
                    System.out.println("Tu billetera se encuentra vacia :(");
                } else {
                    for(int i=0;i<miBilletera.size(); i++) {
                        System.out.println("Indice [" + i + "]\tMoneda de $" + miBilletera.get(i).getValor());
                    }
                }
            } else if(opcionMenu == 1) {
                 if(miBilletera.isEmpty()) {
                     System.out.println("No puedes comprar. Billetera vacia");
                     continue;
                 }

                 System.out.println("Elige con que moneda pagar:");
                 for(int i=0;i<miBilletera.size(); i++) {
                     System.out.println("Indice [" + i + "]\tMoneda de $" + miBilletera.get(i).getValor());
                 }
                 System.out.println("Ingresa el INDICE de la moneda a usar: ");

                 int opcionMoneda = scanner.nextInt();

                 if(opcionMoneda < 0 || opcionMoneda >= miBilletera.size()) {
                     System.out.println("Indice invalido.");
                     continue;
                 }

                 Moneda monedaInsertada = miBilletera.remove(opcionMoneda);

                 System.out.println("\n=====VITRINA=====");
                 System.out.println("1. Coca Cola. $1000");
                 System.out.println("2. Sprite. $900");
                 System.out.println("3. Fanta. $900");
                 System.out.println("4. Snickers. $600");
                 System.out.println("5. Super 8. $300");
                 System.out.println("======================");

                 System.out.println("Que deseas comprar?");

                 int eleccionProducto = scanner.nextInt();

                 if(eleccionProducto < 0 || eleccionProducto > 5) {
                     System.out.println("Producto Invalido");
                     continue;
                 }

                 System.out.println("\nQue moneda deseas utilizar?");
                 System.out.println("1. $100");
                 System.out.println("2. $500");
                 System.out.println("3. $1000");
                 System.out.println("4. Forzar maquina a comprar sin moneda");

                 int eleccionMoneda = scanner.nextInt();
                 Moneda monedaInsertada = null;

                 switch (eleccionMoneda) {
                     case 1: monedaInsertada = new Moneda100(); break;
                     case 2: monedaInsertada = new Moneda500(); break;
                     case 3: monedaInsertada = new Moneda1000(); break;
                     case 4: monedaInsertada = null; break;
                     default:
                         System.out.println("Opcion invalida. Usaras maquina sin moneda");
                         monedaInsertada = null;
                 }

                 System.out.println("\nProcesando compra...");

                 try {
                     Comprador comprador = new Comprador(monedaInsertada, eleccionProducto, expendedor);

                     System.out.println("Compra Exitosa!");
                     System.out.println("Consumiste: " + comprador.getSaborConsumido());

                     System.out.println("La maquina te devuelve: $" + comprador.getVueltoTotal());
                 } catch (PagoInsuficienteException | PagoIncorrectoException e) {
                     System.out.println("ERROR: " + e.getMessage());
                     System.out.println("La maquina te devuelve la moneda y la guardas en tu billetera...");
                     miBilletera.add(monedaInsertada);
                 } catch (NoHayProductoException e) {
                     System.out.println("ERROR: " + e.getMessage());
                 } catch (Exception e) {
                     System.out.println("ERROR CRITICO: " + e);
                 }
            }
        }
        scanner.close();
    }
}