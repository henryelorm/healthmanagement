/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthmanagement.utility;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.util.StringConverter;

/**
 *
 * @author Elorm
 */
public class DateOfBirth {

    String pattern = "dd-MM-yyyy";

    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

    public static ObservableList monthList = FXCollections.observableArrayList("JANUARY", "FEBRUARY", "MARCH", "APRIL",
            "MAY", "JUNE", "JULY", "AUGUST", "SEPTEMBER", "OCTOBER", "NOVEMBER", "DECEMBER");

    public static ObservableList medicationDays = FXCollections.observableArrayList("Daily", "Weekly", "Monthly", "Single Dose", "Continuous", "When Needed");

    public static Date date(String day, ComboBox monthBox, String year) {

        Date date;
        int i = 0;
        if (monthBox.getValue().equals("JANUARY")) {

            i = 0;
        } else if (monthBox.getValue().equals("FEBRUARY")) {
            i = 1;
        } else if (monthBox.getValue().equals("MARCH")) {
            i = 2;
        } else if (monthBox.getValue().equals("APRIL")) {
            i = 3;
        } else if (monthBox.getValue().equals("MAY")) {
            i = 4;
        } else if (monthBox.getValue().equals("JUNE")) {
            i = 5;
        } else if (monthBox.getValue().equals("JULY")) {
            i = 6;
        } else if (monthBox.getValue().equals("AUGUST")) {
            i = 7;
        } else if (monthBox.getValue().equals("SEPTEMBER")) {
            i = 8;
        } else if (monthBox.getValue().equals("OCTOBER")) {
            i = 9;
        } else if (monthBox.getValue().equals("NOVEMBER")) {
            i = 10;
        } else if (monthBox.getValue().equals("DECEMBER")) {
            i = 11;
        }

        return date = new Date((Integer.parseInt(year) - 1900), i, Integer.parseInt(day));
    }

    public static String getMonth(int month) {

        switch (month) {
            case 0:
                return "JANUARY";
            case 1:
                return "FEBRUARY";
            case 2:
                return "MARCH";
            case 3:
                return "APRIL";
            case 4:
                return "MAY";
            case 5:
                return "JUNE";
            case 6:
                return "JULY";
            case 7:
                return "AUGUST";
            case 8:
                return "SEPTEMBER";
            case 9:
                return "OCTOBER";
            case 10:
                return "NOVEMBER";
            case 11:
                return "DECEMBER";
            default:
                break;
        }
        return "";
    }

    public static String getMonth_Integer(String month) {

        switch (month) {
            case "Jan":
                return "01";
            case "Feb":
                return "02";
            case "Mar":
                return "03";
            case "Apr":
                return "04";
            case "May":
                return "05";
            case "Jun":
                return "06";
            case "Jul":
                return "07";
            case "Aug":
                return "08";
            case "Sep":
                return "09";
            case "Oct":
                return "10";
            case "Nov":
                return "11";
            case "Dec":
                return "12";
            default:
                break;
        }
        return "";
    }

    public static ObservableList dayList() {
        ObservableList daylist = FXCollections.observableArrayList();

        for (int i = 1; i < 32; i++) {
            daylist.add(i);
        }
        return daylist;
    }

    public static ObservableList yearList() {
        ObservableList yearList = FXCollections.observableArrayList();

        for (int i = 1; i < 115; i++) {
            int yr = 1929 + i;
            if (yr <= 2045) {
                yearList.add(yr);
            }
        }
        return yearList;

    }

    public String toString(LocalDate date) {
        if (date != null) {
            return dateFormatter.format(date);
        } else {
            return "";
        }
    }

    public LocalDate fromString(String string) {
        if (string != null && !string.isEmpty()) {
            return LocalDate.parse(string, dateFormatter);
        } else {
            return null;
        }
    }

}
