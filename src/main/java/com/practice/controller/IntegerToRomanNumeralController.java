package com.practice.controller;

import com.practice.dto.RomanNumeralDto;
import com.practice.dto.RomanNumeralsInRange;
import com.practice.service.IntegerToRoman;
import com.practice.service.RomanNumeralsInRangeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@Api(value = "Endpoint to get roman numeral representation of the integer")
public class IntegerToRomanNumeralController {

    private final IntegerToRoman integerToRomanService;
    private final RomanNumeralsInRangeService romanNumeralsInRangeService;

    public IntegerToRomanNumeralController(IntegerToRoman integerToRomanService,
        RomanNumeralsInRangeService romanNumeralsInRangeService) {
        this.integerToRomanService = integerToRomanService;
        this.romanNumeralsInRangeService = romanNumeralsInRangeService;
    }

    /**
     * Gets roman numeral.
     *
     * @param input the input
     * @return ResponseEntity roman numeral
     */
    @ApiOperation(value = "Get roman numeral for integer",
                  notes = "Returns the roman representation of decimal number in the range[1-3999]")
    @GetMapping(value = "/v1/romannumeral")
    public ResponseEntity<RomanNumeralDto> getRomanNumeral(
        @ApiParam(name = "query", type = "Integer", value = "Integer value in the range[1-3999]", required = true) @RequestParam(name = "query") Integer input) {
        RomanNumeralDto romanNumeral = integerToRomanService.convert(input);
        return ResponseEntity.ok(romanNumeral);
    }

    /**
     * V2 version of the rest service Added support for range queries. If query parameter is provided, min and max will
     * be ignored
     *
     * @param min   the min
     * @param max   the max
     * @param query the query
     * @return ResponseEntity RomanNumeralsInRange
     */
    @ApiOperation(value = "Added support for range queries. If query parameter is provided, min and max will be ignored",
                  notes = "Returns a list of roman numerals for the min, max provided or single result when query is provided")
    @GetMapping(value = "/v2/romannumeral")
    public ResponseEntity<RomanNumeralsInRange> getRomanNumberForRangeOfNumbers(
        @ApiParam(name = "min", type = "Integer", value = "Should be less than max. Integer value in the range[1-3999]")
        @RequestParam(name = "min", required = false) Integer min,
        @ApiParam(name = "max", type = "Integer", value = "Should be greater than min. Integer value in the range[1-3999]")
        @RequestParam(name = "max", required = false) Integer max,
        @ApiParam(name = "query", type = "Integer", value = "Integer value in the range[1-3999]")
        @RequestParam(name = "query", required = false) Integer query) {
        if (query != null) {
            List<RomanNumeralDto> romanNumberList = new ArrayList<>();
            romanNumberList.add(integerToRomanService.convert(query));
            return ResponseEntity.ok(new RomanNumeralsInRange(romanNumberList));
        }
        List<RomanNumeralDto> romanNumberList = romanNumeralsInRangeService.getRomanNumeralsInRange(min, max);
        return ResponseEntity.ok(new RomanNumeralsInRange(romanNumberList));
    }
}