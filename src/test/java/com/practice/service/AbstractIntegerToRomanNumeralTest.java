package com.practice.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.practice.dto.RomanNumeralDto;
import com.practice.exception.InvalidInputException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

public abstract class AbstractIntegerToRomanNumeralTest {

    /**
     * This method reads a RomanNumeralTestData.xlsx file from src/main/resources/RomanNumeralTestData.xlsx
     *
     * @return Map of input and expected Roman numeral
     * @throws IOException if test data file is missing or is invalid or not in expected form
     */
    Map<Integer, String> getTestData() throws IOException {

        Map<Integer, String> map = new HashMap<>();
        String fileName = "RomanNumeralTestData.xlsx";
        ClassLoader cl = this.getClass()
                             .getClassLoader();
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver(cl);
        Resource[] resources = resolver.getResources("classpath*:/" + fileName);
        File attachmentFileByName = resources[0].getFile();
        try (FileInputStream file = new FileInputStream(attachmentFileByName)) {
            XSSFWorkbook myWorkBook = new XSSFWorkbook(file);
            Sheet sheet = myWorkBook.getSheetAt(0);
            DataFormatter formatter = new DataFormatter();
            for (Row row : sheet) {
                int num = 0;
                String expectedRomanNumeral = "";
                for (Cell cell : row) {
                    if (cell.getCellTypeEnum() == CellType.FORMULA) {
                        expectedRomanNumeral = cell.getRichStringCellValue()
                                                   .getString();
                    } else {
                        String val = formatter.formatCellValue(cell);
                        num = Integer.parseInt(val);
                    }
                }
                map.put(num, expectedRomanNumeral);
                if (num == 3999) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }
}