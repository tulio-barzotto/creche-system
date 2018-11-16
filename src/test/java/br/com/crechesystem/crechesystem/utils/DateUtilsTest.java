package br.com.crechesystem.crechesystem.utils;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

public class DateUtilsTest {

    @Test
    public void deveRetornarDiferencaSeisMeses() {
        LocalDate initialDate = LocalDate.now().minusMonths(6);
        LocalDate finalDate = LocalDate.now();
        long months = DateUtils.getMonthsBetweenDates(initialDate, finalDate);
        Assert.assertEquals(6L, months);
    }

}