package com.practice.dto;

import java.util.List;


public class RomanNumeralsInRange {

    private final List<RomanNumeralDto> conversions;

    public RomanNumeralsInRange(List<RomanNumeralDto> conversions) {
        this.conversions = conversions;
    }

    public List<RomanNumeralDto> getConversions() {
        return conversions;
    }

}
