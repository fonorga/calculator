package com.ciber.calculator.controller;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ciber.calculator.data.PrimeNumbersResult;
import com.ciber.calculator.service.CalculatorService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/calculator")
@Api(tags = "Calculator API", description = "Includes several methods to perform operations with lists of numbers")
class CalculatorController {

    private static final Log LOGGER = LogFactory.getLog("CalculatorController");
    
    private final CalculatorService calculatorService;

    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    } 

    /**
     * Method that sums the even numbers of a list
     * @param numList List of numbers
     * @return The sum of even numbers
     */
    @PostMapping("/sumEvenNumbers")
    @ApiOperation(value = "Even numbers sum", response = Integer.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 400, message = "Bad Request")
    })
    ResponseEntity<Integer> sumEvenNumbers(@ApiParam(value = "List of numbers", required = true) @RequestParam("numbers") @NotEmpty List<Integer> numList){
        LOGGER.debug("Received numbers: " + numList);

        int result = calculatorService.sumEvenNumbers(numList);

        LOGGER.debug("Even numbers sum: " + result);

        return ResponseEntity.ok(result);
    }
    
    /**
     * Method that multiplies the odd numbers of a lists
     * @param numList List of numbers
     * @return The multiply of odd numbers
     */
    @PostMapping("/multiplyOddNumbers")
    @ApiOperation(value = "Multiply odd numbers", response = Integer.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 400, message = "Bad Request")
    })
    ResponseEntity<Integer> multiplyOddNumbers(@ApiParam(value = "List of numbers", required = true) @RequestParam("numbers") @NotEmpty List<Integer> numList){
        LOGGER.debug("Received numbers: " + numList);

        int result = calculatorService.multiplyOddNumbers(numList);
        
        LOGGER.debug("Odd numbers multiplication: " + result);

        return ResponseEntity.ok(result);
    }
    
    /**
     * Method to count the prime numbers in a list and return it, leaving only those that are prime
     * @param numList List of numbers
     * @return A PrimeNumberResult object, including the count of the prime numbers and a list with them
     */
    @GetMapping(value = "/getPrimeNumbers", produces = "application/json")
    @ApiOperation(value = "Identify prime numbers", response = PrimeNumbersResult.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 400, message = "Bad Request")
    })
    ResponseEntity<PrimeNumbersResult> getPrimeNumbers(@ApiParam(value = "List of numbers", required = true) @RequestParam("numbers") @NotEmpty List<Integer> numList){
        LOGGER.debug("Received numbers: " + numList);

        PrimeNumbersResult result = calculatorService.getPrimeNumbers(numList);

        LOGGER.debug("Prime numbers count: " + result.getCount());
        LOGGER.debug("Prime numbers list: " + result.getPrimeNumbers());
        
        return ResponseEntity.ok(result);
    }
}