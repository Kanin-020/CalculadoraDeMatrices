/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import view.CalculadoraVista;
import controller.Controlador;
import model.Calculadora;

/**
 * Clase principal de la aplicación. Se encarga de correr el método main.
 */
public class Programa {

    /**
     * Se encarga de ejecutar toda la aplicación.
     * @param args Variable de tipo String
     * @throws java.lang.InterruptedException Tipo de excepción.
     */
    public static void main(String[] args) throws InterruptedException {
        Calculadora calculadora = new Calculadora();
        CalculadoraVista calculadoraVista = new CalculadoraVista();
        Controlador controlador = new Controlador(calculadoraVista, calculadora);

        calculadoraVista.setLocationRelativeTo(null);
        calculadoraVista.setVisible(true);

    }
}
