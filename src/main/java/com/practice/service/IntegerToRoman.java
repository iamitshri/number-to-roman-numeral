package com.practice.service;

import com.practice.dto.RomanNumeralDto;
import java.util.concurrent.CompletableFuture;

public interface IntegerToRoman {

    RomanNumeralDto convert(Integer num);

    CompletableFuture<RomanNumeralDto> convertAsync(Integer input);
}
