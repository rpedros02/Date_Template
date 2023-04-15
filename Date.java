package model;

import java.time.Year;

public class Date {
    private int day;
    private int month;
    private int year;

    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    private void checkDate(int day, int month, int year) throws InvalidDateException {
        if (isValid(day, month, year)) {
            this.day = day;
            this.month = month;
            this.year = year;
        } else {
            throw new InvalidDateException(day + "/" + month + "/" + year + ": Invalid Date");
        }
    }

    private boolean isValid(int day, int month, int year) {
        boolean flag = false;
        if (year < 1900 || year > Year.now().getValue()) {
            return flag;
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

    private boolean isEqual(Date date) {
        return this.day == date.day && this.month == date.month && this.year == date.year;
    }

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

    private boolean isLater(Date date) {
        return !(isEarlier(date) && isEqual(date));
    }

    private boolean isLeap(int year) {
        return ((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0);
    }

    @Override
    public String toString() {
        return day + "/" + month + "/" + year;
    }
}
