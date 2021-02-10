package com.practice.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.dto.RomanNumeralDto;
import com.practice.exception.InvalidInputException;
import com.practice.exception.InvalidRangeException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test the number to roman conversion for range of input
 */
public class RomanNumeralsInRangeServiceTest {

    IntegerToRomanNumeralService integerToRomanNumeralService = new IntegerToRomanNumeralService();
    RomanNumeralsInRangeService romanNumeralsInRangeService = new RomanNumeralsInRangeService(
        integerToRomanNumeralService);

    @Test
    public void getRomanNumeralsInRange() throws JsonProcessingException {
        // arrange
        List<RomanNumeralDto> lst = new ArrayList<>();
        int min = 1;
        int max = 3999;
        for (int i = min; i <= max; i++) {
            lst.add(integerToRomanNumeralService.convert(i));
        }

        // act
        List<RomanNumeralDto> romanNumeralsInRange = romanNumeralsInRangeService.getRomanNumeralsInRange(min, max);

        // assert
        ObjectMapper mapper = new ObjectMapper();
        String expected = mapper.writeValueAsString(lst);
        String actual = mapper.writeValueAsString(romanNumeralsInRange);
        assertThat(actual).isEqualTo(expected);
    }

    /**
     * Test SimplifiedIntegerToRomanNumeralService implementation for getting range
     * @throws JsonProcessingException exception in json processing
     */
    @Test
    public void getRomanNumeralsInRangeUsingAlternateImplementation() throws JsonProcessingException {

        // arrange
        SimplifiedIntegerToRomanNumeralService service = new SimplifiedIntegerToRomanNumeralService();
        RomanNumeralsInRangeService rangeService = new RomanNumeralsInRangeService(service);
        List<RomanNumeralDto> lst = new ArrayList<>();
        int min = 1;
        int max = 3999;
        for (int i = min; i <= max; i++) {
            lst.add(integerToRomanNumeralService.convert(i));
        }

        // act
        List<RomanNumeralDto> romanNumeralsInRange = rangeService.getRomanNumeralsInRange(min, max);

        // assert
        ObjectMapper mapper = new ObjectMapper();
        String expected = mapper.writeValueAsString(lst);
        String actual = mapper.writeValueAsString(romanNumeralsInRange);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void getRomanNumeralsInvalidRange() {
        // arrange
        int min = 12;
        int max = 10;
        // act assert
        Assert.assertThrows(InvalidRangeException.class,
                            () -> romanNumeralsInRangeService.getRomanNumeralsInRange(min, max));
    }

    @Test
    public void getRomanNumeralsInvalidMinOrMax() {
        // arrange
        int min = 0;
        int max = 10;
        // act assert
        Assert.assertThrows(InvalidInputException.class,
                            () -> romanNumeralsInRangeService.getRomanNumeralsInRange(min, max));

        // arrange
        int min2 = 10;
        int max2 = 0;
        // act assert
        Assert.assertThrows(InvalidInputException.class,
                            () -> romanNumeralsInRangeService.getRomanNumeralsInRange(min2, max2));
    }
}