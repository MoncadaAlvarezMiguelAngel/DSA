package org.eda2.Practica_03;
import java.io.File; 
import java.io.FileNotFoundException;  
import java.util.Scanner;

import java.util.ArrayList;


public class Mochila {  


	int contador = 1;
	
	/**
	 * Metodo de carga, en este método cargamos los archivos de texto necesarios
	 * para el funcionamiento del algoritmo.
	 * Se necesitan cargar tres archivos: el primero para cargar la capacidad de 
	 * la mochila, el segundo para cargar el peso de cada objeto y el tercero para cargar
	 * el valor de cada objeto
	 * @param basefilename
	 * @throws FileNotFoundException
	 */
	private void loadFile(String basefilename) throws FileNotFoundException { //METODO LOAD
		File f = new File(folder+basefilename+"_c.txt");
		this.objects = new ArrayList<Objeto>();
		Scanner sc = new Scanner(f);
		this.capacidad = Integer.parseInt(sc.nextLine().trim());
		sc.close();

		f = new File(folder+basefilename+"_w.txt");
		sc = new Scanner(f);
		
		while (sc.hasNextLine()) {
			String linea = sc.nextLine().trim();
			Objeto objetoIterado = new Objeto("Objeto" + contador,Integer.parseInt(linea),0);
			objects.add(objetoIterado);
			contador++;
		}
		
		sc.close();
		
		f = new File(folder+basefilename+"_p.txt");
		sc = new Scanner(f);
		
		
		for (Objeto ob : objects) {
			String linea = sc.nextLine().trim();
			ob.setValor(Integer.parseInt(linea));
			
		}
		
		sc.close();
	}
	
	/**
	 * Este es el constructor, hay que pasarle el nombre del archivo (ejemplo: "p01") y un 1 o un 2 dependiendo
	 * del metodo load que queremos usar. El load 1 es para archivos sin decimales y 
	 * el load 2 es para archivos con decimales.
	 * 
	 * @param basefilename
	 * @param opcion
	 */
	public Mochila(String basefilename,int opcion) {  //CARGAR DATOS
		try {
			if (opcion == 1){
				loadFile(basefilename);
			}
			
			if (opcion == 2) {
				loadFile2(basefilename);
			}
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

	
	
	private ArrayList<Objeto> objects = new ArrayList<Objeto>();
	private double pesoFinal;
	private double valorFinal;
	private int capacidad;
	private String folder = System.getProperty("user.dir")+
			File.separator
			+"src"+File.separator
			+"org"+File.separator
			+"eda2"+File.separator
			+"Practica_03"+File.separator;
	
	/**
	 * Este es el algoritmo de la mochila 0-1
	 * Primero ordenamos de forma ascendente por peso
	 * Por otra parte, debemos diseñar una estructura tipo matriz con unas dimensiones específicas, concretamente de 
	 * tamaño (N+1) x (P+1), donde N representa el número de objetos y P el peso de la mochila.
	 * Al crear la matriz por defecto se rellenan todos los valores a 0
	 * Por último, una vez se ha calculado la matriz completa, sabremos el valor máximo que puede alcanzar nuestra mochila
	 * siendo este el situado en la última celda de la matriz.
	 * @return
	 */
	public ArrayList<Objeto> Prob1Mochila() {  //P01
		
		this.objects.sort(new CompararMochila());
		int n = this.objects.size();
		double[][] B = new double[n + 1][capacidad + 1];
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= capacidad; j++) {
				if (this.objects.get(i - 1).getPeso() <= j) {
					B[i][j] = Math.max(B[i - 1][j],
							this.objects.get(i - 1).getValor() + B[i - 1][j - (int)this.objects.get(i - 1).getPeso()]);
				} else {
					B[i][j] = B[i - 1][j];
				}
			}
		}
		return objetos(B); 
	}
	
	/** Este metodo es para recuperar los objetos de la mochila.
	 *  Partiendo de la última celda, verificaremos si la situada justo encima tiene un valor distinto (que siempre será inferior o igual).
	 *  Si el valor es igual, significará que el objeto de la fila actual no pertenece al conjunto
	 *  resultado, por lo que nos desplazaremos a la fila superior. En cambio, si el valor es distinto, significará que el objeto de la fila si debe pertenecer al resultado,
	 *  por lo que desplazaremos a la fila superior y nos desplazaremos el peso del objeto escogido a la izquierda.
	 * 
	 * @param B
	 * @return
	 */
	public ArrayList<Objeto> objetos(double[][] B) { //RECOGER OBJETOS PARTE01
		ArrayList<Objeto> results = new ArrayList<Objeto>();
		valorFinal = B[B.length-1][B[0].length-1];
		pesoFinal = 0;
		int j = B[0].length - 1; 
		for (int i = B.length - 1; i > 0; i--) { 
			if (B[i][j] != B[i - 1][j]) {
				results.add(this.objects.get(i - 1));
				pesoFinal += this.objects.get(i-1).getPeso();
				j -= this.objects.get(i - 1).getPeso();
			}
		}
		return results;
	}
	
	/**
	 * Algoritmo de la mochila 0-1: elementos ilimitados
	 * En esta ocasión tendremos un array de tamaño P+1, de forma que array[ i ] almacene 
	 * el valor máximo que se puede lograr usando todos los elementos bajo una capacidad de mochila i.
	 * El primer paso es ordenar los elementos de forma ascendente por su peso, eso no cambia respecto al algoritmo de la mochila clásico.
	 * Ahora solo queda valorar que es mejor, si el valor que habia en el array en la posición i (valor antiguo, similar al de la fila superior) o 
	 * el valor de la celda desplazada el peso de elemento a la izquierda más su propio valor. El funcionamiento es similar que el clásico, pero ahora 
	 * no usaremos una matriz, sino un simple array, por lo que ahorramos en consumo de memoria.
	 * El valor máximo que se puede alcanzar con la mochila es el que está en el array en la última posición.
	 * @return
	 */
    public ArrayList<Objeto> mochilaIlimitada(){  //MOCHILA PARTE02
		this.objects.sort(new CompararMochila()); 
        int n = this.objects.size();
		double[] array = new double[capacidad + 1];
		for (int i = 0; i <= capacidad; i++) {
			for (int j = 0; j < n; j++) {
				if(this.objects.get(j).getPeso()  <= i) {
					array[i] = Math.max(array[i], array[i - (int)this.objects.get(j).getPeso()]+this.objects.get(j).getValor());
				}
			}
		}
		return items(array);
	}
    
    /**
     * Algoritmo para la recuperación de los objetos de la mochila ilimitada
     * Nuestro algoritmo iterativo permanecerá en bucle mientras la capacidad restante sea superior al menor de los pesos de los objetos.
     * Lo que haremos será buscar aquel objeto que, en el espacio que queda de la mochila, más valor nos proporcione. Una vez se encuentre el
     * mejor candidato, se agrega a la solución y se decrementa el espacio de la mochila.
     * 
     * @param array
     * @return
     */
	
    private ArrayList<Objeto> items(double[] array) {  //RECUPERAR OBJETOS PARTE02
		valorFinal = 0;
		pesoFinal = 0;
		ArrayList<Objeto> results = new ArrayList<Objeto>();
		int c = this.capacidad; 
		double minWeight = this.objects.get(0).getPeso(); 
		int n = this.objects.size();
		while(c >= minWeight) { 
			double maxValue = 0;
			int item = -1; 
			for (int i = n-1; i >= 0; i--) { 
				if(c - this.objects.get(i).getPeso() >= 0) { 
					double newValue = array[c - (int)this.objects.get(i).getPeso()] + this.objects.get(i).getValor();
					if(newValue > maxValue) {
						maxValue = newValue;
						item = i;
					}
				}
			}
			if(item == -1) break; 
			valorFinal += this.objects.get(item).getValor();
			pesoFinal += this.objects.get(item).getPeso();
			results.add(this.objects.get(item));
			c -= this.objects.get(item).getPeso(); 
		}
		return results;
	}

    /**
	 * Método greedyKnapsack, en este método establecemos una relación calidad/peso y ordenamos
	 * los distintos elementos que vamos cargando del archivo (de forma descendente en nuestro caso) basándonos
	 * en dicha relación hasta que se alcance la capacidad establecida.
	 * @return
	 */
    public ArrayList<Objeto> greedyMochila(){ //MOCHILA PARTE 04
		this.objects.sort(new CompararMochila()); 
		valorFinal = 0;
		pesoFinal = 0;
		ArrayList<Objeto> results = new ArrayList<Objeto>();
		for (Objeto ob : objects) {
			if(pesoFinal + ob.getPeso() <= capacidad) { 
				ob.setCantidad(1);
				results.add(ob);
				pesoFinal += ob.getPeso();
				valorFinal += ob.getValor();
				if(pesoFinal == capacidad) break;
			}else {
				ob.setCantidad((capacidad-pesoFinal)/ob.getPeso());
				results.add(ob);
				pesoFinal += ob.getPeso() * ob.getCantidad();
				valorFinal += ob.getValor() * ob.getCantidad();
				break; 
			}
		}
		return results;	
	}
    
    /**
     * Algoritmo para soportar los pesos decimales
     *  Multiplicamos todos los decimales por un factor de la forma 10x , donde "x" es el 
     *  número máximo de decimales.
     *  Para ello usamos los metodos "numeroDecimales" y "NumeroMaxDecimales"
     *  Ahora, puesto que los valores no tienen decimales, los podemos tratar como números enteros
     * @return
     */
	public ArrayList<Objeto> decimalMochila() {  //MOCHILA PARTE 03

		
		this.objects.sort(new CompararMochila());
		int n = this.objects.size();
		double[][] B = new double[n + 1][capacidad + 1];
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= capacidad; j++) {
				maxNumberOfDecimals();
				if (this.objects.get(i - 1).getPeso() <= j) {
					B[i][j] = Math.max(B[i - 1][j],
							this.objects.get(i - 1).getValor() + B[i - 1][j - (int)this.objects.get(i - 1).getPeso()]);
				} else {
					B[i][j] = B[i - 1][j];
				}
			}
		}
		return objetos(B); 

	}
	
	/**
	 * Metodo para determinar el número de decimales que tiene un número
	 * @param d
	 * @return
	 */
	
	private int numberOfDecimals(double d) { 
		if(d == (int)d) return 0;
		String text = Double.toString(Math.abs(d));
		int integerPlaces = text.indexOf('.');
		if(integerPlaces == -1) return 0;
		int decimalPlaces = text.length() - integerPlaces - 1;
		return decimalPlaces;
	}
	
	/**
	 * Metodo para obtener el máximo número de decimales
	 * @return
	 */
	private int maxNumberOfDecimals() {
		int max = numberOfDecimals(capacidad);
		for (Objeto ob : objects) {
			int n = numberOfDecimals(ob.getPeso());
			max = n > max ? n : max;
		}
		return (int) Math.pow(10, max);
	}
	
	
	/**
	 * Modificacion del metodo "loadFile1" para que pueda soportar
	 * la lectura de numeros reales
	 * @param basefilename
	 * @throws FileNotFoundException
	 */
	private void loadFile2(String basefilename) throws FileNotFoundException { //METODO LOAD
		File f = new File(folder+basefilename+"_c.txt");
		this.objects = new ArrayList<Objeto>();
		Scanner sc = new Scanner(f);
		this.capacidad = Integer.parseInt(sc.nextLine().trim());
		sc.close();

		f = new File(folder+basefilename+"_w.txt");
		sc = new Scanner(f);
		
		while (sc.hasNextLine()) {
			String linea = sc.nextLine().trim();
			Objeto objetoIterado = new Objeto("Objeto" + contador,Double.parseDouble(linea),0);
			objects.add(objetoIterado);
			contador++;
		}
		
		sc.close();
		
		f = new File(folder+basefilename+"_p.txt");
		sc = new Scanner(f);
		
		
		for (Objeto ob : objects) {
			String linea = sc.nextLine().trim();
			ob.setValor(Double.parseDouble(linea));
			
		}
		
		sc.close();
	}
	
}