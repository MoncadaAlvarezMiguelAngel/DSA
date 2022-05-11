package org.eda2.Practica_03;
import org.eda2.Practica_03.Mochila;
import org.eda2.Practica_03.Objeto;


import java.util.ArrayList;

public class Ejecutar {

	public static void main(String[] args) {
		
		//P01
		/**
		 * Ejecutamos el algoritmo básico para los archivos P01
		 */
		Mochila objeto = new Mochila("p01",1);
		ArrayList<Objeto> wm = objeto.Prob1Mochila(); 
		System.out.println("Mochila clasica ==>"+wm);
		
		
		//P02
		/**
		 * Ejecutamos el algoritmo mochilaIlimitada para los archivos P02
		 */
		Mochila objeto2 = new Mochila("p02", 1);
		ArrayList<Objeto> uk = objeto2.mochilaIlimitada(); 
		System.out.println("Mochila ilimitada ==>"+uk);
		
	
		//P02
		/**
		 * Ejecutamos el algoritmo greedyMochila para los archivos P02
		 */
		Mochila objeto4 = new Mochila("p02", 1);
		ArrayList<Objeto> gk = objeto4.greedyMochila(); 
		System.out.println("Greedy Mochila ==>"+gk);
		
	
		//P03 (le añadimos decimales en el archivo)
		/**
		 * Ejecutamos el algoritmo mochila con decimales para los archivos P03
		 */
		Mochila objeto8 = new Mochila("p03", 2);
		ArrayList<Objeto> go = objeto8.decimalMochila(); 
		System.out.println("Decimal Mochila ==>"+go);
	
		
		
		
		
	}

}