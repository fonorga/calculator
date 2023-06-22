package com.ciber.calculator;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.ciber.calculator.service.Calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Arrays;

@SpringBootTest
public class CalculatorApplicationTests {

	private Calculator calculator = new Calculator();

	@Test
	void contextLoads() {
	}

	/* Antiguo
	@Test
	public void testSum() {
		assertEquals(5, calculator.sum(2, 3));
	}
	*/

	@Test
	public void testSumEvenNumbers() {
		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		assertEquals(30, calculator.sumEvenNumbers(list)); //El resultado esperado es 30
	}

	@Test
	public void testMultiplyOddNumbers() {
		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
		assertEquals(15, calculator.multiplyOddNumbers(list)); //El resultado esperado es 15
	}
}
