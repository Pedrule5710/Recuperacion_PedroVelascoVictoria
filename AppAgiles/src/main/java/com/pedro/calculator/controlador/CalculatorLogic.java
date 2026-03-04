package com.pedro.calculator.controlador;

/**
 * Clase encargada de las operaciones matemáticas de la calculadora.
 * Cumple con el objetivo de desacoplamiento de la UT3.
 */
public class CalculatorLogic {

    /**
     * Calcula el factorial de un número natural.
     * @param n Número double (debe ser natural y no negativo).
     * @return El factorial como entero.
     */
    public static int calcularFactorial(double n) {
        if (n % 1 != 0) throw new RuntimeException("n is not natural");
        if (n < 0) throw new RuntimeException("n is negative");
        int r = 1;
        for (int i = 2; i <= n; i++) {
            r *= i;
        }
        return r;
    }

    /**
     * Calcula la potencia de una base.
     * @param b Base.
     * @param e Exponente (debe ser natural y no negativo).
     * @return Resultado de b elevado a e.
     */
    public static double calcularExponente(double b, double e) {
        if (e % 1 != 0 || e < 0) throw new RuntimeException("e is not natural");
        if (b == 0 && e == 0) throw new RuntimeException("0^0 is undefined");

        double r = b;
        for (int i = 1; i < e; i++) r *= b;
        return r;
    }

    /**
     * Calcula la circunferencia basada en un radio.
     * @param radio El radio actual.
     * @return Longitud de la circunferencia.
     */
    public static double calcularCircunferencia(double radio) {
        return 3.14159 * 2 * radio;
    }
}