package org.eda2.Practica_04;

import java.io.File;
import java.util.ArrayList;
import org.eda2.Practica_04.Bab;



public class Main <Vertex extends Comparable<Vertex>>{
	
	
	public static void main(String[] args) {
		
		String file = System.getProperty("user.dir")
				+File.separator+"src"
				+File.separator+"org"
				+File.separator+"eda2"
				+File.separator+"Practica_04"
				+File.separator+ "graphEDAlandTSP.txt";
		

		Bab b = new Bab(file, "Almeria");
		System.out.println(b.mapa);
		
		//Ejecutamos el algoritmo de branchandbound implementado en la clase Bab 
		ArrayList<String> branchandbound = b.TSPBaB("Almeria");
		System.out.println(branchandbound + " coste = " + b.costeFinal);
		
		//Ejecutamos el algoritmo de backtracking implementado en la clase Bab 
		ArrayList<ArrayList<String>> backtracking = b.BackTracking();
		System.out.println(backtracking);

	}
}