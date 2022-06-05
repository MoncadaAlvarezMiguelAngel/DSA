package org.eda2.Practica_04;
 import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;


public class Bab {
	
	public HashMap<String, HashMap<String, Double>> mapa;//Implementado con hashmaps para evitar repetidos
	private boolean dirigido;
	private int numVertices;
	private double valorMinimoArista;
	private String origen;
	double costeFinal;
	ArrayList<ArrayList<String>> caminos = new ArrayList<ArrayList<String>>();
	
	/**
	 * Contructor vacío
	 */
	public Bab() {
		this.dirigido = true;
		this.mapa = new HashMap<String, HashMap<String, Double>>();
	}
	
	/**
	 * Metodo constructor encargado de cargar el archivo
	 * @param file
	 */
	
	public Bab(String file, String origen) {
		this(); 
		this.origen = origen;
		Scanner scan = null;
		String linea = "";
		String[] aux = null;

		try {
			scan = new Scanner(new File(file));
		} catch (Exception ex) {
			System.out.println("Error al cargar el archivo. El sistema no puede encontrar el archivo especificado.");
			System.exit(-1);
		}
		
		linea = scan.nextLine();
		setDirigido(linea.equals("0") ? false : true);
		
		linea = scan.nextLine();
		setnumVertices(Integer.parseInt(linea));
		for (int i = 0; i < Integer.valueOf(linea); i++) {
			addVertex(scan.nextLine());
		}
		
		linea = scan.nextLine();
		for (int i = 0; i < Integer.valueOf(linea); i++) {
			aux = scan.nextLine().split(" ");
			addEdge(aux[0], aux[1], Double.parseDouble(aux[2]));
		}
	}
	
	/**
	 * Get origen
	 * @return
	 */
	public String getOrigen() {
		return origen;
	}
	
	/**
	 * Set origen
	 * @return
	 */
    public void setOrigen(String source) {
		this.origen = source;
	}
    
	/**
	 * Get numVertices
	 * @return 
	 */
	public int getnumVertices() {
		return numVertices;
	}

	/**
	 * Set numVertices
	 * @param numVertices
	 */
	public void setnumVertices(int numVertices) {
		this.numVertices = numVertices;
	}

	/**
	 * Get valorMinimoArista
	 * @return 
	 */
	public double getvalorMinimoArista() {
		return valorMinimoArista;
	}

	/** 
	 * Set valorMinimoArista
	 * @param minEdgeValue
	 */
	public void setvalorMinimoArista(double valorMinimoArista) {
		valorMinimoArista = getvalorMinimoArista();
		this.valorMinimoArista = valorMinimoArista;
	}

	/**
	 * Set Dirigido
	 * @param dirigido
	 */
	public void setDirigido(boolean dirigido) {
  		this.dirigido = dirigido;
	}
	
	/**
	 * Método que añade un vertice al mapa en cuestión
	 * @param vertice
	 */
	
	public void addVertex(String vertice) {
        if (this.mapa.containsKey(vertice)) return;
        this.mapa.put(vertice, new HashMap<String, Double>());
  	} 

  	/**
  	 * Metodo  que añade una arista al grafo 
  	 * @param v1
  	 * @param v2
  	 * @param weight
  	 */
  	public void addEdge(String v1, String v2, double weight) {
  		addVertex(v1);
  		addVertex(v2);
  		this.mapa.get(v1).put(v2, weight);
  		if (!this.dirigido) {
        	this.mapa.get(v2).put(v1, weight);
       	}
  	}

	/**
  	 *  Método que devuelve el conjunto de vecinos
  	 *  @param from 
  	 *  @return TreeSet<String>
   	 */
	
	public TreeSet<String> getNeighbors(String from) {
		HashMap<String, Double> neighbors = this.mapa.get(from);
		return neighbors == null ? null : new TreeSet<String>(neighbors.keySet());
	}
	
	/**
  	 * Método que devuelve el número de aristas del grafo
  	 * @return
  	 */
  	public int numberOfEdges() {
		int count = 0;
		for (HashMap<String, Double> itMap : this.mapa.values())
			count += itMap.size();
		return count;
	}
  	
	/**
	 * Metodo que dice si un grafo contiene una arista o no
	 * @param candidato
	 * @param etapa
	 * @return
	 */
	public boolean containsEdge(String candidato, String etapa) {
		return this.mapa.containsKey(candidato) && this.mapa.get(candidato).containsKey(etapa);
	}
	
	
	/**
	 * Metodo que agrega la etapa a un array solucion
	 * @param etapa
	 */
	private void agregarEtapaASolucion(String[] etapa) {
		ArrayList<String> aux = new ArrayList<String>();
		for (int i = 0; i < etapa.length; i++) {
			aux.add(etapa[i]);
		}
		caminos.add(aux);
	}

	/**
	 * Metodo que dice si un candidato es aceptable como solucion o no
	 * @param etapa
	 * @param nivel
	 * @param candidato
	 * @param visitados
	 * @return
	 */
	private boolean esAceptable(String[] etapa, int nivel, String candidato, TreeMap<String, Boolean> visitados) {
		boolean esUltimoAlcanzable = true;
			if(nivel == visitados.size()-1) {
				esUltimoAlcanzable = containsEdge(candidato, etapa[0]);
			}
			return esUltimoAlcanzable && !visitados.get(candidato);
	}
	
    /**
     * Metodo que devuelve la arista de menor valor
     * @return 
     */
    public double minEdgeValue() {
        double minimum = Double.MAX_VALUE;
        for (HashMap<String, Double> it : mapa.values()) {
            for (Double v: it.values()) {
                if (v<minimum) {
                    minimum = v;
                }
            }
        }
        return minimum;
     }
    
	/**
	 * Metodo que delvuelve el peso entre dos vertices
	 * @param vertice1
	 * @param vertice2
	 * @return
	 */
	public Double getWeight (String vertice1, String vertice2) {
		return containsEdge(vertice1, vertice2) ? this.mapa.get(vertice1).get(vertice2) : null;
	}
	


	/**
	 * Metodo que nos devulve el numero de vertices
	 * @return 
	 */
	public int numberOfVertex() {
		return this.mapa.size();
	}
	
	/**
	 * @return
	 */

	public ArrayList<ArrayList<String>> BackTracking() {
		TreeMap<String, Boolean> visitados = new TreeMap<String, Boolean>();
		ArrayList<String> resultados = new ArrayList<String>();
		String[] etapa = new String[numVertices+1]; 
		int nivel = 1;
		inicializarEstructuras(visitados, etapa);
		BackTracking(etapa, nivel, visitados, resultados);
		return this.caminos;
	}

	/**
	 * @param visitados
	 * @param etapa
	 */
	private void inicializarEstructuras(TreeMap<String, Boolean> visitados, String[] etapa) {
		 for (String vertice : mapa.keySet()) {
	            visitados.put(vertice, false);
	        }
	        etapa[0] = origen;
	        etapa[etapa.length-1] = origen;
	        visitados.put(origen, true);
	}

	/**
	 * @param etapa
	 * @param nivel
	 * @param visitados
	 * @param resultados
	 */
	private void BackTracking(String[] etapa, int nivel, TreeMap<String, Boolean> visitados, ArrayList<String> resultados) {
		for (String candidato : getNeighbors(etapa[nivel-1])) { 
			if(esAceptable(etapa, nivel, candidato, visitados)) { 
				etapa[nivel] = (String) candidato; 
				if(nivel == visitados.size()-1) { 
					agregarEtapaASolucion(etapa);
				}else {
					visitados.put(candidato, true);
					BackTracking(etapa, nivel+1, visitados, resultados);
					visitados.put(candidato, false);
				}
			}
		}
		etapa[nivel] = null; 
	}
	
	/**
	 * @param origen
	 * @return
	 */
	public ArrayList<String> TSPBaB(String origen) {
		HashMap<String, Double> neighborMap = mapa.get(origen);
		if (neighborMap == null)
			return null;
		
	
		double minEdgeValue = minEdgeValue();

		PathNode firstNode = new PathNode(origen);
		
		firstNode.setMinEdgeValue(minEdgeValue);

		PriorityQueue<PathNode> priorityQueue = new PriorityQueue<>();
		
		ArrayList<String> shortestCircuit = null;

		priorityQueue.add(firstNode);
 
		double bestCost = Double.MAX_VALUE;
		int nVertices = numberOfVertex();


		while(priorityQueue.size() > 0) {
			PathNode Y = priorityQueue.poll();
			if(Y.getEstimatedCost() >= bestCost) break;
			String from = Y.lastVertexRes();
			if(Y.getVisitedVertex() == nVertices && containsEdge(from, origen)) {
				Y.addVertexRes(origen);
				Y.setTotalCost(Y.getTotalCost() + getWeight(from, origen));
				if(Y.getTotalCost() < bestCost) {
					bestCost = Y.getTotalCost();
					shortestCircuit = Y.getRes();
				}
			}else {
				for (String to : getNeighbors(from)) {
					if(!Y.isVertexVisited(to)) {
						PathNode X = new PathNode(Y);
						X.addVertexRes(to);
						X.setVisitedVertex(X.getVisitedVertex() + 1);
						X.setTotalCost(X.getTotalCost() + getWeight(from, to));
						X.setEstimatedCost(X.getTotalCost() + (nVertices - X.getVisitedVertex() + 1) * minEdgeValue);
						if(X.getEstimatedCost() < bestCost) {
							priorityQueue.add(X);
						}
					}
				}
			}
		}
		costeFinal = bestCost;
		return shortestCircuit;
	}	
}