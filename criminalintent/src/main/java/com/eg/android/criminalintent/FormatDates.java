package com.eg.android.criminalintent;

import android.text.format.DateFormat;

import java.util.Date;

/**
 * @author Datuu
 * @date on 2018/1/29.
 * @email yiyun0331@163.com
 */

public class FormatDates {
    private String dates;

    public FormatDates(Date datess) {
        //日期格式化
        String yue = (String) DateFormat.format("MM", datess);
        String zhou = (String) DateFormat.format("EE", datess);
        switch (yue) {
            case "01":
                yue = "Jan";
                break;
            case "02":
                yue = "Feb";
                break;
            case "03":
                yue = "Mar";
                break;
            case "04":
                yue = "Apr";
                break;
            case "05":
                yue = "May";
                break;
            case "06":
                yue = "Jun";
                break;
            case "07":
                yue = "Jul";
                break;
            case "08":
                yue = "Aug";
                break;
            case "09":
                yue = "Sep";
                break;
            case "10":
                yue = "Oct";
                break;
            case "11":
                yue = "Nov";
                break;
            case "12":
                yue = "Dec";
                break;
            default:
                yue = "13月";
        }
        switch (zhou) {
            case "周一":
                zhou = "Monday";
                break;
            case "周二":
                zhou = "Tuesday";
                break;
            case "周三":
                zhou = "Wednesday";
                break;
            case "周四":
                zhou = "Thursday";
                break;
            case "周五":
                zhou = "Friday";
                break;
            case "周六":
                zhou = "Saturday ";
                break;
            case "周日":
                zhou = "Sunday";
                break;
            default:
                zhou = "周八";
        }

        String date2 = (String) DateFormat.format(" dd, yyyy", datess);
        dates = zhou + " " + yue + date2;
    }

    public String getDates() {
        return dates;
    }

}
