package com.practice.service;

import com.practice.dto.RomanNumeralDto;
import com.practice.exception.InvalidInputException;
import com.practice.exception.InvalidRangeException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class RomanNumeralsInRangeService {

    private static final String MESSAGE = "A valid integer in the range [1-3999] is required";
    private final IntegerToRoman integerToRomanService;

    public RomanNumeralsInRangeService(IntegerToRoman integerToRomanService) {
        this.integerToRomanService = integerToRomanService;
    }

    public List<RomanNumeralDto> getRomanNumeralsInRange(Integer min, Integer max) {

        Assert.notNull(min, "min is null. " + MESSAGE);
        Assert.notNull(max, "max is null. " + MESSAGE);
        if (min < 1 || min > 3999) {
            throw new InvalidInputException("min is not within the required range. " + MESSAGE);
        }

        if (max < 1 || max > 3999) {
            throw new InvalidInputException("max is not within the required range. " + MESSAGE);
        }

        if (min >= max) {
            throw new InvalidRangeException("Min must be less than max. Both must be in the supported range[1-3999]");
        }

        List<CompletableFuture<RomanNumeralDto>> result = new ArrayList<>();
        for (int i = min; i <= max; i++) {
            result.add(integerToRomanService.convertAsync(i));
        }
        CompletableFuture<Void> combinedCompletableFuture = CompletableFuture.allOf(
            result.toArray(new CompletableFuture[0]));
        CompletableFuture<List<RomanNumeralDto>> listCompletableFuture = combinedCompletableFuture.thenApply(
            f -> result.stream()
                       .map(CompletableFuture::join)
                       .collect(Collectors.toList()));
        List<RomanNumeralDto> list = listCompletableFuture.join();
        Collections.sort(list); // since we can not depend on the ordering of events in asynchronous processing
        return list;
    }
}
