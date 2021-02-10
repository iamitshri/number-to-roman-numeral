package com.practice.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.dto.RomanNumeralDto;
import com.practice.dto.RomanNumeralsInRange;
import com.practice.exception.InvalidInputException;
import com.practice.exception.InvalidRangeException;
import com.practice.service.IntegerToRoman;
import com.practice.service.IntegerToRomanNumeralService;
import com.practice.service.RomanNumeralsInRangeService;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(MockitoJUnitRunner.class)
public class IntToRomanControllerTest {

    Logger log = LoggerFactory.getLogger(this.getClass()
                                             .getName());
    @Mock
    IntegerToRoman service;

    @Mock
    RomanNumeralsInRangeService romanNumeralsInRangeService;

    @InjectMocks
    IntegerToRomanNumeralController controller;

    MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = standaloneSetup(this.controller).build();
    }

    @Test
    public void getRomanNumeralValidInput() throws Exception {

        // arrange
        RomanNumeralDto rd = new RomanNumeralDto(1, "I");
        when(service.convert(1)).thenReturn(rd);

        // act
        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.get("/v1/romannumeral")
                                                                      .param("query", "1"))
                                       .andExpect(status().isOk())
                                       .andDo(print())
                                       .andReturn();
        // assert
        String actual = result.getResponse()
                              .getContentAsString();
        String expected = objectToJson(rd);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void getRomanNumeralInvalidInput() throws Exception {

        // arrange
        when(service.convert(4000)).thenThrow(new InvalidInputException());

        // act
        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.get("/v1/romannumeral")
                                                                      .param("query", "4000"))
                                       .andExpect(status().is5xxServerError())
                                       .andDo(print())
                                       .andReturn();
    }

    public String objectToJson(Object obj) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.error("JsonProcessingException: ", e);
            return obj.toString();
        }
    }

    @Test
    public void getRomanNumeralInRangeInvalidInput() throws Exception {

        // arrange
        when(romanNumeralsInRangeService.getRomanNumeralsInRange(2, 1)).thenThrow(new InvalidRangeException());

        // act
        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.get("/v2/romannumeral")
                                                                      .param("min", "2")
                                                                      .param("max", "1"))
                                       .andExpect(status().is5xxServerError())
                                       .andDo(print())
                                       .andReturn();
    }

    @Test
    public void getRomanNumeralInRangeQueryParameterInvalidInput() throws Exception {

        // arrange
        when(service.convert(4000)).thenThrow(new InvalidInputException());

        // act
        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.get("/v2/romannumeral")
                                                                      .param("query", "4000"))
                                       .andExpect(status().is5xxServerError())
                                       .andDo(print())
                                       .andReturn();
    }

    @Test
    public void getRomanNumeralInRangeValidInput() throws Exception {

        // arrange
        List<RomanNumeralDto> lst = new ArrayList<>();
        lst.add(new RomanNumeralDto(1, "I"));
        lst.add(new RomanNumeralDto(2, "II"));

        when(romanNumeralsInRangeService.getRomanNumeralsInRange(2, 1)).thenReturn(lst);

        // act
        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.get("/v2/romannumeral")
                                                                      .param("min", "2")
                                                                      .param("max", "1"))
                                       .andExpect(status().isOk())
                                       .andDo(print())
                                       .andReturn();

        RomanNumeralsInRange range = new RomanNumeralsInRange(lst);
        String expected = objectToJson(range);
        String actual = result.getResponse()
                              .getContentAsString();
        assertThat(actual).isEqualTo(expected);
    }
}