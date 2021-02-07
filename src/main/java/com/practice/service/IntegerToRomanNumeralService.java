package com.practice.service;

import com.practice.dto.RomanNumeralDto;
import com.practice.exception.InvalidInputException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
@Qualifier("regular")
public class IntegerToRomanNumeralService implements IntegerToRoman {

    private static final String MESSAGE = "A valid integer in the range of [1-3999] is required";

    /**
     * <h1>  Time Complexity is O(log10(n)) We are looking at each digit of number  </h1>
     * <h1> Space Complexity O(1) if we dont count the string builder used for creating output </h1>
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

        StringBuilder sb = new StringBuilder();
        int divideBy = getDivisor(input);
        int num = input;
        while (num > 0) {
            if (num >= 1000) {
                int n = num / divideBy;
                sb.append("M".repeat(n));

            } else if (num >= 500) {
                int n = num / divideBy;
                if (num >= 900) {
                    sb.append("CM");
                } else {
                    sb.append("D");
                    sb.append("C".repeat(n - 5));
                }

            } else if (num >= 100) {
                int n = num / divideBy;
                if (num >= 400) {
                    sb.append("CD");
                } else {
                    sb.append("C".repeat(n));
                }

            } else if (num >= 50) {
                int n = num / divideBy;
                if (num >= 90) {
                    sb.append("XC");
                } else {
                    sb.append("L");
                    sb.append("X".repeat(n - 5));
                }

            } else if (num >= 10) {
                int n = num / divideBy;
                if (num >= 40) {
                    sb.append("XL");
                } else {
                    sb.append("X".repeat(n));
                }

            } else if (num >= 5) {
                if (num >= 9) {
                    sb.append("IX");
                } else {
                    sb.append("V");
                    sb.append("I".repeat(num - 5));
                }

            } else if (num >= 4) {
                sb.append("IV");
                num = 0;
            } else {
                sb.append("I".repeat(num));
                num = 0;
            }
            num = num % divideBy;
            divideBy = getDivisor(num);
        }
        String romanNumeral = sb.toString();
        return new RomanNumeralDto(input, romanNumeral);
    }

    int getDivisor(int num) {
        int ceil = (int) Math.floor(Math.log10(num));
        return (int) Math.pow(10, ceil);
    }


}
