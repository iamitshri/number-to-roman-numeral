package com.practice.dto;

import io.swagger.annotations.ApiModelProperty;

public class RomanNumeralDto {

    @ApiModelProperty(notes = "Input number in the range[1-3999]", name = "input", required = true, dataType = "Integer")
    private final int input;

    @ApiModelProperty(notes = "Roman representation of the input number", name = "output")
    private final String output;

    public RomanNumeralDto(int input, String output) {
        this.input = input;
        this.output = output;
    }

    public int getInput() {
        return input;
    }

    public String getOutput() {
        return output;
    }

    @Override
    public String toString() {
        return "RomanNumeralDto{" +
                   "input=" + input +
                   ", output='" + output + '\'' +
                   '}';
    }
}
