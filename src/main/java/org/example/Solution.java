package org.example;
import java.time.YearMonth;

public class Solution {
    public static void main(String[] args) {
        printBonusDatesBetween(2010, 2015);
    }

    /**
     * Prints all dates between two given years that remain the same, if numbers of the date are reversed.
     * Solution by: Saulius Jakovonis
     * @param fromYear - start year (inclusive)
     * @param toYear - end year (exclusive)
     */
    public static void printBonusDatesBetween(int fromYear, int toYear) {
        // Checking illegal domain
        if (fromYear < 0) throw new RuntimeException("Year " + fromYear + " is negative.");
        if (fromYear > toYear) throw new RuntimeException(fromYear + " is larger than " + toYear);

        for (int currYear = fromYear; currYear < Math.min(9999, toYear); currYear++) {
            // Converting the year into a char array and composing the appropriate month and day out of these digits
            // so that the dates remain the same if the numbers are reversed
            char[] currYearDigits = formatToNdigits(currYear, 4).toCharArray();
            int newMonth = Integer.parseInt(currYearDigits[3] + "" + currYearDigits[2]);
            int newDay = Integer.parseInt(currYearDigits[1] + "" + currYearDigits[0]);

            // Checking if it is a valid month
            if (newMonth > 12 || newMonth < 1)
                continue;

            // Checking if it is a valid day, given a month and a year
            int maxMonthDays = YearMonth.of(fromYear, newMonth).lengthOfMonth();
            if (newDay > maxMonthDays)
                continue;

            // Outputting the result
            System.out.println(
                    formatToNdigits(currYear, 4) + "-" +
                    formatToNdigits(newMonth, 2) + "-" +
                    formatToNdigits(newDay, 2));
        }
    }

    /**
     * Helper method:
     *  Prepends zeros to a String representation of a number until it matches the specified number of digits.
     *  (e.g. formatToNdigits(999, 4) --> "0999")
     * @param number - number to be formatted
     * @param nDigits - desired number of digits
     * @return - formatted number as a String
     */
    public static String formatToNdigits(int number, int nDigits) {
        StringBuilder numberBuilder = new StringBuilder(String.valueOf(number));

        while (numberBuilder.length() < nDigits)
            numberBuilder.insert(0, "0");

        return numberBuilder.toString();
    }
}