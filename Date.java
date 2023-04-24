package model;


import java.io.Serializable;
import java.time.Year;

/**
 * Basic Date Template.
 *
 * @link <a href="https://github.com/rpedros02/Date_Template">Link to GitHub.</a>
 */
public class Date implements Serializable {
    private int day;
    private int month;
    private int year;

    /**
     * Date Constructor.
     *
     * @param day   → Date Day - Int
     * @param month → Date Month - Int
     * @param year  → Date Year - Int
     */
    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    /**
     * Checks if the given Date is valid using {@link  Date#isValid(int day, int month, int year)}.
     *
     * @param day   → Date Day - Int
     * @param month → Date Month - Int
     * @param year  → Date Year - Int
     * @throws InvalidDateException in case the given Date is invalid.
     */
    private void checkDate(int day, int month, int year) throws InvalidDateException {
        if (isValid(day, month, year)) {
            this.day = day;
            this.month = month;
            this.year = year;
        } else {
            throw new InvalidDateException(day + "/" + month + "/" + year + ": Invalid Date");
        }
    }

    /**
     * Verifies the Date by checking if the number of days matches the given month.
     * Also calls the method {@link Date#isLeap(int year)} to check if the year is Leap.
     *
     * @param day   → Given Day - Int
     * @param month → Given Month - Int
     * @param year  → Given Year - Int
     * @return true if the Date is valid, otherwise false.
     */
    private boolean isValid(int day, int month, int year) {
        boolean flag = false;
        if (year < 1900 || year > Year.now().getValue()) {
            return false;
        }
        switch (month) {
            case 1, 3, 5, 7, 8, 10, 12 -> {
                if (day > 0 && day <= 31) {
                    flag = true;
                }
            }
            case 4, 6, 9, 11 -> {
                if (day > 0 && day <= 30) {
                    flag = true;
                }
            }
            case 2 -> {
                if (isLeap(year)) {
                    if (day > 0 && day <= 29) {
                        flag = true;
                    }
                } else {
                    if (day > 0 && day <= 28) {
                        flag = true;
                    }
                }
            }
            default -> {
            }
        }
        return flag;
    }

    /**
     * Method that verifies if two Dates are equal.
     *
     * @param date → Given Date - Date
     * @return true if the Dates are equal, false otherwise.
     */
    private boolean isEqual(Date date) {
        return this.day == date.day && this.month == date.month && this.year == date.year;
    }

    /**
     * Checks if this Date is earlier than the given one.
     *
     * @param date → Given Date - Date
     * @return true if this Date is earlier, false otherwise.
     */
    private boolean isEarlier(Date date) {
        if (this.year < date.year) {
            return true;
        }
        if (this.year == date.year) {
            if (this.month < date.month) {
                return true;
            }
            if (this.month == date.month) {
                return day < date.day;
            }
        }
        return false;
    }

    /**
     * Checks if this Date is Later than the given one.
     * The method does this by checking if the date is neither {@link Date#isEqual(Date givenDate)}(Equal) or {@link Date#isEarlier(Date givenDate)}(Earlier).
     *
     * @param date → Given Date - Date
     * @return true if this Date is later than the given one, false otherwise.
     */
    private boolean isLater(Date date) {
        return !(isEarlier(date) && isEqual(date));
    }

    /**
     * Checks if the given Year is Leap.
     *
     * @param year → Given Year - Int
     * @return true if the Year is leap, false otherwise.
     */
    private boolean isLeap(int year) {
        return ((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0);
    }

    /**
     * Override Method toString.
     *
     * @return Date in <b>dd/mm/YYYY</b> format.
     */
    @Override
    public String toString() {
        return day + "/" + month + "/" + year;
    }
}
