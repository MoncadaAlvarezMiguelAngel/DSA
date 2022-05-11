package org.eda2.Practica_03;

public class Objeto {

    private String nombre;
    private double peso;
    private double valor;
    private double cantidad;
/**
 * Método constructor de la clase Objeto, que usamos para llevar los
 * objetos del interior de la mochila teniendo encuenta tanto su peso 
 * como su valor como su nombre.
 * @param nombre
 * @param peso
 * @param valor
 */
    public Objeto(String nombre, double peso, double valor) {
        this.nombre = nombre;
        this.peso = peso;
        this.valor = valor;
        this.cantidad = 1;
    }
/**
 * Método que nos devuelve el  nombre del objeto
 * @return
 */
    public String getNombre() {
        return nombre;
    }
/**
 * Método que nos devuelve el peso del objeto
 * @return
 */
    public double getPeso() {
        return peso;
    }
/**
 * Método que nos devuelve el valor del objeto
 * @return
 */
    public double getValor() {
        return valor;
    }
/**
 * Método que establece el valor del objeto dado este por parámetro
 * @param profit
 */
    public void setValor(double profit) {
        this.valor = profit;
    }
/**
 * Método que devuelve la cantidad que objetos del mismo tipo
 * @return
 */
    public double getCantidad() {
        return cantidad;
    }
/**
 * Método que establece la cantidad de objetos de un tipo
 * @param quantity
 */
    public void setCantidad(double quantity) {
        this.cantidad = quantity;
    }
/**
 * Cadena que imprime por pantalla los datos de un objeto
 */
    @Override
    public String toString() {
        return nombre + ", peso=" + (peso*cantidad) + ", valor=" + (valor*cantidad);
    }

}