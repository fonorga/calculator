package com.ciber.calculator.service;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class Calculator {
    /* Antiguo
    public int sum(int a, int b) {
        return a + b;
    }
    */

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

}
