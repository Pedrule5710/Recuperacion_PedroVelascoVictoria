package com.pedro.calculadora.CalculadoraTest;


import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.pedro.calculator.controlador.CalculatorLogic;

class CalculadoraTest {

    @Test
    @DisplayName("Factorial de 5 debe ser 120")
    void testFactorialSimple() {
        // Probamos el método que extrajimos en la UT3
        assertEquals(120, CalculatorLogic.calcularFactorial(5));
    }

    @ParameterizedTest(name = "El factorial de {0} debe ser {1}")
    @CsvSource({
        "0, 1",
        "1, 1",
        "2, 2",
        "3, 6",
        "4, 24"
    })
    void testFactorialParametrizado(double entrada, int resultadoEsperado) {
        assertEquals(resultadoEsperado, CalculatorLogic.calcularFactorial(entrada));
    }
}