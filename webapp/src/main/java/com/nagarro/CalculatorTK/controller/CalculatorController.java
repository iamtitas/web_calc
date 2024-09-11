package com.nagarro.CalculatorTK.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;

@Controller
public class CalculatorController {
	
	private final Counter calculationsCounter;
	
	public CalculatorController() {
        this(new SimpleMeterRegistry());
    }

    public CalculatorController(MeterRegistry registry) {
        this.calculationsCounter = Counter.builder("calculations_total")
                .description("Total number of calculations performed")
                .register(registry);
    }

	@RequestMapping(value = "/", method = RequestMethod.GET)
    public String showCalculatorPage() {
        return "calculator";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ModelAndView calculate(@RequestParam("num1") int num1, @RequestParam("num2") int num2, @RequestParam("operator") String operator) {
        ModelAndView modelAndView = new ModelAndView("result");
        int result;
        switch (operator) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                result = num1 / num2;
                break;
            default:
                result = 0;
        }
        modelAndView.addObject("result", result);
        
        calculationsCounter.increment();
        return modelAndView;
    }
}