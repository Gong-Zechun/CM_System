package com.util;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateUtil {

    /**
     * 下月最后一天
     *
     * @return
     */
    public static String getLastDayOfNextMonth() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, 1);
        // 下个月最后一天
        calendar.add(Calendar.MONTH, 1); // 加一个月
        calendar.set(Calendar.DATE, 1); // 设置为该月第一天
        calendar.add(Calendar.DATE, -1); // 再减一天即为上个月最后一天
        String day_last = df.format(calendar.getTime());
        StringBuffer endStr = new StringBuffer().append(day_last);
        day_last = endStr.toString();
        return day_last;
    }

    /**
     * 当月最后一天
     *
     * @return
     */
    public static String getLastDayOfTheMonth() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        ;
        // 获取当前月最后一天
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.DAY_OF_MONTH,
                ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        String last = format.format(ca.getTime());
        return last;
    }

    // 用于返回比例日期，其中refDate日期一定要大于当前日期
    public static Date calcByRate(double rate, Date endDate) {
        // 得到与当前时间的差距
        int diff = DateUtil.getIntervalMinute(new Date(), endDate);
        // 计算差距比例
        int diffRate = (int) (diff * rate);
        return DateUtil.dateAfterNMinute(diffRate);
    }

    /**
     * 获取当前日期，使用java.sql.Timestamp格式返回 rich 2012-6-4 上午11:27:07 修改者名字 修改日期 修改内容
     *
     * @return
     * @return java.sql.Timestamp
     * @throws
     */
    public static java.sql.Timestamp getTimestampForToday() {
        return new java.sql.Timestamp(System.currentTimeMillis());
    }

    /**
     * 函数功能说明 peter 2012-7-18 修改者名字 修改日期 修改内容
     *
     * @param @param year
     * @param @param month
     * @param @param date
     * @param @return
     * @return Date
     * @throws
     */
    public static Date createDate(int year, int month, int date) {
        Calendar c = Calendar.getInstance();
        c.set(year, month - 1, date);
        return c.getTime();
    }

    /**
     * 获取日期中的年
     *
     * @param date
     *            日期
     * @return 年份
     */
    public static int getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 将Timestamp转化成字符串 you 2012-8-30 修改者名字 修改日期 修改内容
     *
     * @param @param date
     * @param @return
     * @return String
     * @throws
     */
    public static String timestampToString(Timestamp date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String ss = df.format(date);
        return ss;
    }

    /**
     * 获取日期中的月
     *
     * @param date
     *            日期
     * @return 月份 (1~12)
     */
    public static int getMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取日期(月)
     *
     * @param date
     *            日期
     * @return 天
     */
    public static int getDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return day;
    }

    /**
     * 获取星期天 (1(星期日)~7(星期六)
     *
     * @param date
     *            日期
     * @return 天
     */
    public static int getDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        return day;
    }

    public static Date clone(Date date) {
        return new Date(date.getTime());
    }

    // 1.计算某一月份的最大天数
    public static int ActualMaximum(Date date) {
        Calendar time = Calendar.getInstance();
        time.clear();
        time.set(Calendar.YEAR, getYear(date));
        time.set(Calendar.MONTH, getMonth(date) - 1);// 注意,Calendar对象默认一月为0
        int day = time.getActualMaximum(Calendar.DAY_OF_MONTH);// 本月份的天数
        return day;
    }

    /**
     * 计算两个任意时间中间的间隔天数 miracre 2012-11-6 修改者名字 修改日期 修改内容 
     *
     * @param startday
     * @param endday
     * @return 
     */
    public static int getIntervalDays(Calendar startday, Calendar endday) {
        // 确保startday在endday之前
        if (startday.after(endday)) {
            Calendar cal = startday;
            startday = endday;
            endday = cal;
        }
        // 分别得到两个时间的毫秒数
        long sl = startday.getTimeInMillis();
        long el = endday.getTimeInMillis();

        long ei = el - sl;
        // 根据毫秒数计算间隔天数
        return (int) (ei / (1000 * 60 * 60 * 24));
    }

    /**
     * 计算两个任意时间中间的间隔分钟数 miracre 2012-11-6 修改者名字 修改日期 修改内容 
     *
     * @param start
     * @param end
     * @return 
     */
    public static int getIntervalMins(Date start, Date end) {
        Calendar startday = Calendar.getInstance();
        startday.setTime(start);
        Calendar endday = Calendar.getInstance();
        endday.setTime(end);
        // 确保startday在endday之前
        if (startday.after(endday)) {
            Calendar cal = startday;
            startday = endday;
            endday = cal;
        }
        // 分别得到两个时间的毫秒数
        long sl = startday.getTimeInMillis();
        long el = endday.getTimeInMillis();

        long ei = el - sl;
        // 根据毫秒数计算间隔天数
        return (int) (ei / (1000 * 60));
    }

    /**
     * 计算两个任意时间中间的间隔分钟数 miracre 2012-11-6 修改者名字 修改日期 修改内容 
     *
     * @param startday
     * @param endday
     * @return 
     */
    public static int getIntervalMins(Calendar startday, Calendar endday) {
        // 确保startday在endday之前
        if (startday.after(endday)) {
            Calendar cal = startday;
            startday = endday;
            endday = cal;
        }
        // 分别得到两个时间的毫秒数
        long sl = startday.getTimeInMillis();
        long el = endday.getTimeInMillis();

        long ei = el - sl;
        // 根据毫秒数计算间隔天数
        return (int) (ei / (1000 * 60));
    }

    public static int getIntervalHours(Date start, Date end) {
        Calendar startday = Calendar.getInstance();
        startday.setTime(start);
        Calendar endday = Calendar.getInstance();
        endday.setTime(end);
        // 确保startday在endday之前
        if (startday.after(endday)) {
            Calendar cal = startday;
            startday = endday;
            endday = cal;
        }
        // 分别得到两个时间的毫秒数
        long sl = startday.getTimeInMillis();
        long el = endday.getTimeInMillis();

        long ei = el - sl;
        // 根据毫秒数计算间隔小时数
        return (int) (ei / (1000 * 60 * 60));
    }

    public static double getIntervalHoursDouble(Date start, Date end) {
        Calendar startday = Calendar.getInstance();
        startday.setTime(start);
        Calendar endday = Calendar.getInstance();
        endday.setTime(end);
        // 确保startday在endday之前
        if (startday.after(endday)) {
            Calendar cal = startday;
            startday = endday;
            endday = cal;
        }
        // 分别得到两个时间的毫秒数
        long sl = startday.getTimeInMillis();
        long el = endday.getTimeInMillis();

        long ei = el - sl;
        // 根据毫秒数计算间隔小时数
        BigDecimal b =
                new BigDecimal(ei).divide(
                        (new BigDecimal(1000).multiply(new BigDecimal(60).multiply(new BigDecimal(60)))), 1, BigDecimal.ROUND_HALF_EVEN);
        return b.doubleValue();
    }

    // (2)传进Date对象
    /**
     * 计算两个时间之间相隔天数
     *
     * @param startday
     *            开始时间
     * @param endday
     *            结束时间
     * @return
     */
    public static int getIntervalDays(Date startday, Date endday) {
        // 确保startday在endday之前
        if (startday.after(endday)) {
            Date cal = startday;
            startday = endday;
            endday = cal;
        }
        // 分别得到两个时间的毫秒数
        long sl = startday.getTime();
        long el = endday.getTime();

        long ei = el - sl;
        // 根据毫秒数计算间隔天数
        return (int) (ei / (1000 * 60 * 60 * 24));
    }

    public static long getIntervalHm(Date startday, Date endday) {
        // 确保startday在endday之前
        if (startday.after(endday)) {
            Date cal = startday;
            startday = endday;
            endday = cal;
        }
        // 分别得到两个时间的毫秒数
        long sl = startday.getTime();
        long el = endday.getTime();

        long ei = el - sl;
        // 根据毫秒数计算间隔分钟数
        return ei;
    }

    public static int getIntervalMinute(Date startday, Date endday) {
        // 确保startday在endday之前
        if (startday.after(endday)) {
            Date cal = startday;
            startday = endday;
            endday = cal;
        }
        // 分别得到两个时间的毫秒数
        long sl = startday.getTime();
        long el = endday.getTime();

        long ei = el - sl;
        // 根据毫秒数计算间隔分钟数
        return (int) (ei / (1000 * 60));
    }

    public static long getIntervalHour(Date startday, Date endday) {
        // 确保startday在endday之前
        if (startday.after(endday)) {
            Date cal = startday;
            startday = endday;
            endday = cal;
        }
        // 分别得到两个时间的毫秒数
        long sl = startday.getTime();
        long el = endday.getTime();

        long ei = el - sl;
        // 根据毫秒数计算间隔
        return (int) (ei / 1000);
    }

    /**
     * 同上理，可以用相同的方法计算出任意两个时间相隔的小时数，分钟数，秒钟数等 注：以上方法是完全按时间计算，有时并不能令人满意，如：
     * startday="2006-10-11 20:00:00" endday="2006-10-12 8:00:00"
     * 计算结果为0，但是我们也许相让计算结果变为1，此时可以用如下方法实现： 在传参之前，先设定endday的时间，如：
     * endday.set(Calendar.HOUR_OF_DAY, 23); endday.set(Calendar.MINUTE, 59);
     * endday.set(Calendar.SECOND, 59); endday.set(Calendar.MILLISECOND, 59);
     * 这样再传进去startday,endday，则结果就如我们所愿了。不过，如果嫌以上方法麻烦，可以参考以下方法：
     */
    // (3)改进精确计算相隔天数的方法
    public int getDaysBetween(Calendar d1, Calendar d2) {
        if (d1.after(d2)) {
            // swap dates so that d1 is start and d2 is end
            java.util.Calendar swap = d1;
            d1 = d2;
            d2 = swap;
        }
        int days = d2.get(Calendar.DAY_OF_YEAR) - d1.get(Calendar.DAY_OF_YEAR);
        int y2 = d2.get(Calendar.YEAR);
        if (d1.get(Calendar.YEAR) != y2) {
            d1 = (Calendar) d1.clone();
            do {
                days += d1.getActualMaximum(Calendar.DAY_OF_YEAR);// 得到当年的实际天数
                d1.add(Calendar.YEAR, 1);
            } while (d1.get(Calendar.YEAR) != y2);
        }
        return days;
    }

    public static int getDaysBetween(Date startday, Date endday) {
        Calendar d1 = Calendar.getInstance();
        d1.setTime(startday);

        Calendar d2 = Calendar.getInstance();
        d2.setTime(endday);

        if (d1.after(d2)) {
            // swap dates so that d1 is start and d2 is end
            java.util.Calendar swap = d1;
            d1 = d2;
            d2 = swap;
        }
        int days = d2.get(Calendar.DAY_OF_YEAR) - d1.get(Calendar.DAY_OF_YEAR);
        int y2 = d2.get(Calendar.YEAR);
        if (d1.get(Calendar.YEAR) != y2) {
            d1 = (Calendar) d1.clone();
            do {
                days += d1.getActualMaximum(Calendar.DAY_OF_YEAR);// 得到当年的实际天数
                d1.add(Calendar.YEAR, 1);
            } while (d1.get(Calendar.YEAR) != y2);
        }
        return days;
    }

    // 获取系统当前时间：
    public static String getSystemTime() {
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(date);
    }

    public static String getSystemTime(String format) {
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(date);
    }

    // 获取系统当前时间：
    public static String getSortSystemTime() {
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(date);
    }

    public static Date getSystemDate() {
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String s = df.format(date);

        Date d2 = null;
        try {
            d2 = df.parse(s);
        } catch (ParseException e) {

            e.printStackTrace();
        }

        return d2;
    }


    public static Date getDateyyyyMMddHHmmss() {
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");

        String s = df.format(date);

        Date d2 = null;
        try {
            d2 = df.parse(s);
        } catch (ParseException e) {

            e.printStackTrace();
        }

        return d2;
    }

    public static Date getSortSystemDate() throws ParseException {
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        String s = df.format(date);

        Date d2 = df.parse(s);

        return d2;
    }

    public static String getDateTimeByFormat(Long time, String format) {
        if (time == null || time == 0l) {
            return "";
        } else {
            Date utilDate = new java.util.Date(time);
            SimpleDateFormat df = new SimpleDateFormat(format);
            return df.format(utilDate);
        }
    }

    public static String getDateTime(Long time) {
        if (time == null || time == 0l) {
            return "";
        } else {
            Date utilDate = new java.util.Date(time);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return df.format(utilDate);
        }
    }

    public static String getDateStr(Date time, String format) {
        if (time == null) {
            return "";
        } else {
            SimpleDateFormat df = new SimpleDateFormat(format);
            return df.format(time);
        }
    }

    public static String getDateTime(Date time) {
        return getDateStr(time, "yyyy-MM-dd HH:mm:ss");
    }

    //
    public static String getSortDateTime(long time) {
        Date utilDate = new java.util.Date(time);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(utilDate);
    }

    public static Date getSortDate(long time) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date utilDate = new java.util.Date(time);

        String s = df.format(utilDate);
        Date d2 = null;
        try {
            d2 = df.parse(s);
        } catch (ParseException e) {

            e.printStackTrace();
        }
        return d2;
    }

    // Calendar转化为Date
    public static Date getDateByCalendar() {
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();

        // SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
        // java.util.Date date= myFormatter.parse("2003-05-1");
        // SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return date;
    }

    // Calendar转化为Date
    public static Calendar getCalendarByDate() {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }

    // 字符串转化成时间类型（字符串可以是任意类型，只要和SimpleDateFormat中的格式一致即可）

    // java.text.SimpleDateFormat sdf = new
    // java.text.SimpleDateFormat("M/dd/yyyy hh:mm:ss a",java.util.Locale.US);
    //
    // java.util.Date d = sdf.parse("5/13/2003 10:31:37 AM");
    //
    //
    // SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    //
    // String mDateTime1=formatter.format(d);

    // 当前时间

    // Calendar cal = Calendar.getInstance();
    //
    // // SimpleDateFormat formatter = new
    // SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    //
    // SimpleDateFormat formatter = new
    // SimpleDateFormat("yyyy-MM-dd HH:mm:ss G E D F w W a E F");
    //
    // String mDateTime=formatter.format(cal.getTime());

    // 1年前日期

    // java.util.Date myDate=new java.util.Date();
    //
    // long myTime=(myDate.getTime()/1000)-60*60*24*365;
    //
    // myDate.setTime(myTime*1000);
    //
    // String mDate=formatter.format(myDate);

    // 明天日期

    // myDate=new java.util.Date();
    //
    // myTime=(myDate.getTime()/1000)+60*60*24;
    //
    // myDate.setTime(myTime*1000);
    //
    // mDate=formatter.format(myDate);

    // 两个时间之间的天数

    // SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
    //
    // java.util.Date date= myFormatter.parse("2003-05-1");
    //
    // java.util.Date mydate= myFormatter.parse("1899-12-30");
    //
    // long day=(date.getTime()-mydate.getTime())/(24*60*60*1000);

    // 加半小时

    // SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    //
    // java.util.Date date1 = format.parse("2002-02-28 23:16:00");
    //
    // long Time=(date1.getTime()/1000)+60*30;
    //
    // date1.setTime(Time*1000);
    //
    // String mydate1=formatter.format(date1);

    // 年月周求日期

    // SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM F E");
    //
    // java.util.Date date2= formatter2.parse("2003-05 5 星期五");
    //
    // SimpleDateFormat formatter3 = new SimpleDateFormat("yyyy-MM-dd");
    //
    // String mydate2=formatter3.format(date2);

    // 求是星期几

    // mydate= myFormatter.parse("2001-1-1");
    //
    // SimpleDateFormat formatter4 = new SimpleDateFormat("E");
    //
    // String mydate3=formatter4.format(mydate);
    //
    // 在开发web应用中，针对不同的数据库日期类型，我们需要在我们的程序中对日期类型做各种不同的转换。
    // 若对应数据库数据是oracle的Date类型，即只需要年月日的，可以选择使用java.sql.Date类型，
    // 若对应的是MSsqlserver数据库的DateTime类型，即需要年月日时分秒的，选择java.sql.Timestamp类型
    // 你可以使用dateFormat定义时间日期的格式，转一个字符串即可

    /**
     * method 将字符串类型的日期转换为一个timestamp（时间戳记java.sql.Timestamp）
     *
     * @param dateString
     *            需要转换为timestamp的字符串
     * @return dataTime timestamp
     */
    public final static java.sql.Timestamp string2Time(String dateString) {
        if (dateString == null || "".equals(dateString)) {
            return null;
        }
        DateFormat dateFormat;
        dateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss.SSS",
                Locale.ENGLISH);// 设定格式
        // dateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss",
        // Locale.ENGLISH);
        dateFormat.setLenient(false);
        java.util.Date timeDate = null;
        java.sql.Timestamp dateTime = null;
        try {
            timeDate = dateFormat.parse(dateString);
            dateTime = new java.sql.Timestamp(timeDate.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }// util类型
        // Timestamp类型,timeDate.getTime()返回一个long型

        return dateTime;
    }

    /**
     * method 将字符串类型的日期转换为一个Date（java.sql.Date）
     *
     * @param dateString
     *            需要转换为Date的字符串
     * @return dataTime Date
     */
    public final static java.sql.Date string2Date(String dateString) {
        if (dateString == null || "".equals(dateString)) {
            return null;
        }
        DateFormat dateFormat;
        dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false);
        java.util.Date timeDate = null;
        java.sql.Date dateTime = null;
        try {
            timeDate = dateFormat.parse(dateString);
            dateTime = new java.sql.Date(timeDate.getTime());// sql类型
        } catch (ParseException e) {
            e.printStackTrace();
        }// util类型

        return dateTime;
    }

    /**
     * method 将字符串类型的日期转换为一个Date（java.sql.Date）
     *
     * @param dateString
     *            需要转换为Date的字符串
     * @return dataTime Date
     */
    public final static java.sql.Date sortString2Date(String dateString) {
        if (dateString == null || "".equals(dateString)) {
            return null;
        }
        DateFormat dateFormat;
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        java.util.Date timeDate = null;
        java.sql.Date dateTime = null;
        try {
            timeDate = dateFormat.parse(dateString);
            dateTime = new java.sql.Date(timeDate.getTime());// sql类型
        } catch (ParseException e) {

            e.printStackTrace();
        }// util类型

        return dateTime;
    }

    public static boolean isdate(String s) {
        String a[] = s.split("-");
        boolean flg = true;
        if (!(Integer.parseInt(a[0]) >= 1950 && Integer.parseInt(a[0]) <= 2050)) {
            flg = false;
        }
        return flg;
    }

    public static boolean checkDate(String s) {
        boolean ret = true;
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            ret = df.format(df.parse(s)).equals(s);
        } catch (ParseException e) {
            ret = false;
        }
        return ret;
    }

    public Object dateinfo(String s) {
        String a[] = s.split("-", 2);
        Hashtable fest = new Hashtable();
        fest.put("01-01", "元旦节");
        fest.put("02-14", "情人节");
        fest.put("03-12", "植树节");
        fest.put("03-15", "消费者节");
        fest.put("04-01", "愚人节");
        fest.put("04-05", "清明节");
        fest.put("05-01", "劳动节");
        fest.put("06-01", "儿童节");
        fest.put("07-01", "建党节");
        fest.put("08-01", "建军节");
        fest.put("09-10", "教师节");
        fest.put("10-01", "国庆节");
        fest.put("12-25", "圣诞节");
        if (fest.containsKey(a[1])) {
            return fest.get(a[1]);
        } else {
            return "无节日";
        }
    }

    public String xingzuo(Date s) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(s);
        String xingzuo = "无";
        int day = cal.get(Calendar.DAY_OF_YEAR);
        if ((cal.get(Calendar.YEAR) % 4 == 0)
                && (cal.get(Calendar.YEAR) % 100 != 0)
                || (cal.get(Calendar.YEAR) % 400 == 0)) {
            if ((day >= 1 && day <= 19) || (day >= 357 && day <= 366)) {
                xingzuo = "魔蝎座";
            } else if (day >= 20 && day <= 49) {
                xingzuo = "水瓶座";
            } else if (day >= 50 && day <= 80) {
                xingzuo = "双鱼座";
            } else if (day >= 81 && day <= 110) {
                xingzuo = "白羊座";
            } else if (day >= 111 && day <= 141) {
                xingzuo = "金牛座";
            } else if (day >= 142 && day <= 173) {
                xingzuo = "双子座";
            } else if (day >= 174 && day <= 203) {
                xingzuo = "巨蟹座";
            } else if (day >= 204 && day <= 235) {
                xingzuo = "狮子座";
            } else if (day >= 236 && day <= 266) {
                xingzuo = "处女座";
            } else if (day >= 267 && day <= 296) {
                xingzuo = "天秤座";
            } else if (day >= 297 && day <= 326) {
                xingzuo = "天蝎座";
            } else if (day >= 327 && day <= 356) {
                xingzuo = "射手座";
            }
        } else {
            if ((day >= 1 && day <= 19) || (day >= 357 && day <= 366)) {
                xingzuo = "魔蝎座";
            } else if (day >= 20 && day <= 48) {
                xingzuo = "水瓶座";
            } else if (day >= 49 && day <= 79) {
                xingzuo = "双鱼座";
            } else if (day >= 80 && day <= 109) {
                xingzuo = "白羊座";
            } else if (day >= 110 && day <= 140) {
                xingzuo = "金牛座";
            } else if (day >= 141 && day <= 172) {
                xingzuo = "双子座";
            } else if (day >= 173 && day <= 202) {
                xingzuo = "巨蟹座";
            } else if (day >= 203 && day <= 234) {
                xingzuo = "狮子座";
            } else if (day >= 235 && day <= 265) {
                xingzuo = "处女座";
            } else if (day >= 266 && day <= 295) {
                xingzuo = "天秤座";
            } else if (day >= 296 && day <= 325) {
                xingzuo = "天蝎座";
            } else if (day >= 326 && day <= 355) {
                xingzuo = "射手座";
            }
        }
        return xingzuo;
    }

    public Date parseDate(String s) {
        SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date3 = bartDateFormat.parse(s);
            date3 = bartDateFormat.parse(s);
            return date3;
        } catch (Exception ex) {
            return null;
        }
    }

    public static Date parseDate(String s, String format) {
        SimpleDateFormat bartDateFormat = new SimpleDateFormat(format);
        try {
            Date date3 = bartDateFormat.parse(s);
            date3 = bartDateFormat.parse(s);
            return date3;
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * 根据日期获得星期几
     *
     * @param dt
     * @return
     */
    public static String getWeekOfDate(Date dt) {
        String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);

        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];

    }

    // 判断两个日期是否在同一周
    boolean isSameWeekDates(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);
        int subYear = cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);
        if (0 == subYear) {
            if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2
                    .get(Calendar.WEEK_OF_YEAR))
                return true;
        } else if (1 == subYear && 11 == cal2.get(Calendar.MONTH)) {
            // 如果12月的最后一周横跨来年第一周的话则最后一周即算做来年的第一周
            if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2
                    .get(Calendar.WEEK_OF_YEAR))
                return true;
        } else if (-1 == subYear && 11 == cal1.get(Calendar.MONTH)) {
            if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2
                    .get(Calendar.WEEK_OF_YEAR))
                return true;
        }
        return false;
    }

    // 产生周序列
    public static String getSeqWeek() {
        Calendar c = Calendar.getInstance(Locale.CHINA);
        String week = Integer.toString(c.get(Calendar.WEEK_OF_YEAR));
        if (week.length() == 1)
            week = "0" + week;
        String year = Integer.toString(c.get(Calendar.YEAR));
        return year + week;

    }

    // 获得周一的日期
    public static String getMonday(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
    }

    // 获得周五的日期
    public static String getFriday(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
        return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
    }

    // 当前日期前几天或者后几天的日期
    public static String afterNDay(int n) {
        Calendar c = Calendar.getInstance();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        c.setTime(new Date());
        c.add(Calendar.DATE, n);
        Date d2 = c.getTime();

        String s = df.format(d2);
        return s;
    }

    /**
     * 某个日期前几天或者后几天的日期(n为数值，timeunit为时间单位) miracre 2012-11-6 修改者名字 修改日期 修改内容 
     *
     * @param date
     * @param n
     * @param timeunit
     * @return 
     */
    public static Date afterNDay(Date date, int n, String timeunit) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        if (timeunit.equals("年")) {
            c.add(Calendar.YEAR, n);
        } else if (timeunit.equals("月")) {
            c.add(Calendar.MONTH, n);
        } else if (timeunit.equals("日") || timeunit.equals("天")) {
            c.add(Calendar.DATE, n);
        } else if (timeunit.equals("时")) {
            c.add(Calendar.HOUR, n);
        } else if (timeunit.equals("分")) {
            c.add(Calendar.MINUTE, n);
        } else if (timeunit.equals("秒")) {
            c.add(Calendar.SECOND, n);
        }

        return c.getTime();
    }

    public static String DayafterNDay(Date date, int n, String timeunit) {
        Calendar c = Calendar.getInstance();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        c.setTime(date);
        if (timeunit.equals("年")) {
            c.add(Calendar.YEAR, n);
        } else if (timeunit.equals("月")) {
            c.add(Calendar.MONTH, n);
        } else if (timeunit.equals("日") || timeunit.equals("天")) {
            c.add(Calendar.DATE, n);
        } else if (timeunit.equals("时")) {
            c.add(Calendar.HOUR, n);
        } else if (timeunit.equals("分")) {
            c.add(Calendar.MINUTE, n);
        } else if (timeunit.equals("秒")) {
            c.add(Calendar.SECOND, n);
        }
        Date d2 = c.getTime();

        String s = df.format(d2);
        return s;
    }

    public static String DayafterNDay(Date date, int n) {
        Calendar c = Calendar.getInstance();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        c.setTime(date);
        c.add(Calendar.DATE, n);
        Date d2 = c.getTime();

        String s = df.format(d2);
        return s;
    }

    public static Date dateAfterNDay(int n) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, n);
        Date d2 = c.getTime();
        return d2;
    }

    public static Date dateAfterNMinute(int n) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.MINUTE, n);
        Date d2 = c.getTime();
        return d2;
    }

    public static Date dateAfterNMinute(Date date, int n) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MINUTE, n);
        Date d2 = c.getTime();
        return d2;
    }

    public static Date sortDateAfterNDay(int n) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, n);
        Date d2 = c.getTime();
        String s = df.format(d2);
        d2 = df.parse(s);

        return d2;
    }

    public static String sortDateAfterNMonth(int n) throws ParseException {
        Calendar c = Calendar.getInstance();
        DateFormat df = new SimpleDateFormat("yyyy-MM");
        c.setTime(new Date());
        c.add(Calendar.MONTH, n);
        Date d2 = c.getTime();

        String s = df.format(d2);
        return s;
    }

    public static String sortDateBeforeNMonth(int n) throws ParseException {
        Calendar c = Calendar.getInstance();
        DateFormat df = new SimpleDateFormat("yyyy-MM");
        c.setTime(new Date());
        c.add(Calendar.MONTH, -n);
        Date d2 = c.getTime();
        String s = df.format(d2);
        return s;
    }

    /**
     * 将长时间格式字符串转换为时间 yyyy-MM-dd HH:mm:ss
     *
     * @param strDate
     * @return
     */
    public static Date strToDateLong(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }

    /**
     * 将长时间格式时间转换为字符串 yyyy-MM-dd HH:mm:ss
     *
     * @param dateDate
     * @return
     */
    public static String dateToStrLong(java.util.Date dateDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(dateDate);
        return dateString;
    }

    /**
     * 将短时间格式时间转换为字符串 yyyy-MM-dd （示例：Tue Nov 23 00:00:00 CST 2004 转换为 2004-11-23）
     * @param dateDate
     * @return
     */
    public static String dateToStr(Date dateDate) {
        if(dateDate == null){
            return "";
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = formatter.format(dateDate);
        return dateStr;
    }

    public static String dateToLongStr(java.util.Date dateDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String dateString = formatter.format(dateDate);
        return dateString;
    }

    /**
     * 将短时间格式字符串转换为时间 yyyy-MM-dd
     *
     * @param strDate
     * @return
     */
//    public static Date strToDate(String strDate) {
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//        ParsePosition pos = new ParsePosition(0);
//        Date strtodate = formatter.parse(strDate, pos);
//        return strtodate;
//    }

    public static Date strToDate(String strDate) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date strtodate = formatter.parse(strDate);
        return strtodate;
    }

    public static boolean compareDate(String now, String end) {
        java.text.DateFormat df = new java.text.SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        java.util.Calendar c1 = java.util.Calendar.getInstance();
        java.util.Calendar c2 = java.util.Calendar.getInstance();
        try {
            c1.setTime(df.parse(now));
            c2.setTime(df.parse(end));
        } catch (java.text.ParseException e) {
            // System.err.println("格式不正确");
            return false;
        }
        int result = c1.compareTo(c2);
        if (result == 0) {
            // //System.out.println("c1相等c2");
            return false;
        } else if (result < 0) {
            // //System.out.println("c1小于c2");
            return true;
        } else {
            // //System.out.println("c1大于c2");
            return false;
        }
    }

    /**
     * 比较日期（不精确到时分秒） date1是否小于date2 miracre 2012-11-19 修改者名字 修改日期 修改内容
     *
     * @return boolean
     * @throws
     */
    public static boolean compareDateFormatDay(String date1, String date2) {
        java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");
        java.util.Calendar c1 = java.util.Calendar.getInstance();
        java.util.Calendar c2 = java.util.Calendar.getInstance();
        try {
            c1.setTime(df.parse(date1));
            c2.setTime(df.parse(date2));
        } catch (java.text.ParseException e) {
            // System.err.println("格式不正确");
            return false;
        }
        int result = c1.compareTo(c2);
        if (result == 0) {
            // //System.out.println("c1相等c2");
            return false;
        } else if (result < 0) {
            // //System.out.println("c1小于c2");
            return true;
        } else {
            // //System.out.println("c1大于c2");
            return false;
        }
    }

    /**
     * 得到剩余时间 按照XX小时XX分
     *
     * @param now
     *            当前时间
     * @param end
     *            项目要求提交的时间
     * @return
     */
    public static String getSurplusDate(String now, String end) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            if (compareDate(now, end)) {
                java.util.Date begin1 = df.parse(now);
                java.util.Date end1 = df.parse(end);

                long between = (end1.getTime() - begin1.getTime()) / 1000;// 除以1000是为了转换成秒
                long day1 = between / (24 * 3600);
                long minute1 = between % 3600 / 60;
                // long second1=between%60/60;
                long hour2 = ((end1.getTime() - begin1.getTime()) / (60 * 60 * 1000));
                if (day1 > 0) {
                    return day1 + "天";
                }
                return hour2 + "小时" + minute1 + "分";
            } else {
                return "0分";
            }
        } catch (java.text.ParseException e) {
            return "0分";
        }
    }

    /**
     * 得到剩余时间有多少秒
     *
     * @param now
     *            当前时间
     * @param end
     *            项目要求提交的时间
     * @return
     */
    public static String getSurplusSecond(String now, String end) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            if (compareDate(now, end)) {
                java.util.Date begin1 = df.parse(now);
                java.util.Date end1 = df.parse(end);

                long between = (end1.getTime() - begin1.getTime()) / 1000;// 除以1000是为了转换成秒
                return between + "";
            } else {
                return "0";
            }
        } catch (java.text.ParseException e) {
            return "0";
        }
    }

    /**
     * 得到剩余时间有XX天XX时分XX秒
     *
     * @param now
     *            当前时间
     * @param end
     *            项目要求提交的时间
     * @return
     */
    public static String getSurplusDay(String now, String end) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            if (compareDate(now, end)) {
                java.util.Date begin1 = df.parse(now);
                java.util.Date end1 = df.parse(end);

                long between = (end1.getTime() - begin1.getTime()) / 1000;// 除以1000是为了转换成秒
                long day = between / (24 * 3600);
                long hour = between % (24 * 3600) / 3600;
                // long hour2=((end1.getTime()-begin1.getTime())/(60*60*1000));
                long minute = between % 3600 / 60;
                long second = between % 60 / 60;
                return "" + day + "天" + hour + "小时" + minute + "分" + second
                        + "秒";
            } else {
                return "0";
            }
        } catch (java.text.ParseException e) {
            return "0";
        }
    }

    /**
     * 将分钟数格式化为XX天XX小时XX分 miracre 2012-11-6 修改者名字 修改日期 修改内容 
     *
     * @param mins
     * @param workHours
     * @return 
     */
    public static String getFormatMins(int mins, int workHours) {
        String str = "";
        int day = mins / (workHours * 60);
        str += day == 0 ? "" : day + "天";
        int hour = mins % (workHours * 60) / 60;
        str += hour == 0 ? "" : hour + "小时";
        int minute = mins % 60;
        str += minute == 0 ? "" : minute + "分";
        return str;
    }

    /* 获取据今天起iCount天的日期 iCount为正为以后 iCount为负为以前 */
    public static String getNeedDate(int iCount) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, iCount);
        Date d = calendar.getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(d);
    }

    /* 获取据今天起iCount天的日期 iCount为正为以后 iCount为负为以前 */
    public static String getNeedMonthDate(int iCount) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, iCount);
        Date d = calendar.getTime();
        SimpleDateFormat df = new SimpleDateFormat("MM-dd");
        return df.format(d);
    }

    /* 获取据date起iCount天的日期 iCount为正为以后 iCount为负为以前 */
    public static String getNeedMonthDate(int iCount, String date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date dt = df.parse(date, new ParsePosition(0));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dt);
        calendar.add(Calendar.DATE, iCount);
        Date d = calendar.getTime();
        return df.format(d);
    }

    /* 获取当前月(下个月的第一天)与相差前nCount月 */
    public static String[] getNeedMonth(int nCount) {
        String[] date = new String[2];
        Calendar now = Calendar.getInstance();
        int nowYear = now.get(Calendar.YEAR); // 当前年
        int nowMonth = now.get(Calendar.MONTH) + 1; // 当前月
        if ((nowMonth - nCount) > 0) {
            date[0] = nowYear + "-" + (nowMonth - nCount) + "-" + "01";
        } else if ((nowMonth - nCount) <= 0) {
            int n = (nowMonth - nCount) / 12;
            int m = (nowMonth - nCount) % 12;
            date[0] = (nowYear + n - 1) + "-" + (12 + m + 1) + "-01";
        }
        if ((nowMonth + 1) <= 12) {
            date[1] = nowYear + "-" + (nowMonth + 1) + "-" + "01";
        } else {
            date[1] = (nowYear + 1) + "-01-01";
        }
        return date;
    }

    // 获取当前月
    public static int getMonth() {
        Calendar now = Calendar.getInstance();
        return now.get(Calendar.MONTH) + 1; // 当前月
    }

    // 获取当前年
    public static int getYear() {
        Calendar now = Calendar.getInstance();
        int nowYear = now.get(Calendar.YEAR); // 当前年
        return nowYear;
    }

    // 根据月份获取前qs个季度共有多少月
    public static int getpreQuarter(int month, int qs) {
        int a = 0;
        if (month == 1 || month == 4 || month == 7 || month == 10) {
            a = 3 * (qs - 1);
        }
        if (month == 2 || month == 5 || month == 8 || month == 1) {
            a = 3 * (qs - 1) + 1;
        }
        if (month == 3 || month == 6 || month == 9 || month == 12) {
            a = 3 * qs - 1;
        }
        return a;
    }

    /**
     * 根据当前日期获取前N个月的日期所在的年份季度
     *
     * @return
     */
    public static String getPreMonthDateQuary(int month) {
        SimpleDateFormat df = new SimpleDateFormat("MM");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, month);// 对月份进行计算,减去12个月
        Date date = cal.getTime();
        String s = df.format(date);
        int jd = Integer.parseInt(s) / 4 + 1;

        return getPreMonthDateYear(month) + "-" + jd;// +"季度";

    }

    public static String getPreMonthDateYear(int month) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, month);// 对月份进行计算,减去12个月
        Date date = cal.getTime();
        return df.format(date);
    }

    /* 获取日期所在周 */
    public static String getWeekByDate(Date date) {
        Calendar c = Calendar.getInstance(Locale.CHINA);
        c.setTime(date);
        String week = Integer.toString(c.get(Calendar.WEEK_OF_YEAR));
        if (week.length() == 1)
            week = "0" + week;
        String year = Integer.toString(c.get(Calendar.YEAR));
        return year + week;
    }

    public static String getXCMonth(int nCount) {
        String s = "";
        Calendar now = Calendar.getInstance();
        int nowYear = now.get(Calendar.YEAR); // 当前年
        int nowMonth = now.get(Calendar.MONTH) + 1; // 当前月
        if ((nowMonth - nCount) > 0) {
            s = nowYear + "-" + yiwei2liangwei(nowMonth - nCount) + "";
        } else if ((nowMonth - nCount) <= 0) {
            s = (nowYear - 1) + "-" + yiwei2liangwei(12 + (nowMonth - nCount))
                    + "";
        }
        return s;
    }

    private static String yiwei2liangwei(int num) {
        String s = "";
        if (num < 10) {
            s = "0" + num;
        } else {
            s = num + "";
        }
        return s;
    }

    public static String fmtDate(Timestamp time) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(time);
        return dateString;
    }

    // public static void main(String[] args) throws ParseException {
    // SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    // java.util.Date start = df.parse("2004-01-25 13:31:34");
    // java.util.Date end=df.parse("2004-01-026 13:35:53");
    // //System.out.println(getIntervalMinute(start,end));
    //
    // // SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    // // java.util.Date now = df.parse("2004-03-26 13:31:34");
    // // java.util.Date date=df.parse("2004-01-02 11:30:53");
    // long l=end.getTime()-start.getTime();
    // long day=l/(24*60*60*1000);
    // long hour=(l/(60*60*1000)-day*24);
    // long min=((l/(60*1000))-day*24*60-hour*60);
    // long s=(l/1000-day*24*60*60-hour*60*60-min*60);
    // //System.out.println(""+day+"天"+hour+"小时"+min+"分"+s+"秒");
    //
    // java.util.Date begin1=df.parse("2004-01-02 11:30:24");
    // java.util.Date end1 = df.parse("2004-03-26 13:31:40");

    // java.util.Date begin1=df.parse("2004-01-02 11:30:24");
    // java.util.Date end1 = df.parse("2004-01-02 11:31:24");

    // long between=(end1.getTime()-begin1.getTime())/1000;//除以1000是为了转换成秒
    // long day1=between/(24*3600);
    // long hour1=between%(24*3600)/3600;
    // long hour2=((end1.getTime()-begin1.getTime())/(60*60*1000));
    // long minute1=between%3600/60;
    // long second1=between%60/60;
    // // //System.out.println(""+day1+"天"+hour1+"小时"+minute1+"分"+second1+"秒");
    // // //System.out.println(hour2);

    // //System.out.println(between);
    // //System.out.println(getIntervalHour(begin1,end1));

    // }

    /**
     * method 将字符串类型的日期转换为一个Date
     *
     * @param dateString
     *            需要转换为Date的字符串
     * @return dataTime Date
     */
    public final static Date string2UtilDate(String dateString, String format) {
        if (dateString == null || "".equals(dateString)) {
            return null;
        }
        DateFormat dateFormat;
        dateFormat = new SimpleDateFormat(format);
        dateFormat.setLenient(false);
        Date timeDate = null;
        try {
            timeDate = dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }// util类型

        return timeDate;
    }

    /**
     * 提取字符串中的日期 miracre 2012-10-22 修改者名字 修改日期 修改内容 
     *
     * @param str
     * @return 
     */
    public static String getDateTimeByString(String str) {
        String result = "";
        Pattern p = Pattern.compile("(\\d{4})-(\\d{1,2})-(\\d{1,2})");
        Matcher m = p.matcher(str);
        if (m.find()) {
            result = m.group();
        }
        return result;
    }

    /**
     *
     * 字符串转日期 ShionWong 2013-6-27 修改者名字 修改日期 修改内容
     *
     * @param @param dataString
     * @param @return
     * @return Date
     * @throws
     */
    public static Date parseData(String dataString) {
        String fullFormat = "yyyy-MM-dd HH:mm:ss";
        String noSecondFormat = "yyyy-MM-dd HH:mm";
        String fullDate = "yyyy-MM-dd";
        SimpleDateFormat sdf = null;
        if (dataString == null) {
            return null;
        } else if (dataString.length() == fullFormat.length()) {
            sdf = new SimpleDateFormat(fullFormat);
        } else if (dataString.length() == noSecondFormat.length()) {
            sdf = new SimpleDateFormat(noSecondFormat);
        } else if (dataString.length() == fullDate.length()) {
            sdf = new SimpleDateFormat(fullDate);
        }
        try {
            return sdf.parse(dataString);
        } catch (ParseException e) {

            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param dateYMDHMS
     *            - 日期字符串 （格式为format指定的格式）
     * @param format
     *            - 指定dateYMDHMS的日期格式
     * @param unit
     *            - 日期加减的单位（可以是天数、小时、分钟）
     * @param amount
     *            - 要加减的数量
     * @return 返回加减后的日期 （格式为format指定的格式）
     */
    public static String addDate(String dateYMDHMS, String format, String unit,
                                 double amount) {
        try {
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            //System.out.println(dateYMDHMS);
            calendar.setTime(sdf.parse(dateYMDHMS));
            if (unit.endsWith("day") || unit.endsWith("days")) {
                calendar.add(Calendar.SECOND, (int)(amount*86400));
            } else if (unit.equals("hour") || unit.equals("hours")) {
                calendar.add(Calendar.SECOND, (int)(amount*3600));
            } else if (unit.equals("minute") || unit.equals("minutes")) {
                calendar.add(Calendar.SECOND, (int)(amount*60));
            }

            return sdf.format(calendar.getTime());
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 现有日期上增加小时数
     *
     * @param oldDate
     * @param hour
     * @return Date
     * @throws
     */
    public static Date addHour(Date oldDate, int hour) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(oldDate);
        ca.add(Calendar.HOUR_OF_DAY, hour);
        return ca.getTime();
    }

    /**
     * 现有日期上增加秒
     *
     * @param oldDate
     * @return Date
     * @throws
     */
    public static Date addSecond(Date oldDate, int Second) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(oldDate);
        ca.add(Calendar.SECOND, Second);
        return ca.getTime();
    }

//    public static Date date2Date(Date date) {
//        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
//        String sDate=sdf.format(date);
//        return sDate;
//    }

    public static void main(String[] args) throws ParseException {
        //System.out.println(DateUtil.addDate("2014-02-13 17:00:00",
//				"yyyy-MM-dd", "day", -1));
//		Date d = new Date(new Long(21600000));
//		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        // //System.out.println(sdf.format(d));

        System.out.println(getDateStr(Calendar.getInstance().getTime(), "MM"));
    }
}