package org.eda2.Practica_04;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;

public class TestBab {

	public static void main(String[] args) {
		densidad();
	}
	
	static String file = System.getProperty("user.dir")
			+File.separator+"src"
			+File.separator+"org"
			+File.separator+"eda2"
			+File.separator+"Practica_04"
			+File.separator+ "graphEDAlandTSP.txt";
	
	private static void densidad() {
		
		Bab m = new Bab(); 
		m.setOrigen("0"); 
		m.setDirigido(false);
		int N = 1000; //Vértices del grafo
		int porcentaje = 10;
		
		
		int minAristas = N - 1;//Arístas minimas para un grafo conexo
		int maxAristas = N * (N - 1) / 2;//Aristas máximas para un grafo conexo
		int aumenta=((maxAristas-minAristas)*porcentaje)/100;//Aristas añadidas en cada vuelta (irá aumentando para ver la diferencia entre algoritmos)
		LinkedList<Arista> aux = new LinkedList<>();
		long start = 0;
		long end = 0;
		
		for (int i = 0; i < N; i++) {
			for (int j = i+1; j < N; j++) {
				double peso = (int)(Math.random()*100)+1; 
				if(i+1 == j) {
					m.addEdge(i+"", j+"", peso);
				}else {
					aux.add(new Arista(i+"", j+"", peso));
				}
			}
		}
		
		
		System.out.println("NumVertices\tNumAristas\tTB&B\tTBackTracking");
		boolean stop = false;
		do {
			stop = aux.isEmpty();
			System.out.print(m.numberOfVertex()+"\t\t"+m.numberOfEdges()+"\t");
			
			//Medimos el tiempo para el algoritmo de Branch and Bound
			Bab b = new Bab(file, "Albacete");
			start = System.currentTimeMillis();
			ArrayList<String> branchandbound = b.TSPBaB("Albacete");
			end = System.currentTimeMillis();
			System.out.print("\t"+(end-start)+"\t");
			
			//Medimos el tiempo para el algoritmo de Back Tracking
			start = System.currentTimeMillis();
			ArrayList<ArrayList<String>> backtracking = b.BackTracking();
			end = System.currentTimeMillis();
			System.out.print("\t"+(end-start));
			System.out.println();
			
				
			//Agregamos tantas aristas como indique los incrementos (siempre que queden)
			for (int i = 0; i < aumenta && !aux.isEmpty(); i++) {
				Arista e = aux.poll();//Saca la arista de aux
				m.addEdge(e.getOrigen(), e.getDestino(), e.getPeso());//La agrega al grafo
			}		
		}while(!stop);
		
	}

}