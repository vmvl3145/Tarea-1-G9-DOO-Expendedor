package Moneda;
/**
 * - clase con abstract que implementa Comparable<Moneda>.
 * - Método getValor() debe ser abstract
 * - compareTo(Moneda m): comparar por valor
 * - toString(): debe retornar el valor y el número de serie (usar this.hashCode()) para id de la moneda
 */
public abstract class Moneda implements Comparable<Moneda> {}