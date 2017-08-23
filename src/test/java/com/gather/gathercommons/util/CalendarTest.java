package com.gather.gathercommons.util;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;

/**
 * Created with IntelliJ IDEA.
 * $ Project: gathercommons
 * User: rodrigotroy
 * Date: 8/23/17
 * Time: 12:44
 */
public class CalendarTest {
    private Calendar calendar;

    @DataProvider
    public Object[][] fechas() {
        java.util.Calendar calendarAdd2Days = java.util.Calendar.getInstance();
        calendarAdd2Days.add(java.util.Calendar.DAY_OF_MONTH,
                             2);

        return new Object[][]{
                {Calendar.getInstance().getTime(), java.util.Calendar.getInstance().getTime()},
                {Calendar.getInstance().addDay(2).getTime(), calendarAdd2Days.getTime()}};
    }


    @Test(dataProvider = "fechas")
    public void test(Object o1,
                     Object o2) {
        SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");

        Assert.assertEquals(format1.format(o1),
                            format1.format(o2));
    }
}
