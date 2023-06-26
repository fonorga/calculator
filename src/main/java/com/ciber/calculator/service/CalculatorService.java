package com.ciber.calculator.service;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.ciber.calculator.data.PrimeNumbersResult;

@Service
public class CalculatorService {
    private static final Log LOGGER = LogFactory.getLog("CalculatorService");

    /**
     * Method that sums the even numbers of a list
     * @param numList List of numbers
     * @return The sum of even numbers
     */
    public int sumEvenNumbers(List<Integer> numList) {
        LOGGER.debug("Received numbers: " + numList);

        return numList.stream()
                .filter(num -> num % 2 == 0) //Filtramos los valores pares
                .reduce(0, (a, b) -> Integer.sum(a, b)); //Sumamos los elementos filtrados
    }

    /**
     * Method that multiplies the odd numbers of a lists
     * @param numList List of numbers
     * @return The multiply of odd numbers
     */
    public int multiplyOddNumbers(List<Integer> numList) {
        LOGGER.debug("Received numbers: " + numList);

        return numList.stream()
                .filter(num -> num % 2 != 0) //Filtramos los valores impares
                .reduce(1, (a, b) -> a * b); //Multiplicamos los números filtrados
    }

    /**
     * Method to count the prime numbers in a list and return it, leaving only those that are prime
     * @param numList List of numbers
     * @return A PrimeNumberResult object, including the count of the prime numbers and a list with them
     */
    public PrimeNumbersResult getPrimeNumbers(List<Integer> numList){
        LOGGER.debug("Received numbers: " + numList);

        List<Integer> primeNumbers = numList.stream()
                .filter(n -> isPrime(n))
                .collect(Collectors.toList());

        PrimeNumbersResult primeNumbersResult = new PrimeNumbersResult();
        primeNumbersResult.setCount(primeNumbers.size());
        primeNumbersResult.setPrimeNumbers(primeNumbers);

        return primeNumbersResult;
    }

    /**
     * Auxiliar method to know if a number is prime or not
     * @param numList List of numbers
     * @return True or False
     */
    private boolean isPrime(int num) {
        if (num < 2) {
            return false;
        }

        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }

        return true;
    }
}
