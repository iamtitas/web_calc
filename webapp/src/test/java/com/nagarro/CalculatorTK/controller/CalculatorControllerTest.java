package com.nagarro.CalculatorTK.controller;

import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorControllerTest {

    @Test
    public void testCalculate_Addition() {
        // Arrange
        CalculatorController controller = new CalculatorController();

        // Act
        ModelAndView result = controller.calculate(5, 3, "+");

        // Assert
        assertEquals(8, result.getModel().get("result"));
    }

    @Test
    public void testCalculate_Subtraction() {
        // Arrange
        CalculatorController controller = new CalculatorController();

        // Act
        ModelAndView result = controller.calculate(5, 3, "-");

        // Assert
        assertEquals(2, result.getModel().get("result"));
    }

    @Test
    public void testCalculate_Multiplication() {
        // Arrange
        CalculatorController controller = new CalculatorController();

        // Act
        ModelAndView result = controller.calculate(5, 3, "*");

        // Assert
        assertEquals(15, result.getModel().get("result"));
    }

    @Test
    public void testCalculate_Division() {
        // Arrange
        CalculatorController controller = new CalculatorController();

        // Act
        ModelAndView result = controller.calculate(6, 3, "/");

        // Assert
        assertEquals(2, result.getModel().get("result"));
    }
}
