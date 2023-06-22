package com.ciber.calculator.controller;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ciber.calculator.service.Calculator;

@RestController
class CalculatorController {
    @Autowired
    private Calculator calculator;

    //Método para sumar los números pares de una lista
    @RequestMapping("/sumEvenNumbers")
    ResponseEntity<String> sumEvenNumbers(@RequestParam("numbers") @NotEmpty List<Integer> numList){
        int sum = calculator.sumEvenNumbers(numList); //Se llama al servicio. La lista se pasó como parámetro en la URL
        return ResponseEntity.ok("La suma de números pares es: " + sum); //Devuelve el código HTTP 200 OK
    }

    //Método para multiplicar los números impares de una lista
    @RequestMapping("/multiplyOddNumbers")
    ResponseEntity<String> multiplyOddNumbers(@RequestParam("numbers") @NotEmpty List<Integer> numList){
        int mult = calculator.multiplyOddNumbers(numList); //Se llama al servicio. La lista se pasó como parámetro en la URL
        return ResponseEntity.ok("El producto de los números impares es: " + mult); //Devuelve el código HTTP 200 OK
    }

    //Método para contar los números primos de una lista y devolver la lista marcando los que lo son con un asterisco
    @RequestMapping("/primeNumbers")
    ResponseEntity<String> primeNumbers(@RequestParam("numbers") @NotEmpty List<Integer> numList){
        List<String> result = calculator.primeNumbers(numList); //Se llama al servicio. La lista se pasó como parámetro en la URL
        return ResponseEntity.ok("Lista de números (primos con asterisco): " + result); //Devuelve el código HTTP 200 OK
    }

    //Método para la validación de los parámetros
    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<String> handleValidationException(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        
        // Construir un mensaje de error con los detalles de validación
        StringBuilder errorMessage = new StringBuilder();
        for (FieldError fieldError : fieldErrors) {
            errorMessage.append(fieldError.getDefaultMessage()).append("; ");
        }

        return ResponseEntity.badRequest().body("Error de validación"); //Devuelve el código HTTP 400 Bad Request 
    }
}