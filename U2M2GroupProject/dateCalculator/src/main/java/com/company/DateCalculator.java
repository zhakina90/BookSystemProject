package com.company;

public class DateCalculator {
    public static void main(String[] args) {
        System.out.println("Hello, world! I am the date calculator!");
    }
    public int claculateTheDaysOfMonth(int year, int month){
        int returnVal =30;
        int returnValue = 100;
        switch (month){
            case 2:
                if (year%4==0){
                    returnVal = 29;
                }else{
                    returnVal=28;
                }
                returnVal = 28;
                break;
            case 7:
            case 12:
                returnVal = 31;
                break;
        }
        return returnVal;
    }

}
