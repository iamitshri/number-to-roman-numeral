package com.practice.service;

import com.practice.dto.RomanNumeralDto;
import com.practice.exception.InvalidInputException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
@Primary
@Qualifier("optimized")
public class SimplifiedIntegerToRomanNumeralService implements IntegerToRoman {

    private static final String MESSAGE = "A valid integer in the range of [1-3999] is required";

    String[] thousands = {"", "M", "MM", "MMM"};
    String[] hundreds = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
    String[] tens = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
    String[] units = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};

    /**
     * <h1>  Time Complexity is O(log10(n)) We are looking at each digit of number  </h1>
     * <h1> Space Complexity O(1) since our defined arrays do not grow with size of the input
     * if we dont count the string builder used for creating output </h1>
     *
     * @param input Integer
     * @return RomanNumeralDto Object that has the conversion from Integer to Roman Numeral
     */
    @Override
    public RomanNumeralDto convert(Integer input) {

        Assert.notNull(input, MESSAGE);

        if (input < 1 || input > 3999) {
            throw new InvalidInputException(MESSAGE);
        }

        int num = input;
        int thousandthsPlace = num / 1000; // e.g. 1234/1000=1
        int hundredthsPlace = (num % 1000) / 100; // e.g. (1234%1000)/100=234/100=2
        int tenthsPlace = (num % 100) / 10;//e.g. (1234%100)/10=34/10=3
        int unitPlace = num % 10; // e.g. 1234%10=4

        StringBuilder sb = new StringBuilder();
        sb.append(thousands[thousandthsPlace]);
        sb.append(hundreds[hundredthsPlace]);
        sb.append(tens[tenthsPlace]);
        sb.append(units[unitPlace]);
        String romanNumeral = sb.toString();
        return new RomanNumeralDto(input, romanNumeral);
    }
}
