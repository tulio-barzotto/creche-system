package br.com.crechesystem.crechesystem.utils;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public final class DateUtils {

    private DateUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static long getMonthsBetweenDates(LocalDate initialDate, LocalDate finalDate) {
        return ChronoUnit.MONTHS.between(initialDate, finalDate);
    }
}
