package model;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

public class CalculadoraTest {

        Calculadora calculadora;
        float[][] matrizUno = {
                        { 1, 1 },
                        { 1, 1 },
        };

        // float[][] matrizUno;

        @Before
        public void before() {

                calculadora = new Calculadora();
                calculadora.setMatrizUno(matrizUno);

        }

        @Test
        public void sumarMatricesCompararResultados() throws NullPointerException {
                float[][] matrizDos = {
                                { 3, 3 },
                                { 6, 6 },
                };

                calculadora.setMatrizDos(matrizDos);

                float[][] matrizEsperada = {
                                { 4, 4 },
                                { 7, 7 },
                };

                assertNotNull(matrizUno);
                assertNotNull(matrizDos);

                calculadora.sumarMatrices();

                assertArrayEquals(calculadora.getMatrizResultante(), matrizEsperada);
        }

        @Test
        public void sumarMatricesCompararTamaños() throws NullPointerException {
                float[][] matrizDos = {
                                { 3, 3 },
                                { 6, 6 },
                };

                calculadora.setMatrizDos(matrizDos);

                assertNotNull(matrizUno);
                assertNotNull(matrizDos);

                calculadora.sumarMatrices();

                int numColumnasMatrizUno = matrizUno[0].length;
                int numColumnasMatrizDos = matrizDos[0].length;

                assertEquals(numColumnasMatrizUno, numColumnasMatrizDos);
        }

        @Test
        public void multiplicacionDeMatricesCompararResultados() {
                float[][] matrizDos = {
                                { 1, 1 },
                                { 1, 1 },
                };

                calculadora.setMatrizDos(matrizDos);

                calculadora.multiplicarMatrices();

                float[][] matrizEsperada = {
                                { 2, 2 },
                                { 2, 2 },
                };

                assertArrayEquals(calculadora.getMatrizResultante(), matrizEsperada);

                int numColumnaUno = matrizUno[0].length;
                int numFilaDos = matrizDos.length;

                assertEquals(numColumnaUno, numFilaDos);
        }

        @Test
        public void multiplicarMatrices() throws NullPointerException {

                assertNotNull(matrizUno);

                float[][] matrizEsperada = {
                                { 5, 5 },
                                { 5, 5 },
                };
                calculadora.multiplicarPorEscalar(5);

                assertArrayEquals(calculadora.getMatrizResultante(), matrizEsperada);
        }

}
