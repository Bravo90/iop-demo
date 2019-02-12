package com.sitech.billing.common.utils;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtilsTest {

    private Logger logger = LoggerFactory.getLogger(DateUtils.class);

    @Test
    public void dateFormat() {
        String s = DateUtils.dateFormat(new Date(), DateUtils.DATE_TIME_STANDARD_PATTERN);
        logger.info(s);
    }

    @Test
    public void dateParse() throws ParseException{
        Date date = DateUtils.dateParse("20181202203212", DateUtils.DATE_TIME_STRING_PATTERN);
        logger.info(date.toString());
    }

    @Test
    public void datePatternExchange() throws ParseException {
        String s = DateUtils.datePatternExchange("20181202203212", DateUtils.DATE_TIME_STRING_PATTERN, DateUtils.DATE_TIME_STANDARD_PATTERN);
        logger.info(s);
    }

    @Test
    public void offsetDate()throws ParseException {
        Date dateFrom = DateUtils.dateParse("20180228203212", DateUtils.DATE_TIME_STRING_PATTERN);
        Date date = DateUtils.offsetDate(dateFrom, Calendar.YEAR, -1);
        String s = DateUtils.dateFormat(date, DateUtils.DATE_TIME_STANDARD_PATTERN);
        logger.info(s);
    }


    public static void main(String[] args) {
        final String date[] = { "2001-01-01", "2002-02-02", "2003-03-03", "2004-04-04", "2005-05-05" };
        String pattern = "yyyy-MM-dd";

        final SimpleDateFormat unSafeDateFormat = new SimpleDateFormat(pattern);

        for (int i = 0; i < date.length; i++) {
            final int temp = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        for (int j = 0; j < 10; j++) {
                            String str1 = date[temp];
                            String str2 = unSafeDateFormat.format(unSafeDateFormat.parse(str1));

                            System.out.println(temp + ", unsafe: " + Thread.currentThread().getName() + ", expire="
                                    + str1 + ", real=" + str2);
                        }
                    } catch (Exception e) {
                        System.out.println(temp + ", unsafe: " + Thread.currentThread().getName() + ", parse failed");
                    }
                }
            }).start();
        }
    }
}