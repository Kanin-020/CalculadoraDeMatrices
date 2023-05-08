/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Calculadora;
import view.CalculadoraVista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTable;
import javax.swing.JOptionPane;

/**
 * Clase intermediara entre la vista y la lógica.
 * Se encarga de controlar los elementos de la vista.
 */
public class Controlador implements ActionListener {

    private CalculadoraVista calculadoraVista;
    private Calculadora calculadora;
    
    public Controlador(CalculadoraVista calculadoraVista, Calculadora calculadora) {
        this.calculadoraVista = calculadoraVista;
        this.calculadora = calculadora;

        this.calculadoraVista.getBotonSuma().addActionListener(this);
        this.calculadoraVista.getBotonMultiplicacionEscalar().addActionListener(this);
        this.calculadoraVista.getBotonMultiplicacion().addActionListener(this);
        this.calculadoraVista.getBotonTranspuesta().addActionListener(this);
        this.calculadoraVista.getBotonInversa().addActionListener(this);
        this.calculadoraVista.getBotonSistema().addActionListener(this);
        this.calculadoraVista.getBotonDeterminante().addActionListener(this);
        this.calculadoraVista.getBotonLimpiar().addActionListener(this);
        this.calculadoraVista.getBotonSistemaCramer().addActionListener(this);

    }

    /**
     * Se encarga de definir las acciones de los botones dentro de la vista.
     * @param evento Variable de tipo ActionEvent, define que una acción ha ocurrido. 
    */
    public void actionPerformed(ActionEvent evento) {

        if (calculadoraVista.getBotonSuma() == evento.getSource()) {

            if (sonMatricesValidas(calculadoraVista.getMatrizUno(), calculadoraVista.getMatrizDos())) {
                calculadora.setMatrizUno(asignarValoresMatriz(calculadoraVista.getMatrizUno()));
                calculadora.setMatrizDos(asignarValoresMatriz(calculadoraVista.getMatrizDos()));
                if (calculadora.determinarIgualdadTamaño()) {
                    limpiarMatrizResultante();
                    calculadora.sumarMatrices();
                    mostrarMatrizResultante(calculadora.getMatrizResultante());
                } else {
                    JOptionPane.showMessageDialog(null, "Las matrices no son iguales", "Error",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        }
        if (calculadoraVista.getBotonMultiplicacionEscalar() == evento.getSource()) {

            if (esMatrizValida(calculadoraVista.getMatrizUno())) {
                limpiarMatrizResultante();
                calculadora.setMatrizUno(asignarValoresMatriz(calculadoraVista.getMatrizUno()));
                try {
                    calculadora.multiplicarPorEscalar(
                            Float.parseFloat(calculadoraVista.getTextEscalar().getText()));
                    mostrarMatrizResultante(calculadora.getMatrizResultante());
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Entrada escalar invalida", "Error", JOptionPane.WARNING_MESSAGE);
                }

            } else {
                JOptionPane.showMessageDialog(null, "Matriz Uno invalida", "Error", JOptionPane.WARNING_MESSAGE);
            }

        }
        if (calculadoraVista.getBotonMultiplicacion() == evento.getSource()) {

            if (sonMatricesValidas(calculadoraVista.getMatrizUno(), calculadoraVista.getMatrizDos())) {
                limpiarMatrizResultante();
                calculadora.setMatrizUno(asignarValoresMatriz(calculadoraVista.getMatrizUno()));
                calculadora.setMatrizDos(asignarValoresMatriz(calculadoraVista.getMatrizDos()));
                if (calculadora.determinarDimensionComun()) {
                    calculadora.multiplicarMatrices();
                    mostrarMatrizResultante(calculadora.getMatrizResultante());
                } else {
                    JOptionPane.showMessageDialog(null, "El número de columnas de la Matriz Uno debe ser igual al número de filas de la Matriz Dos", "Error", JOptionPane.WARNING_MESSAGE);
                }
            }
        }
        if (calculadoraVista.getBotonTranspuesta() == evento.getSource()) {

            if (esMatrizValida(calculadoraVista.getMatrizUno())) {
                limpiarMatrizResultante();
                calculadora.setMatrizUno(asignarValoresMatriz(calculadoraVista.getMatrizUno()));
                if (calculadora.esMatrizCuadrada(calculadora.getMatrizUno())) {
                    calculadora.obtenerTranspuesta();
                    mostrarMatrizResultante(calculadora.getMatrizResultante());
                } else {
                    JOptionPane.showMessageDialog(null, "Matriz Uno no es cuadrada", "Error", JOptionPane.WARNING_MESSAGE);
                }

            } else {
                JOptionPane.showMessageDialog(null, "Matriz Uno invalida", "Error", JOptionPane.WARNING_MESSAGE);
            }
        }
        if (calculadoraVista.getBotonInversa() == evento.getSource()) {

            if (esMatrizValida(calculadoraVista.getMatrizUno())) {
                limpiarMatrizResultante();
                calculadora.setMatrizUno(asignarValoresMatriz(calculadoraVista.getMatrizUno()));
                if (calculadora.esMatrizCuadrada(calculadora.getMatrizUno())) {
                    calculadora.obtenerInversa();
                    mostrarMatrizResultante(calculadora.getMatrizResultante());
                } else {
                    JOptionPane.showMessageDialog(null, "Matriz Uno no es cuadrada", "Error", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Matriz Uno invalida", "Error", JOptionPane.WARNING_MESSAGE);
            }
        }
        if (calculadoraVista.getBotonSistema() == evento.getSource()) {

            if (sonMatricesValidas(calculadoraVista.getMatrizUno(), calculadoraVista.getMatrizDos())) {
                limpiarMatrizResultante();
                calculadora.setMatrizUno(asignarValoresMatriz(calculadoraVista.getMatrizUno()));
                calculadora.setMatrizDos(asignarValoresMatriz(calculadoraVista.getMatrizDos()));
                if (calculadora.esMatrizCuadrada(calculadora.getMatrizUno())) {
                    calculadora.resolverSistemaEcuacionesGaussJordan();
                    mostrarMatrizResultante(calculadora.getMatrizResultante());
                } else {
                    JOptionPane.showMessageDialog(null, "La cantidad de X´s debe ser igual a la de ecuaciones", "Error", JOptionPane.WARNING_MESSAGE);
                }
            }
        }
        if (calculadoraVista.getBotonSistemaCramer() == evento.getSource()) {

            if (sonMatricesValidas(calculadoraVista.getMatrizUno(), calculadoraVista.getMatrizDos())) {
                limpiarMatrizResultante();
                calculadora.setMatrizUno(asignarValoresMatriz(calculadoraVista.getMatrizUno()));
                calculadora.setMatrizDos(asignarValoresMatriz(calculadoraVista.getMatrizDos()));
                if (calculadora.esMatrizCuadrada(calculadora.getMatrizUno())) {
                    calculadora.resolverSistemaEcuacionesCramer();
                    mostrarMatrizResultante(calculadora.getMatrizResultante());
                } else {
                    JOptionPane.showMessageDialog(null, "La cantidad de X´s debe ser igual a la de ecuaciones", "Error", JOptionPane.WARNING_MESSAGE);
                }
            }
        }
        if (calculadoraVista.getBotonDeterminante() == evento.getSource()) {

            if (esMatrizValida(calculadoraVista.getMatrizUno())) {
                limpiarMatrizResultante();
                calculadora.setMatrizUno(asignarValoresMatriz(calculadoraVista.getMatrizUno()));
                if (calculadora.esMatrizCuadrada(calculadora.getMatrizUno())) {
                    calculadora.obtenerDeterminante();
                    mostrarMatrizResultante(calculadora.getMatrizResultante());
                } else {
                    JOptionPane.showMessageDialog(null, "Matriz Uno no es cuadrada", "Error", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Matriz Uno invalida", "Error", JOptionPane.WARNING_MESSAGE);
            }
        }
        if (calculadoraVista.getBotonLimpiar() == evento.getSource()) {
            limpiarMatrices();
            limpiarMatrizResultante();
        }

    }
/**
 * Se encarga de determinar si una matriz es valida para operar dentro de la vista.
 * @param tablaMatrizEntrada Matriz de entrada dentro de la vista.
 * @return Variable de tipo boolean, devuelve verdadero o falso.
 */
    private boolean esMatrizValida(JTable tablaMatrizEntrada) {
        JTable tablaMatriz;
        tablaMatriz = tablaMatrizEntrada;
        int numFilas;
        numFilas = 0;
        int numColumnas;
        numColumnas = 0;

        int indiceUno;
        for (indiceUno = 0; indiceUno < tablaMatriz.getRowCount(); indiceUno++) {
            int numColumnasPorFila;
            numColumnasPorFila = 0;
            int numFilasPorColumna;
            numFilasPorColumna = 0;
            int indiceDos;
            for (indiceDos = 0; indiceDos < tablaMatriz.getColumnCount(); indiceDos++) {
                if (tablaMatriz.getValueAt(indiceUno, indiceDos) != null) {
                    numColumnasPorFila = indiceDos + 1;
                }
                if (tablaMatriz.getValueAt(indiceDos, indiceUno) != null) {
                    numFilasPorColumna = indiceDos + 1;
                }
            }
            if (numColumnasPorFila > numColumnas) {
                numColumnas = numColumnasPorFila;
            }
            if (numFilasPorColumna > numFilas) {
                numFilas = numFilasPorColumna;
            }
        }

        if (numFilas != 0 && numColumnas != 0) {
            int indiceFilas;
            for (indiceFilas = 0; indiceFilas < numFilas; indiceFilas++) {
                int indiceColumnas;
                for (indiceColumnas = 0; indiceColumnas < numColumnas; indiceColumnas++) {
                    if (tablaMatriz.getValueAt(indiceFilas, indiceColumnas) == null) {
                        return false;
                    }
                }
            }
        } else {
            return false;
        }
        return true;
    }

/**
 * Determina si 2 matrices son validas para operar dentro de la vista.
 * @param matrizUnoEntrada Primera matriz de entrada dentro de la vista.
 * @param matrizDosEntrada Segunda matriz de entrada dentro de la vista.
 * @return Variable de tipo boolean, devuelve verdadero o falso.
 */
    private boolean sonMatricesValidas(JTable matrizUnoEntrada, JTable matrizDosEntrada) {
        boolean sonValidas;
        sonValidas = true;
        JTable matrizUno;
        matrizUno = matrizUnoEntrada;
        JTable matrizDos;
        matrizDos = matrizDosEntrada;
        if (!esMatrizValida(matrizUno)) {
            JOptionPane.showMessageDialog(null, "Matriz Uno invalida", "Error", JOptionPane.WARNING_MESSAGE);
            sonValidas = false;
        }
        if (!esMatrizValida(matrizDos)) {
            JOptionPane.showMessageDialog(null, "Matriz Dos invalida", "Error", JOptionPane.WARNING_MESSAGE);
            sonValidas = false;
        }
        return sonValidas;
    }

    /**
     * Se encarga de obtener los valores de la matriz dentro de la vista, y mandarlos a la lógica de la aplicación.
     * @param tablaMatrizEntrada Matriz que será recibida dentro de la vista.
     * @return Devuelve una matriz de valores enteros.
     */
    private float[][] asignarValoresMatriz(JTable tablaMatrizEntrada) {
        JTable tablaMatriz;
        tablaMatriz = tablaMatrizEntrada;
        int numFilas;
        numFilas = 0;
        int numColumnas;
        numColumnas = 0;

        int i;
        for (i = 0; i < tablaMatriz.getRowCount(); i++) {
            if (tablaMatriz.getValueAt(i, 0) != null) {
                numFilas = i + 1;
            }
            if (tablaMatriz.getValueAt(0, i) != null) {
                numColumnas = i + 1;
            }
        }

        float[][] matriz = new float[numFilas][numColumnas];

        int indiceFilas;
        for (indiceFilas = 0; indiceFilas < numFilas; indiceFilas++) {
            int indiceColumnas;
            for (indiceColumnas = 0; indiceColumnas < numColumnas; indiceColumnas++) {
                matriz[indiceFilas][indiceColumnas] = (float) tablaMatriz.getValueAt(indiceFilas, indiceColumnas);
            }
        }
        return matriz;
    }

    /**
     * Obtiene la matriz generada en la lógica y la convierte en una tabla para la salida en la vista.
     * @param matrizResultanteEntrada Matriz de valores flotantes utilizada en la lógica de la aplicación.
     */
    private void mostrarMatrizResultante(float[][] matrizResultanteEntrada) {

        float[][] matrizResultante;
        matrizResultante = matrizResultanteEntrada;

        int indiceFilas;
        for (indiceFilas = 0; indiceFilas < matrizResultante.length; indiceFilas++) {
            int indiceColumnas;
            for (indiceColumnas = 0; indiceColumnas < matrizResultante[0].length; indiceColumnas++) {
                calculadoraVista.getMatrizResultante().setValueAt(matrizResultante[indiceFilas][indiceColumnas],
                        indiceFilas, indiceColumnas);
            }
        }
    }

    /**
     * Se encarga de borrar los valores mostrados en la matriz resultante de la vista.
     */
    private void limpiarMatrizResultante() {

        int indiceFilas;
        for (indiceFilas = 0; indiceFilas < calculadoraVista.getMatrizResultante().getRowCount(); indiceFilas++) {
            int indiceColumnas;
            for (indiceColumnas = 0; indiceColumnas < calculadoraVista.getMatrizResultante().getColumnCount(); indiceColumnas++) {
                calculadoraVista.getMatrizResultante().setValueAt(null,
                        indiceFilas, indiceColumnas);
            }
        }
    }

    /**
     * Se encarga de borrar los valores mostrados en las matrices de entrada en la vista-
     */
    private void limpiarMatrices() {
        int indiceFilas;
        for (indiceFilas = 0; indiceFilas < calculadoraVista.getMatrizUno().getRowCount(); indiceFilas++) {
            int indiceColumnas;
            for (indiceColumnas = 0; indiceColumnas < calculadoraVista.getMatrizUno().getColumnCount(); indiceColumnas++) {
                calculadoraVista.getMatrizUno().setValueAt(null, indiceFilas, indiceColumnas);
                calculadoraVista.getMatrizDos().setValueAt(null, indiceFilas, indiceColumnas);
            }
        }
    }
    
}
