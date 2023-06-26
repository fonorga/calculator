package com.ciber.calculator.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.ciber.calculator.data.PrimeNumbersResult;
import com.ciber.calculator.exception.ExceptionManager;
import com.ciber.calculator.service.CalculatorService;

@ExtendWith(MockitoExtension.class)
public class CalculatorControllerTest {
    
    @Mock
    private CalculatorService calculatorService;

    @InjectMocks
    private CalculatorController calculatorController;
    
	private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders
            .standaloneSetup(calculatorController)
            .setControllerAdvice(new ExceptionManager())
            .build();
    }    

    @Test
    void sumEvenNumbersTest() throws Exception {
        when(calculatorService.sumEvenNumbers(any())).thenReturn(30);

        mockMvc.perform(MockMvcRequestBuilders.post("/calculator/sumEvenNumbers")
                .contentType(MediaType.APPLICATION_JSON)
                .param("numbers", "1,2,3,4,5,6,7,8,9,10"))
				.andExpect(status().isOk())
                .andExpect(content().string("30"));
    }

    @Test
    void sumEvenNumbers_WhenNoListProvided_Test() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/calculator/sumEvenNumbers")
                .contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
                .andExpect(content().string("The required parameter 'numbers' is not present in the request."));
    }

    @Test
    void sumEvenNumbers_WhenWrongListProvided_Test() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/calculator/sumEvenNumbers")
                .contentType(MediaType.APPLICATION_JSON)
                .param("numbers","1,2,3,4,invalid,5,6,7,8,9,10"))
				.andExpect(status().isBadRequest())
                .andExpect(content().string("The type of the 'numbers' parameter is not valid for this request."));
    }

    @Test
    void multiplyOddNumbersTest() throws Exception {
        when(calculatorService.multiplyOddNumbers(any())).thenReturn(15);

        mockMvc.perform(MockMvcRequestBuilders.post("/calculator/multiplyOddNumbers")
                .contentType(MediaType.APPLICATION_JSON)
                .param("numbers", "1,2,3,4,5"))
				.andExpect(status().isOk())
                .andExpect(content().string("15"));
    }

    @Test
    void multiplyOddNumbers_WhenNoListProvided_Test() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/calculator/multiplyOddNumbers")
                .contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
                .andExpect(content().string("The required parameter 'numbers' is not present in the request."));
    }

    @Test
    void multiplyEvenNumbers_WhenWrongListProvided_Test() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/calculator/multiplyOddNumbers")
                .contentType(MediaType.APPLICATION_JSON)
                .param("numbers","1,2,3,4,invalid,5"))
				.andExpect(status().isBadRequest())
                .andExpect(content().string("The type of the 'numbers' parameter is not valid for this request."));
    }

    @Test
    void getPrimeNumbersTest() throws Exception {
        PrimeNumbersResult primeNumbersResult = new PrimeNumbersResult();
        primeNumbersResult.setCount(4);
        primeNumbersResult.setPrimeNumbers(List.of(1,3,5,7));
        when(calculatorService.getPrimeNumbers(any())).thenReturn(primeNumbersResult);

        mockMvc.perform(MockMvcRequestBuilders.get("/calculator/getPrimeNumbers")
                .param("numbers", "1,2,3,4,5,6,7,8,9,10"))
				.andExpect(status().isOk())
                .andExpect(content().string("{\"primeNumbersCount\":4,\"primeNumbersList\":[1,3,5,7]}"));
    }

    @Test
    void getPrimeNumbers_WhenNoListProvided_Test() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/calculator/getPrimeNumbers"))
				.andExpect(status().isBadRequest())
                .andExpect(content().string("The required parameter 'numbers' is not present in the request."));
    }

    @Test
    void getPrimeNumbers_WhenWrongListProvided_Test() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/calculator/getPrimeNumbers")
                .param("numbers", "1,2,3,4,5,6,7,8,9,10,invalid"))
				.andExpect(status().isBadRequest())
                .andExpect(content().string("The type of the 'numbers' parameter is not valid for this request."));
    }
}
