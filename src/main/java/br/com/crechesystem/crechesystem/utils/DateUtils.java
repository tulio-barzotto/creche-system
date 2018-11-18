package br.com.crechesystem.crechesystem.utils;

import java.sql.Date;
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

    public static String getAgeFormatted(Date birthdate) {
        LocalDate localDateBirthdate = birthdate.toLocalDate();
        int monthsFromBirthdate = getMonthFromBirthdate(localDateBirthdate);
        int years = monthsFromBirthdate / 12;
        int months = monthsFromBirthdate % 12;
        if (years > 0) {
            if(years == 1) {
                return String.format("%d ano e %d meses", years, months);
            } else {
                return String.format("%d anos e %d meses", years, months);
            }
        } else {
            return String.format("%d meses", months);
        }
    }
}
