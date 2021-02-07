package com.practice;

import static org.assertj.core.api.Assertions.assertThat;

import com.practice.service.IntegerToRomanNumeralService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class NumberToRomanNumeralApplicationTests {

    @Autowired
    IntegerToRomanNumeralService service;

    @Test
    public void contextLoads() {
        assertThat(service).isNotNull();
    }
}
