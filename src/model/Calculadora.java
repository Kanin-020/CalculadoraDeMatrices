/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Arrays;
import java.util.Collections;

/**
 * Se encarga de la lógica de la Calculadora.
 */
public class Calculadora {

    private float[][] matrizUno;
    private float[][] matrizDos;
    private float[][] matrizResultante;
    final int UNA_DIMENSION = 1;
    final int PRIMERA_POSICION = 0;
    final int DUPLICAR = 2;

    /**
     * Se encarga de sumar dos matrices.
     */
    public void sumarMatrices() {
        int numFilas;
        numFilas = getNumFilasMatrizUno();
        int numColumnas;
        numColumnas = getNumColumnasMatrizUno();

        matrizResultante = new float[numFilas][numColumnas];

        int indiceFilas;
        for (indiceFilas = 0; indiceFilas < numFilas; indiceFilas++) {
            int indiceColumnas;
            for (indiceColumnas = 0; indiceColumnas < numColumnas; indiceColumnas++) {
                matrizResultante[indiceFilas][indiceColumnas] = matrizUno[indiceFilas][indiceColumnas]
                        + matrizDos[indiceFilas][indiceColumnas];
            }
        }
    }

    /**
     * Se encarga de multiplicar una matriz por un escalar.
     * @param escalarEntrada Escalar de tipo flotante por el cual la matrizUno es multiplicada.
     */
    public void multiplicarPorEscalar(float escalarEntrada) {

        int numFilas;
        numFilas = getNumFilasMatrizUno();
        int numColumnas;
        numColumnas = getNumColumnasMatrizUno();

        float escalar;
        escalar = escalarEntrada;

        matrizResultante = new float[numFilas][numColumnas];

        int indiceFilas;
        for (indiceFilas = 0; indiceFilas < numFilas; indiceFilas++) {
            int indiceColumnas;
            for (indiceColumnas = 0; indiceColumnas < numColumnas; indiceColumnas++) {
                matrizResultante[indiceFilas][indiceColumnas] = matrizUno[indiceFilas][indiceColumnas] * escalar;
            }
        }

    }

    /**
     * Se encarga de multiplicar 2 matrices.
     */
    public void multiplicarMatrices() {

        int numFilas;
        numFilas = getNumFilasMatrizUno();
        int numColumnas;
        numColumnas = getNumColumnasMatrizDos();

        int numDimensionComun;
        numDimensionComun = getNumColumnasMatrizUno();

        matrizResultante = new float[numFilas][numColumnas];

        int indiceFilas;
        for (indiceFilas = 0; indiceFilas < numFilas; indiceFilas++) {
            int indiceColumnas;
            float sumador;
            for (indiceColumnas = 0; indiceColumnas < numColumnas; indiceColumnas++) {
                sumador = 0;
                int indiceDimensionComun;
                for (indiceDimensionComun = 0; indiceDimensionComun < numDimensionComun; indiceDimensionComun++) {
                    sumador += matrizUno[indiceFilas][indiceDimensionComun]
                            * matrizDos[indiceDimensionComun][indiceColumnas];
                }
                matrizResultante[indiceFilas][indiceColumnas] = sumador;
            }
        }

    }

    /**
     * Obtiene la transpuesta de una matriz.
    */
    public void obtenerTranspuesta() {

        int numFilas;
        numFilas = getNumFilasMatrizUno();
        int numColumnas;
        numColumnas = getNumColumnasMatrizUno();

        matrizResultante = new float[numColumnas][numFilas];

        int indiceFilas;
        for (indiceFilas = 0; indiceFilas < numFilas; indiceFilas++) {
            int indiceColumnas;
            for (indiceColumnas = 0; indiceColumnas < numColumnas; indiceColumnas++) {
                matrizResultante[indiceColumnas][indiceFilas] = matrizUno[indiceFilas][indiceColumnas];
            }
        }

    }

    /**
     * Obtiene la inversa de una Matriz.
     */
    public void obtenerInversa() {

        float[][] matrizAumentada, matrizAumentadaInversa;

        matrizAumentada = aumentarMatriz();
        matrizAumentadaInversa = algoritmoGaussJordan(matrizAumentada);
        matrizResultante = reducirMatriz(matrizAumentadaInversa);

    }

    /**
     * Resuelve un sistema de ecuaciones cuadrado por método de Gauss.
     */
    public void resolverSistemaEcuacionesGaussJordan() {

        float[][] matrizAumentada;
        matrizAumentada = obtenerEcuaciones();

        float[][] matrizGaussJordan;
        matrizGaussJordan = algoritmoGaussJordan(matrizAumentada);

        int numColumnas;
        numColumnas = matrizGaussJordan.length;

        float[] arregloValoresX;
        arregloValoresX = new float[numColumnas];

        matrizResultante = new float[UNA_DIMENSION][numColumnas];

        int indiceFilas;
        for (indiceFilas = 0; indiceFilas < numColumnas; indiceFilas++) {
            arregloValoresX[indiceFilas] = matrizGaussJordan[indiceFilas][numColumnas];
        }

        int indiceColumnas;
        for (indiceColumnas = 0; indiceColumnas < numColumnas; indiceColumnas++) {
            matrizResultante[PRIMERA_POSICION][indiceColumnas] = arregloValoresX[indiceColumnas];
        }

    }

    /**
     * Obtiene el determinante de la matriz uno.
     */
    public void obtenerDeterminante() {

        matrizResultante = new float[UNA_DIMENSION][UNA_DIMENSION];

        float determinante;
        determinante = reglaDeSarrus(matrizUno);

        matrizResultante[PRIMERA_POSICION][PRIMERA_POSICION] = determinante;

    }

    /**
     * Resuelve un sistema de ecuaciones cuadrado por método de Cramer.
     */
    public void resolverSistemaEcuacionesCramer() {

        int numColumnas;
        numColumnas = getNumColumnasMatrizUno();

        float[][] matrizAmpliada;
        matrizAmpliada = ampliarMatriz();

        float determinante;
        determinante = reglaDeSarrus(matrizUno);

        matrizResultante = new float[UNA_DIMENSION][numColumnas];

        matrizResultante = calcularIncognitas(matrizAmpliada, determinante);

    }
    
    // Subfunciones utilizadas

    /**
     * Se encarga de aumentar la matriz uno, uniendola a la matriz identidad.
     * @return Devuelve la matriz aumentada de tipo float.
     */
    private float[][] aumentarMatriz() {

        int numFilasAumentada;
        numFilasAumentada = getNumFilasMatrizUno();
        int numColumnasAumentada;
        numColumnasAumentada = getNumColumnasMatrizUno() * DUPLICAR;

        float[][] matrizIdentidad;
        matrizIdentidad = generarMatrizIdentidad(getNumFilasMatrizUno(), getNumColumnasMatrizUno());

        float[][] matrizAumentada;
        matrizAumentada = new float[numFilasAumentada][numColumnasAumentada];

        int indiceFilas;
        for (indiceFilas = 0; indiceFilas < numFilasAumentada; indiceFilas++) {
            int indiceColumnas, indiceColumnasIdentidad;
            for (indiceColumnas = 0; indiceColumnas < numColumnasAumentada; indiceColumnas++) {
                if (indiceColumnas < matrizUno[indiceFilas].length) {
                    matrizAumentada[indiceFilas][indiceColumnas] = matrizUno[indiceFilas][indiceColumnas];
                } else {
                    indiceColumnasIdentidad = indiceColumnas - getNumColumnasMatrizUno();
                    matrizAumentada[indiceFilas][indiceColumnas] = matrizIdentidad[indiceFilas][indiceColumnasIdentidad];
                }
            }
        }
        return matrizAumentada;
    }

    /**
     * Se encarga de ampliar la matriz uno, uniendola al vector de resultados.
     * @return Devuelve la matriz ampliada de tipo float.
     */
    private float[][] ampliarMatriz() {
        int numFilasAmpliada;
        numFilasAmpliada = getNumFilasMatrizUno();
        int numColumnasAmpliada;
        numColumnasAmpliada = getNumColumnasMatrizUno() + 1;

        float[][] matrizAmpliada;
        matrizAmpliada = new float[numFilasAmpliada][numColumnasAmpliada];

        int indiceFilas;
        for (indiceFilas = 0; indiceFilas < numFilasAmpliada; indiceFilas++) {
            int indiceColumnas;
            for (indiceColumnas = 0; indiceColumnas < numColumnasAmpliada - 1; indiceColumnas++) {
                matrizAmpliada[indiceFilas][indiceColumnas] = matrizUno[indiceFilas][indiceColumnas];
            }
        }

        for (indiceFilas = 0; indiceFilas < numFilasAmpliada; indiceFilas++) {
            matrizAmpliada[indiceFilas][numColumnasAmpliada - 1] = matrizDos[indiceFilas][PRIMERA_POSICION];
        }

        return matrizAmpliada;
    }

    /**
     * Calcula los valores de las incognitas para resolver sistemas por método de Cramer.
     * @param matrizEntrada Matriz ampliada de la cual se obtendran los valores.
     * @param determinanteEntrada Determinante de la matriz original.
     * @return Vector de resultados de tipo flotante.
     */
    private float[][] calcularIncognitas(float[][] matrizEntrada, float determinanteEntrada) {

        float[][] matrizOriginal;
        matrizOriginal = matrizEntrada;
        float determinate;
        determinate = determinanteEntrada;
        int numColumnas;
        numColumnas = getNumColumnasMatrizUno();
        int numFilas;
        numFilas = getNumFilasMatrizUno();

        float[][] matrizReducida;
        matrizReducida = new float[numFilas][numColumnas];
        float[][] resultados;
        resultados = new float[UNA_DIMENSION][numColumnas];

        int indiceIntercambio;
        for (indiceIntercambio = 0; indiceIntercambio < matrizOriginal[PRIMERA_POSICION].length - 1; indiceIntercambio++) {

            float[][] matriz;
            
            matriz = new float[numFilas][numColumnas + 1];
            
            int indiceFilas;
            for (indiceFilas = 0; indiceFilas < numFilas; indiceFilas++) {
                int indiceColumnas;
                for (indiceColumnas = 0; indiceColumnas < numColumnas + 1; indiceColumnas++) {
                    matriz[indiceFilas][indiceColumnas] = matrizOriginal[indiceFilas][indiceColumnas];
                }
            }

            float intercambio;
            for (indiceFilas = 0; indiceFilas < matriz.length; indiceFilas++) {
                intercambio = matriz[indiceFilas][indiceIntercambio];
                matriz[indiceFilas][indiceIntercambio] = matriz[indiceFilas][numColumnas];
                matriz[indiceFilas][numColumnas] = intercambio;
            }

            for (indiceFilas = 0; indiceFilas < matriz.length; indiceFilas++) {
                int indiceColumnas;
                for (indiceColumnas = 0; indiceColumnas < matriz[PRIMERA_POSICION].length - 1; indiceColumnas++) {
                    matrizReducida[indiceFilas][indiceColumnas] = matriz[indiceFilas][indiceColumnas];
                }
            }

            resultados[0][indiceIntercambio] = reglaDeSarrus(matrizReducida) / determinate;

        }

        return resultados;
    }

    /**
     * Genera una matriz de indentidad.
     * @param numFilasEntrada Número de filas de la matriz identidad.
     * @param numColumnasEntrada Número de columnas de la matriz indentidad.
     * @return Devuelve la matriz identidad de tipo float.
     */
    private float[][] generarMatrizIdentidad(int numFilasEntrada, int numColumnasEntrada) {
        int numFilas;
        numFilas = numFilasEntrada;
        int numColumnas;
        numColumnas = numColumnasEntrada;

        float[][] matrizIdentidad;
        matrizIdentidad = new float[numFilas][numColumnas];

        int indiceFilas;
        for (indiceFilas = 0; indiceFilas < numFilas; indiceFilas++) {
            int indiceColumnas;
            for (indiceColumnas = 0; indiceColumnas < numColumnas; indiceColumnas++) {
                if (indiceFilas == indiceColumnas) {
                    matrizIdentidad[indiceFilas][indiceColumnas] = 1;
                } else {
                    matrizIdentidad[indiceFilas][indiceColumnas] = 0;
                }
            }
        }
        return matrizIdentidad;
    }

    /**
     * Aplica el algoritmo de GaussJordan a al matriz de entrada, obteniendo la matriz inversa.
     * @param matrizEntrada Matriz que será operada.
     * @return Matriz de tipo float después de aplicar el algoritmo se obtiene la matriz inversa.
     */
    private float[][] algoritmoGaussJordan(float[][] matrizEntrada) {
        float[][] matriz;
        matriz = matrizEntrada;
        float inversoMultiplicativo;
        int indiceFilas;
        try {
            for (indiceFilas = 0; indiceFilas < matriz.length; indiceFilas++) {
                if (matriz[indiceFilas][indiceFilas] != 1) {
                    inversoMultiplicativo = 1 / matriz[indiceFilas][indiceFilas];
                    int indiceColumnas;
                    for (indiceColumnas = 0; indiceColumnas < matriz[PRIMERA_POSICION].length; indiceColumnas++) {
                        matriz[indiceFilas][indiceColumnas] = matriz[indiceFilas][indiceColumnas]
                                * inversoMultiplicativo;
                    }
                }
                int indiceFilasPivote;
                for (indiceFilasPivote = 0; indiceFilasPivote < matriz.length; indiceFilasPivote++) {
                    float inversoAditivo;
                    if (indiceFilasPivote != indiceFilas) {
                        inversoAditivo = -1 * matriz[indiceFilasPivote][indiceFilas];
                        int indiceColumnas;
                        for (indiceColumnas = 0; indiceColumnas < matriz[PRIMERA_POSICION].length; indiceColumnas++) {
                            matriz[indiceFilasPivote][indiceColumnas] += matriz[indiceFilas][indiceColumnas]
                                    * inversoAditivo;
                        }
                    }
                }
            }
            return matriz;
        } catch (NullPointerException e) {
            System.out.println("No se encotró la dirección de memoria");
        }
        return null;
    }

    /**
     * Elimina los espacios no utilizados de la matriz aumentada.
     * @return Matriz con dimesiones reducidas.
     */
    private float[][] reducirMatriz(float[][] matrizEntrada) {
        float[][] matriz;
        matriz = matrizEntrada;
        int numFilas;
        numFilas = matriz.length;
        int numColumnas;
        numColumnas = matriz[PRIMERA_POSICION].length / 2;
        float[][] matrizInversa = new float[numFilas][numColumnas];

        int indiceFilas;
        for (indiceFilas = 0; indiceFilas < numFilas; indiceFilas++) {
            int indiceColumnas;
            for (indiceColumnas = numColumnas; indiceColumnas < matriz[PRIMERA_POSICION].length; indiceColumnas++) {
                matrizInversa[indiceFilas][indiceColumnas - numColumnas] = matriz[indiceFilas][indiceColumnas];
            }
        }
        return matrizInversa;
    }

    /**
     * Obtiene el vector resultante para el Sistema de ecuaciones por método de Gauss
     * @return Retorna el vector de resultados.
     */
    private float[][] obtenerEcuaciones() {

        int numEcuaciones;

        numEcuaciones = getNumColumnasMatrizUno();

        float[][] matriz;
        matriz = new float[numEcuaciones][numEcuaciones + 1];

        int indiceFilas;
        for (indiceFilas = 0; indiceFilas < numEcuaciones; indiceFilas++) {
            int indiceColumnas;
            for (indiceColumnas = 0; indiceColumnas < numEcuaciones + 1; indiceColumnas++) {
                if (indiceColumnas < numEcuaciones) {
                    matriz[indiceFilas][indiceColumnas] = matrizUno[indiceFilas][indiceColumnas];
                } else {
                    matriz[indiceFilas][indiceColumnas] = matrizDos[indiceFilas][PRIMERA_POSICION];
                }
            }
        }
        return matriz;

    }

    /**
     * Obtiene los cofactores necesarios para determinar el determinante.
     * @param matrizEntrada Matriz de la cual se obtendran los cofactores.
     * @param indiceFilaMatrizEntrada Indice que recorre las filas de la matriz.
     * @param indiceColumnaMatrizEntrada Indice que recorre las columnas de la matriz.
     */
    private float obtenerCofactor(float[][] matrizEntrada, int indiceFilaMatrizEntrada, int indiceColumnaMatrizEntrada) {
        float[][] matriz;
        matriz = matrizEntrada;
        int indiceFilaMatriz;
        indiceFilaMatriz = indiceFilaMatrizEntrada;
        int indiceColumnaMatriz;
        indiceColumnaMatriz = indiceColumnaMatrizEntrada;
        int tamanioMatriz;
        tamanioMatriz = matriz.length;
        int tamanioSubmatriz;
        tamanioSubmatriz = matriz.length - 1;
        float submatriz[][];
        submatriz = new float[tamanioSubmatriz][tamanioSubmatriz];

        int contadorColumnas;
        contadorColumnas = 0;
        int contadorFilas;
        contadorFilas = 0;
        int indiceFilas;
        for (indiceFilas = 0; indiceFilas < tamanioMatriz; indiceFilas++) {
            int indiceColumnas;
            for (indiceColumnas = 0; indiceColumnas < tamanioMatriz; indiceColumnas++) {
                if (indiceFilas != indiceFilaMatriz && indiceColumnas != indiceColumnaMatriz) {
                    submatriz[contadorFilas][contadorColumnas] = matriz[indiceFilas][indiceColumnas];
                    contadorColumnas++;
                    if (contadorColumnas >= tamanioSubmatriz) {
                        contadorFilas++;
                        contadorColumnas = 0;
                    }
                }
            }
        }

        float cofactor;
        cofactor = (float) Math.pow(-1.0, indiceFilaMatriz + indiceColumnaMatriz) * reglaDeSarrus(submatriz);

        return cofactor;
    }

    /**
     * Determina el determinante de una matriz.
     * @param matrizEntrada Matriz de la cual se sacará el determinante.
     * @return Retorna el determinante de la matriz.
     */
    private float reglaDeSarrus(float[][] matrizEntrada) {

        float[][] matriz;
        matriz = matrizEntrada;

        float determinante;
        determinante = 0;

        if (matriz.length == 1) {
            return matriz[0][0];

        } else {
            for (int indiceColumnas = 0; indiceColumnas < matriz.length; indiceColumnas++) {
                determinante = determinante + matriz[PRIMERA_POSICION][indiceColumnas] * obtenerCofactor(matriz, PRIMERA_POSICION, indiceColumnas);
            }
        }

        return determinante;
    }

    /**
     * Determina si la matriz uno y la matriz dos son del mismo tamaño.
     * @return Retorna verdadero o falso.
     */
    public boolean determinarIgualdadTamaño() {
        return getNumFilasMatrizUno() == getNumFilasMatrizDos() && getNumColumnasMatrizUno() == getNumColumnasMatrizDos();
    }
    
    /**
     * Determina si una matriz tiene el mismo numero de filas que de columnas.
     * @param matrizEntrada Matriz la cual será verificada.
     * @return Retorna verdadero o falso.
     */
    public boolean esMatrizCuadrada(float[][] matrizEntrada) {
        float[][] matriz;
        matriz = matrizEntrada;
        return matriz.length == matriz[PRIMERA_POSICION].length;
    }
    
    /**
     * Deterina si la cantidad de columnas de la matriz uno es igual a la cantidad de filas de la matriz dos.
     * @return Retorna verdadero o falso.
     */
    public boolean determinarDimensionComun() {
        return getNumColumnasMatrizUno() == getNumFilasMatrizDos();
    }

    //Getters y Setters

    /**
     * Asigna la matriz recibida a la matriz uno
     * @param matrizUno La matriz que será asignada
     */
    public void setMatrizUno(float[][] matrizUno) {
        this.matrizUno = matrizUno;
    }

    /**
     * Devuelve la matriz uno
     * @return La matriz uno que será devuelta
     */
    public float[][] getMatrizUno() {
        return matrizUno;
    }

    /**
     * Asigna la matriz recibida a la matriz dos
     * @param matrizDos La matriz que será asignada
     */
    public void setMatrizDos(float[][] matrizDos) {
        this.matrizDos = matrizDos;
    }

    /**
     * Devuelve la matriz dos
     * @return La matriz dos que será devuelta
     */
    public float[][] getMatrizDos() {
        return matrizDos;
    }

    /**
     * Asigna la matriz recibida a la matriz resultante
     * @param matrizResultante La matriz que será asignada
     */
    public void setMatrizResultante(float[][] matrizResultante) {
        this.matrizResultante = matrizResultante;
    }

    /**
     * Devuelve la matriz resultante.
     * @return La matriz resultante que será devuelta
     */
    public float[][] getMatrizResultante() {
        return matrizResultante;
    }

    /**
     * Devuelve el número de filas de la matriz uno.
     * @return El número de filas de la matriz uno que será devuelta
     */
    public int getNumFilasMatrizUno() {
        return matrizUno.length;
    }

    /**
     * Devuelve el número de columnas de la matriz uno.
     * @return El número de colunmas de la matriz uno que será devuelta
     */
    public int getNumColumnasMatrizUno() {
        return matrizUno[PRIMERA_POSICION].length;
    }

    /**
     * Devuelve el número de filas de la matriz dos.
     * @return El número de filas de la matriz dos que será devuelta
     */
    public int getNumFilasMatrizDos() {
        return matrizDos.length;
    }

    /**
     * Devuelve El número de columnas de la matriz dos.
     * @return El número de columnas de la matriz dos que será devuelta
     */
    public int getNumColumnasMatrizDos() {
        return matrizDos[PRIMERA_POSICION].length;
    }

}
