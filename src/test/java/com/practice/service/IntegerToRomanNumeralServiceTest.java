package com.practice.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.practice.dto.RomanNumeralDto;
import com.practice.exception.InvalidInputException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IntegerToRomanNumeralServiceTest extends AbstractIntegerToRomanNumeralTest {

    Logger log = LoggerFactory.getLogger(this.getClass()
                                             .getName());

    IntegerToRomanNumeralService service = new IntegerToRomanNumeralService();

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void testInvalidInput() {
        // act
        exception.expect(InvalidInputException.class);
        service.convert(0);
    }

    @Test
    public void testNullInput() {
        // act
        exception.expect(IllegalArgumentException.class);
        service.convert(null);
    }

    @Test
    public void testOutOfRange() {
        // act
        exception.expect(InvalidInputException.class);
        service.convert(4000);
    }

    @Test
    public void testConvertAllDataRange() throws IOException {

        // arrange
        Map<Integer, String> map = getTestData();

        // act
        List<String> lst = new ArrayList<>();
        for (int num : map.keySet()) {
            RomanNumeralDto res = service.convert(num);
            String expected = map.get(num);
            String actual = res.getOutput();
            if (!actual.equals(expected)) {
                String msg = "input:" + num + ", expected: " + expected + ", Actual:" + actual;
                lst.add(msg);
            }
        }
        // asset
        log.info(String.join("\n", lst));
        // assert that we did not find any mismatch
        assertThat(lst).isEmpty();
    }
}