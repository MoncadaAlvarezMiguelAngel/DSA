package org.eda2.Practica_03;

import java.util.Comparator;

public class CompararMochila implements Comparator<Objeto>{
/**
 * Método compare que nos devuelve un int(-1,0,1) de manera que ordenamos de forma descendente los objetos.
 */
    @Override
    public int compare(Objeto o1, Objeto o2) {

    int compare = Double.compare(o1.getPeso(), o2.getPeso());
    if (compare == 0) {
        int compare2 = Double.compare(o1.getValor(),o2.getValor());
        return compare2 == 0 ?  o1.getNombre().compareTo(o2.getNombre()) : compare2;
    }
    return compare;

    }

}