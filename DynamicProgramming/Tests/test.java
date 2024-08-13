package org.eda2.Practica_03;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class test {

    @BeforeEach
    void setUp() throws Exception {
    }

    @Test
    void testAlgoritmoMochila() {
        Mochila mochila1 = new Mochila("p02",1);
        ArrayList<Objeto> prueba = mochila1.Prob1Mochila();
        assertEquals("[Objeto3, peso=11.0, valor=23.0, Objeto4, peso=8.0, valor=15.0,"
                + " Objeto2, peso=7.0, valor=13.0]", prueba.toString());
    }

    @Test
    void testAlgoritmoMochilaIlimitada() {
        Mochila mochila1 = new Mochila("p02",1);
        ArrayList<Objeto> prueba = mochila1.mochilaIlimitada();
        assertEquals("[Objeto3, peso=11.0, valor=23.0, Objeto4, peso=8.0, valor=15.0, "
                + "Objeto2, peso=7.0, valor=13.0]", prueba.toString());
    }

    @Test
    void testAlgoritmoMochilaGreedy() {
        Mochila mochila1 = new Mochila("p02",1);
        ArrayList<Objeto> prueba = mochila1.greedyMochila();
        assertEquals("[Objeto2, peso=7.0, valor=13.0, Objeto4, peso=8.0, valor=15.0, "
                + "Objeto5, peso=9.0, valor=16.0, Objeto3, peso=2.0, valor=4.181818181818182]", prueba.toString());
    }



}