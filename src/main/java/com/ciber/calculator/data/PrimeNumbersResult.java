package com.ciber.calculator.data;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PrimeNumbersResult {
    
    @JsonProperty("primeNumbersCount")
    private int count;

    @JsonProperty("primeNumbersList")
    private List<Integer> primeNumbers;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Integer> getPrimeNumbers() {
        return primeNumbers;
    }

    public void setPrimeNumbers(List<Integer> primeNumbers) {
        this.primeNumbers = primeNumbers;
    }
}
