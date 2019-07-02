package com.company;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DateCalculatorTest {
    private DateCalculator dateCalculator;
    @Before
    public void instantiateDateCalculator(){
        dateCalculator = new DateCalculator();
    }
    @Test
    public void june2019Has30Days(){

        int days = dateCalculator.claculateTheDaysOfMonth(2019, 6);
        Assert.assertEquals(days, 30);
    }
    @Test
    public void february2019Has28Days(){

        int days = dateCalculator.claculateTheDaysOfMonth(2019, 2);
        Assert.assertEquals(days, 28);
    }
    @Test
    public void february2019LeapYear(){

        int days = dateCalculator.claculateTheDaysOfMonth(2019, 2);
        Assert.assertEquals(days, 28);

    }
    @Test
    public void july2019Has31Days(){

        int days = dateCalculator.claculateTheDaysOfMonth(2019, 7);
        Assert.assertEquals(days, 31);
    }
    @Test
    public void onehundred2019Has28Days(){

        int days = dateCalculator.claculateTheDaysOfMonth(2019, 7);
        Assert.assertEquals(days, 31);
    }
}
