package com.practice.dto;

import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;

public class RomanNumeralDto implements Comparable<RomanNumeralDto> {

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

    public int compareTo(RomanNumeralDto other) {
        return Integer.compare(this.input, other.input);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RomanNumeralDto that = (RomanNumeralDto) o;
        return input == that.input && Objects.equals(output, that.output);
    }

    @Override
    public int hashCode() {
        return Objects.hash(input, output);
    }
}
