package com.practice.controller;

import com.practice.dto.RomanNumeralDto;
import com.practice.service.IntegerToRoman;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@Api(value = "Endpoint to get roman numeral representation of the integer")
public class IntegerToRomanNumeralController {

    private final IntegerToRoman integerToRomanNumeralService;

    public IntegerToRomanNumeralController(IntegerToRoman integerToRomanNumeralService) {
        this.integerToRomanNumeralService = integerToRomanNumeralService;
    }

    /**
     * @param input
     * @return ResponseEntity
     */
    @ApiOperation(value = "Get roman numeral for integer",
                  notes = "Returns the roman representation of decimal number in the range[1-3999]")
    @GetMapping(value = "/v1/romannumeral")
    public ResponseEntity<RomanNumeralDto> getRomanNumeral(
        @ApiParam(name = "query", type = "Integer", value = "Integer value in the range[1-3999]", required = true) @RequestParam(name = "query") Integer input) {
        RomanNumeralDto romanNumeral = integerToRomanNumeralService.convert(input);
        return ResponseEntity.ok(romanNumeral);
    }
}