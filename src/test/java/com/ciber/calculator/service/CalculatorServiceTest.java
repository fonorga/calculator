package com.ciber.calculator.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ciber.calculator.data.PrimeNumbersResult;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
public class CalculatorServiceTest {
    
    private final CalculatorService calculatorService = new CalculatorService();

    @Test
	public void testSumEvenNumbers() {
		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		assertEquals(30, calculatorService.sumEvenNumbers(list));
	}

	@Test
	public void testMultiplyOddNumbers() {
		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
		assertEquals(15, calculatorService.multiplyOddNumbers(list));
	}

	@Test
	public void testPrimeNumbers() throws Exception {
		List<Integer> list = Arrays.asList(2, 25, 37, 42, 53, 68, 75, 89, 97, 100);

        PrimeNumbersResult result = calculatorService.getPrimeNumbers(list);

		assertEquals(5, result.getCount());
        assertEquals(5, result.getPrimeNumbers().size());
        assertEquals(2, result.getPrimeNumbers().get(0));
        assertEquals(37, result.getPrimeNumbers().get(1));
        assertEquals(53, result.getPrimeNumbers().get(2));
        assertEquals(89, result.getPrimeNumbers().get(3));
        assertEquals(97, result.getPrimeNumbers().get(4));

        assertEquals("{\"primeNumbersCount\":5,\"primeNumbersList\":[2,37,53,89,97]}", new ObjectMapper().writeValueAsString(result));
	}
}
