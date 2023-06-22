package com.ciber.calculator.service;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class Calculator {
    //Método para sumar los números pares de una lista
    public int sumEvenNumbers(List<Integer> numList) {
        return numList.stream()
                .filter(num -> num % 2 == 0) //Filtramos los valores pares
                .reduce(0, (a, b) -> Integer.sum(a, b)); //Sumamos los elementos filtrados
    }

    //Método para multiplicar los números impares de una lista
    public int multiplyOddNumbers(List<Integer> numList) {
        return numList.stream()
                .filter(num -> num % 2 != 0) //Filtramos los valores impares
                .reduce(1, (a, b) -> a * b); //Multiplicamos los números filtrados
    }

    //Método para contar los números primos de una lista y devolver la lista marcando los que lo son con un asterisco
    public String primeNumbers(List<Integer> numList){
        int primeCount = (int) numList.stream()
                .filter(this::isPrime)
                .count();

        List<String> transformedList = numList.stream()
                .map(num -> {
                    if (isPrime(num)) {
                        return num + "*";
                    } else {
                        return String.valueOf(num);
                    }
                })
                .collect(Collectors.toList());

        String result = "Número de primos: " + primeCount + "\nLista de números (primos con astersico): " + transformedList;

        return result;
    }

    //Método auxiliar para el cálculo de números primos
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
