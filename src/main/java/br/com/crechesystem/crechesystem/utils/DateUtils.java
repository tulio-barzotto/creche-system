package br.com.crechesystem.crechesystem.utils;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public final class DateUtils {

    private DateUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static int getMonthsBetweenDates(LocalDate initialDate, LocalDate finalDate) {
        return (int) ChronoUnit.MONTHS.between(initialDate, finalDate);
    }

    public static int getMonthFromBirthdate(LocalDate birthdate) {
        return getMonthsBetweenDates(birthdate, LocalDate.now());
    }
}
