package com.sitech.billing.common.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 日期相关的工具类
 *
 * @author sunzhen
 * @date 2018-12-02 19:47:00
 * @see java.time.format.DateTimeFormatter
 */
public class DateUtils {

    public static final String DATE_TIME_STANDARD_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_TIME_STRING_PATTERM = "yyyyMMddHHmmss";
    public static final String DATE_STANDARD_PATTERN = "yyyy-MM-dd";
    public static final String DATE_STRING_PATTERN = "yyyyMMdd";

    private static ThreadLocal<Map<String, SimpleDateFormat>> sdfMap = new ThreadLocal<Map<String, SimpleDateFormat>>() {
        @Override
        protected Map<String, SimpleDateFormat> initialValue() {
            return new HashMap<>();
        }
    };

    /**
     *  日期类型格式化，将日期格式化为指定类型的日期字符串
     * @param date 日期
     * @param pattern 日期格式
     * @return 格式化后的日期字符串
     */
    public static String dateFormat(final Date date, final String pattern) {
        return getDateFormat(pattern).format(date);
    }

    /**
     *  日期字符串解析为日期
     * @param date 日期字符串
     * @param pattern 日期格式
     * @return 日期
     * @throws ParseException
     */
    public static Date dateParse(final String date, String pattern) throws ParseException {
        return getDateFormat(pattern).parse(date);
    }

    /**
     * 日期字符串格式的转换，例如"2018-12-02 20:23:34"转换为"20181202202334"，反之亦然
     *
     * @param date        给定日期字符串
     * @param patternFrom 日期字符串的格式
     * @param patternTo   要转换成的日期字符串的格式
     * @return 转换后的日期字符串
     * @throws ParseException
     */
    public static String datePatternExchange(String date, String patternFrom, String patternTo) throws ParseException {
        return dateFormat(dateParse(date, patternFrom), patternTo);
    }

    /**
     * 计算日期偏移量，例如计算参考日期的前一天日期或前一个月或后一个月的日期等
     *
     * @param date     参考日期
     * @param dateUnit 日期粒度：年(Calendar.YEAR)、月(Calendar.MONTH)、日(DAY_OF_MONTH)、时(Calendar.HOUR)、分( Calendar.MINUTE)、秒(Calendar.SECOND)等
     * @param offset   偏移量，正数时计算给定日期往后日期，负数是计算给定日期之前的日期
     * @return 偏移日期
     * @see java.util.Calendar
     */
    public static Date offsetDate(Date date, int dateUnit, int offset) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(dateUnit, offset);
        return c.getTime();
    }

    private static SimpleDateFormat getDateFormat(final String pattern) {
        // 先从本地变量中获取日期格式
        Map<String, SimpleDateFormat> tmpSdfMap = sdfMap.get();
        SimpleDateFormat existedSdf = tmpSdfMap.get(pattern);
        // 加入不存在的日期格式
        if (existedSdf == null) {
            existedSdf = new SimpleDateFormat(pattern);
            tmpSdfMap.put(pattern, existedSdf);
        }
        return existedSdf;
    }

}
