package esc.dc.Utils.ForDate;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@Controller
@RequestMapping(value = "/currentDate")
public class TestClassForDate {


    int minBsYear = 1970;
    int maxBsYear = 2100;

    int minAdEqBsYearAD = 1913;
    int minAdEqBsYearBS = 1970;

    int minAdEqBsMonthAD = 3;
    int minAdEqBsMonthBS = 1;

    int minAdEqBsDayAD = 13;
    int minAdEqBsDayBS = 1;

    //for diff
    int[] myArray1 = {0, 1, 1, 22, 1, 3, 1, 1, 1, 3, 1, 22, 1, 3, 1, 3, 1, 22, 1, 3, 1, 19, 1, 3, 1, 1, 3, 1, 2, 2, 1, 3, 1};
    int[] myArray2 = {1, 2, 2, 2, 2, 2, 2, 1, 3, 1, 3, 1, 2, 2, 2, 3, 2, 2, 2, 1, 3, 1, 3, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 3, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 3, 1, 2, 2, 2, 2, 2, 1, 1, 1, 2, 2, 2, 2, 2, 1, 3, 1, 1, 2};
    int[] myArray3 = {0, 1, 2, 1, 3, 1, 3, 1, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2, 2, 2, 2, 2, 2, 2, 2, 1, 3, 1, 3, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 3, 1, 3, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 3, 1, 3, 1, 1, 1, 1, 2, 2, 2, 2, 2, 1, 3, 1, 1, 2};
    int[] myArray4 = {1, 2, 1, 3, 1, 3, 1, 3, 1, 3, 1, 3, 1, 3, 1, 3, 1, 3, 1, 3, 1, 3, 1, 3, 1, 3, 1, 3, 1, 2, 2, 2, 1, 3, 1, 3, 1, 3, 1, 3, 1, 3, 1, 2, 2, 2, 1, 3, 1, 3, 1, 3, 1, 3, 1, 3, 1, 3, 2, 2, 1, 3, 1, 2, 2, 2, 1, 2};
    int[] myArray5 = {59, 1, 26, 1, 28, 1, 2, 1, 12};
    int[] myArray6 = {0, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 3, 1, 3, 1, 3, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 3, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 3, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 5, 1, 1, 2, 2, 1, 3, 1, 2, 1, 2};
    int[] myArray7 = {0, 12, 1, 3, 1, 3, 1, 5, 1, 11, 1, 3, 1, 3, 1, 18, 1, 3, 1, 3, 1, 18, 1, 3, 1, 3, 1, 27, 1, 2};
    int[] myArray8 = {1, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 3, 1, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 15, 2, 4};
    int[] myArray9 = {0, 1, 2, 2, 2, 2, 1, 3, 1, 3, 1, 3, 1, 2, 2, 2, 3, 2, 2, 2, 1, 3, 1, 3, 1, 3, 1, 2, 2, 2, 2, 2, 2, 2, 1, 3, 1, 3, 1, 3, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 3, 1, 3, 1, 2, 2, 2, 15, 2, 4};
    int[] myArray10 = {1, 1, 3, 1, 3, 1, 14, 1, 3, 1, 1, 1, 3, 1, 14, 1, 3, 1, 3, 1, 3, 1, 18, 1, 3, 1, 3, 1, 3, 1, 14, 1, 3, 15, 1, 2, 1, 1};
    int[] myArray11 = {0, 1, 1, 3, 1, 3, 1, 10, 1, 3, 1, 3, 1, 1, 1, 3, 1, 3, 1, 10, 1, 3, 1, 3, 1, 3, 1, 3, 1, 14, 1, 3, 1, 3, 1, 3, 1, 3, 1, 10, 1, 20, 1, 1, 1};
    int[] myArray12 = {1, 2, 2, 1, 3, 1, 3, 1, 3, 1, 2, 2, 2, 2, 2, 3, 2, 2, 2, 2, 2, 1, 3, 1, 3, 1, 3, 1, 2, 2, 2, 2, 2, 2, 2, 1, 3, 1, 3, 1, 3, 1, 3, 1, 2, 2, 2, 2, 2, 2, 2, 1, 3, 1, 3, 1, 20, 3};

    int[][] extractedBsMonthData = {myArray1,myArray2,myArray3,myArray4,myArray5,myArray6,myArray7,myArray8,myArray9,myArray10,myArray11,myArray12};

    //for upperdays
    int[] upperDays1 = {30, 31};
    int[] upperDays2 = {31, 32};
    int[] upperDays3 = {31, 32};
    int[] upperDays4 = {31,32};
    int[] upperDays5 = {31, 32};
    int[] upperDays6 = {30, 31};
    int[] upperDays7 = {29, 30};
    int[] upperDays8 = {29, 30};
    int[] upperDays9 = {29, 30};
    int[] upperDays10 = {29, 30};
    int[] upperDays11 = {29, 30};
    int[] upperDays12 = {30, 31};

    int[][] bsMonthUpperDays = {upperDays1,upperDays2,upperDays3,upperDays4,upperDays5,upperDays6,upperDays7,upperDays8,upperDays9,upperDays10,upperDays11,upperDays12};


    @GetMapping
    public String pintDateHere(){

        Date todayDate = new Date();
        System.out.println("today Date" + todayDate);
        System.out.println(todayDate.getMonth());

        Date adFinalDate = getBsDate(2022,4,14);
        //month is calculated as +1 so for correct during printout we need to negate month by 1
        System.out.println(adFinalDate);
        System.out.println(adFinalDate.getYear()+1900);
        System.out.println(adFinalDate.getMonth());
        System.out.println(adFinalDate.getDate());

        return "/error";
    }


    public Date getBsDate(int adYear,int adMonth , int adDay){
        int bsYearSet=0;
        int bsMonthSet=0;
        int bsDaySet=0;

        bsYearSet = adYear + 57;

        bsMonthSet = (adMonth + 9) % 12;

        if(bsMonthSet==0){
            bsMonthSet=12;
            bsYearSet = bsYearSet-1;
        }

//        bsMonthSet = bsMonthSet==0?12:bsMonthSet;


        bsDaySet =1;

        if (adMonth < 4) {
            bsYearSet -= 1;
        } else if (adMonth == 4) {
            Date bsYearFirstAdDate = getAdDate(bsYearSet, 1, 1);
            if (adDay < bsYearFirstAdDate.getDate()) {
                bsYearSet -= 1;
            }
        }

        Date bsMonthFirstAdDate = getAdDate(bsYearSet, bsMonthSet, 1);
        if (adDay >= 1 && adDay < bsMonthFirstAdDate.getDate()) {
            bsMonthSet = (bsMonthSet != 1) ? bsMonthSet - 1 : 12;
            int bsMonthDays = getBsMonthDays(bsYearSet, bsMonthSet);
            bsDaySet = bsMonthDays - (bsMonthFirstAdDate.getDate() - adDay) + 1;
        } else {
            bsDaySet = adDay - bsMonthFirstAdDate.getDate() + 1;
        }

        Date finalBsDate = new Date();
        finalBsDate.setYear(bsYearSet-1900);
        finalBsDate.setMonth(bsMonthSet);
        finalBsDate.setDate(bsDaySet);
        return finalBsDate;
    }

    public Date getAdDate(int bsYear, int bsMonth, int bsDay){
        int adYearSet = 0;
        int adMonthSet = 0;
        int adDaySet = 0;

        int daysNumFromMinBsYear = getTotalDaysNumFromMinBsYear(bsYear, bsMonth, bsDay);

        adYearSet=minAdEqBsYearAD-1900;
        adMonthSet=minAdEqBsMonthAD;
        adDaySet = minAdEqBsDayAD-1;

        int[] adDate = {adYearSet, adMonthSet, adDaySet};

        Date firstDate = new Date();
        firstDate.setYear(adYearSet);
        firstDate.setMonth(adMonthSet);
        firstDate.setDate(adDaySet);
        firstDate.setDate(firstDate.getDate()+daysNumFromMinBsYear);
        return firstDate;

    }

    public int getMonthDaysNumFormMinBsYear(int bsMonth, int yearDiff){
        int monthDaysFromMinBsYear = 0;
        int yearCount = 0;

        if (yearDiff == 0) {
            return 0;
        }
        int[] bsMonthData = extractedBsMonthData[bsMonth - 1];

        for (int i = 0; i < bsMonthData.length; i++) {
            if (bsMonthData[i] == 0) {
                continue;
            }
            int bsMonthUpperDaysIndex = i % 2;
            if (yearDiff > yearCount + bsMonthData[i]) {
                yearCount += bsMonthData[i];
                monthDaysFromMinBsYear += bsMonthUpperDays[bsMonth - 1][bsMonthUpperDaysIndex] * bsMonthData[i];
            } else {
                monthDaysFromMinBsYear += bsMonthUpperDays[bsMonth - 1][bsMonthUpperDaysIndex] * (yearDiff - yearCount);
                yearCount = yearDiff - yearCount;
                break;
            }
        }

        return monthDaysFromMinBsYear;

    }

    public int getTotalDaysNumFromMinBsYear(int bsYear, int bsMonth, int bsDay){
        int daysNumFromMinBsYear = 0;

        if (bsYear < minBsYear || bsYear > maxBsYear) {
            return 0;
        }
        int diffYears = bsYear - minBsYear;
        for (int month = 1; month <= 12; month++) {
            if (month < bsMonth) {
                daysNumFromMinBsYear += getMonthDaysNumFormMinBsYear(month,diffYears+1);
            } else {
                daysNumFromMinBsYear += getMonthDaysNumFormMinBsYear(month, diffYears);
            }
        }

        if (bsYear > 2085 && bsYear < 2088) {
            daysNumFromMinBsYear += bsDay - 2;
        } else if (bsYear == 2085 && bsMonth > 5) {
            daysNumFromMinBsYear += bsDay - 2;
        } else if (bsYear > 2088) {
            daysNumFromMinBsYear += bsDay - 4;
        } else if (bsYear == 2088 && bsMonth > 5) {
            daysNumFromMinBsYear += bsDay - 4;
        } else {
            daysNumFromMinBsYear += bsDay;
        }

        return daysNumFromMinBsYear;
    }

    public int getBsMonthDays(int bsYear, int bsMonth){

        int yearCount = 0;
        int totalYears = (bsYear + 1) - minBsYear;
        int[] bsMonthData = extractedBsMonthData[bsMonth - 1];
        for (int i = 0; i < bsMonthData.length; i++) {
            if (bsMonthData[i] == 0) {
                continue;
            }

            int bsMonthUpperDaysIndex = i % 2;
            yearCount += bsMonthData[i];
            if (totalYears <= yearCount) {
                if ((bsYear == 2085 && bsMonth == 5) || (bsYear == 2088 && bsMonth == 5)) {
                    return bsMonthUpperDays[bsMonth - 1][bsMonthUpperDaysIndex] - 2;
                } else {
                    return bsMonthUpperDays[bsMonth - 1][bsMonthUpperDaysIndex];
                }
            }
        }

        return 0;
    }
}
