package org.eda2.Practica_04;

public class Arista implements Comparable<Arista>{

    private String origen;
    private String destino;
    private double peso;

    public Arista(String origen, String destino, double peso) {
        super();
        this.origen = origen;
        this.destino = destino;
        this.peso = peso;
    }
  
	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}


	public String getDestino() {
		return destino;
	}


	public void setDestino(String destino) {
		this.destino = destino;
	}


	public double getPeso() {
		return peso;
	}


	public void setPeso(double peso) {
		this.peso = peso;
	}

	@Override
	public String toString() {
		  return origen + "=" + destino + ":" + peso +" |";
	}

	@Override
	public int compareTo(Arista o) {
		int comp = Double.compare(this.peso, o.peso);
        return comp == 0 ? this.origen.compareTo(o.origen) : comp;
	}

}