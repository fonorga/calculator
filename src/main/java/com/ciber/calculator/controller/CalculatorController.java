package com.ciber.calculator.controller;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ciber.calculator.service.Calculator;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@RestController
@Api(tags = "Calculator API", description = "Incluye varios métodos para realizar operaciones con listas de números")
class CalculatorController {
    @Autowired
    private Calculator calculator;

    //Anotación personalizada para evitar repetir el código para cada método
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 400, message = "Solicitud inválida"),
        @ApiResponse(code = 500, message = "Error interno del servidor")
    })
    public @interface ApiResponsesForStringResponse {
    }

    //Método para sumar los números pares de una lista
    @PostMapping("/sumEvenNumbers")
    @ApiOperation(value = "Sumar números pares", response = ResponseEntity.class)
    @ApiResponsesForStringResponse
    ResponseEntity<String> sumEvenNumbers(@ApiParam(value = "Lista de números", required = true) @RequestParam("numbers") @NotEmpty List<Integer> numList){
        int sum = calculator.sumEvenNumbers(numList); //Se llama al servicio. La lista se pasó como parámetro en la URL
        return ResponseEntity.ok("La suma de números pares es: " + sum); //Devuelve el código HTTP 200 OK
    }
    
    //Método para multiplicar los números impares de una lista
    @PostMapping("/multiplyOddNumbers")
    @ApiOperation(value = "Multiplicar números impares", response = ResponseEntity.class)
    @ApiResponsesForStringResponse
    ResponseEntity<String> multiplyOddNumbers(@ApiParam(value = "Lista de números", required = true) @RequestParam("numbers") @NotEmpty List<Integer> numList){
        int mult = calculator.multiplyOddNumbers(numList); //Se llama al servicio. La lista se pasó como parámetro en la URL
        return ResponseEntity.ok("El producto de los números impares es: " + mult); //Devuelve el código HTTP 200 OK
    }
    
    //Método para contar los números primos de una lista y devolver la lista marcando los que lo son con un asterisco
    @PostMapping("/primeNumbers")
    @ApiOperation(value = "Identificar números primos", response = ResponseEntity.class)
    @ApiResponsesForStringResponse
    ResponseEntity<String> primeNumbers(@ApiParam(value = "Lista de números", required = true) @RequestParam("numbers") @NotEmpty List<Integer> numList){
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


    //Método para la configuración de Swagger
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ciber.calculator.controller"))
                .paths(PathSelectors.any())
                .build();
    }

}