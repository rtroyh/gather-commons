package com.gather.gathercommons.util;

import java.util.Calendar;

public class TimeUtil {

    private Calendar c = Calendar.getInstance();

    public TimeUtil() {
    }

    public synchronized final Long getTimeInMillis() {
        return c.getTimeInMillis();
    }
}
