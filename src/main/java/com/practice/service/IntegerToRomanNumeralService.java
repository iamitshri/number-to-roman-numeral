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
     * Refer to this link for  https://en.wikipedia.org/wiki/Roman_numerals subtractive notation is used to calculate
     * number such as 4 is represented as IV instead of IIII
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
        int divideBy = getDivisor(input); // To get the leftmost digit e.g. for 1234, we will
        int num = input;
        while (num > 0) {
            // if the number is in the range [1000-3999]
            if (num >= 1000) {
                int n = num / divideBy;
                sb.append("M".repeat(n));
            } else if (num >= 500) { //number is in the range [500-999]
                int leftMostDigit = num / divideBy; // Get the leftmost digit from the num
                if (num >= 900) { // number is in the range [900-999]
                    sb.append("CM"); // e.g. if num=924 then for 900, we will append CM to final output
                } else { //number is in the range [500-899]
                    sb.append("D");
                    //  Subtractive Notation: D is 500 and C is 100, so for num 724 , C will be repeated 2 times
                    sb.append("C".repeat(leftMostDigit - 5));
                }
            } else if (num >= 100) { // num in range [100 - 499]
                int leftMostDigit = num / divideBy;
                if (num >= 400) { // num in range [400 - 499]
                    sb.append("CD");
                } else {
                    sb.append("C".repeat(leftMostDigit));  // num in range [100 - 399]
                }
            } else if (num >= 50) { // num in range [50 - 99]
                int leftMostDigit = num / divideBy;
                if (num >= 90) { // num in range [90 - 99]
                    sb.append("XC");
                } else {
                    sb.append("L");
                    sb.append("X".repeat(leftMostDigit - 5));
                }
            } else if (num >= 10) { // num in range [10 - 49]
                int leftMostDigit = num / divideBy;
                if (num >= 40) {
                    sb.append("XL");
                } else {
                    sb.append("X".repeat(leftMostDigit)); // num in range [10 - 39]
                }
            } else if (num >= 5) { // num in range [5 - 9]
                if (num >= 9) { // num = 9
                    sb.append("IX");
                } else {
                    sb.append("V");
                    sb.append("I".repeat(num - 5));
                }
            } else if (num >= 4) { //  num=4
                sb.append("IV");
                num = 0;
            } else {  // num is in range [1-3]
                sb.append("I".repeat(num));
                num = 0;
            }
            num = num % divideBy;  // e.g. 1234%1000= 234, num will be 234 for next iteratino
            divideBy = getDivisor(num); // e.g. next divisor for num=234 is 100
        }
        String romanNumeral = sb.toString();
        return new RomanNumeralDto(input, romanNumeral);
    }

    int getDivisor(int num) {
        int ceil = (int) Math.floor(Math.log10(num));
        return (int) Math.pow(10, ceil);
    }
}
