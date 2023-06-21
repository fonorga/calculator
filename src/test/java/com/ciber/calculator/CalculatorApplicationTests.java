package com.ciber.calculator;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.ciber.calculator.service.Calculator;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class CalculatorApplicationTests {

	private Calculator calculator = new Calculator();

	@Test
	void contextLoads() {
	}

	@Test
	public void testSum() {
		assertEquals(5, calculator.sum(2, 3));
	}
}
