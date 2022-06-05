package org.eda2.Practica_04;

import java.util.ArrayList;



public class PathNode implements Comparable<PathNode>{
    
    private ArrayList<String> res;
    private int visitedVertex;
    private double totalCost;
    private double estimatedCost;
    private double minEdgeValue;
    
    /**
     * Metodo constructor que recibe como parametro vertexToVisit
     * @param vertexToVisit
     */
    public PathNode(String vertexToVisit) {
        res = new ArrayList<String>();
        res.add(vertexToVisit);
        setVisitedVertex(1);
        totalCost = 0.0;
        estimatedCost = numberOfVertex() * getMinEdgeValue();
    }
    /**
     * Get MinEdgeValue
     * @param vertexToVisit
     */
    
    public double getMinEdgeValue() {
    	return this.minEdgeValue;
    }
    /**
     * Set MinEdgeValue
     * @param vertexToVisit
     */
    public void setMinEdgeValue(double minEdgeValue2) {
    	this.minEdgeValue=minEdgeValue2;
    }
    
	/**
	 * Método constructor copia
	 * @param parentPathNode
	 */
	public PathNode(PathNode parentPathNode) {
        this.res = new ArrayList<String>(parentPathNode.res);
        this.setVisitedVertex(parentPathNode.getVisitedVertex());
        this.totalCost = parentPathNode.totalCost;
        this.estimatedCost = parentPathNode.estimatedCost;
    }
    
    /**
     * Get Res
     * @return 
     */
    public ArrayList<String> getRes(){
        return this.res;
    }
    
    /**
     * Metodo add a la estructura res
     * @param v
     */
    public void addVertexRes(String v) {
        this.res.add(v);
    }
    
    /**
     * Método que nos devuelve el ultimo vertice de la estructura res
     * @return 
     */
    public String lastVertexRes() {
        return this.res.get(this.res.size()-1);
    }
    
    /**
     * Metodo para comprobar si un vertice ha sido visitado
     * @param to
     * @return boolean
     */
    public boolean isVertexVisited(String to) {
        return this.res.contains(to);
    }
    
    
    /**
     *  Get TotalCos
     * @return 
     */
    public double getTotalCost() {
        return this.totalCost;
    }
    
    /**
     * Set TotalCost
     * @param totalCost
     */
    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }
    
    /**
     * @return estimatedCost
     */
    public double getEstimatedCost() {
        return estimatedCost;
    }
    
    /**
     * Metodo que modifica el coste estimado
     * @param estimatedCost
     */
    public void setEstimatedCost(double estimatedCost) {
        this.estimatedCost = estimatedCost;
    }
  	
  	/**
  	 * @return tamaño de res
  	 */
  	public int numberOfVertex() {
		return this.res.size();
  	}
     
    /**
     * Metodo toString
     */
    public String toString() {
        return this.getVisitedVertex() + "= " + this.totalCost;
    }
    
    /**
     * Metodo CompareTo
     */
    @Override
    public int compareTo(PathNode p) {
        return Double.compare(this.estimatedCost, p.estimatedCost);
    }

	/**
	 * Get VisitedVertex
	 * @return 
	 */
	public int getVisitedVertex() {
		return visitedVertex;
	}

	/**
	 * Set VisitedVertex
	 * @param visitedVertex
	 */
	public void setVisitedVertex(int visitedVertex) {
		this.visitedVertex = visitedVertex;
	}
  }