package cn.llf.framework.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 创建者：   eleven
 * 创建时间： 2018/1/11
 * 描述：
 */
public class DateUtil {

    public static final int PATTEN_TO_DAY    = 1;
    public static final int PATTEN_TO_SECOND = 2;
    public static SimpleDateFormat sdfToDay    = new SimpleDateFormat("yyyy-MM-dd");
    public static SimpleDateFormat sdfToSecond = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");



    public static Date toDate(String timeStr,int patten){
        Date targetDate = null;
        try {
            if (patten == PATTEN_TO_DAY){
                targetDate = sdfToDay.parse(timeStr);
            }else {
                targetDate = sdfToSecond.parse(timeStr);
            }
        }catch (ParseException e){
            throw new IllegalArgumentException("转换失败，时间参数格式错误");
        }
        return targetDate;
    }
    public static String toString(Date date,int patten){
        String result = null;
        try {
            if (patten == PATTEN_TO_DAY){
                result = sdfToDay.format(date);
            }else {
                result = sdfToSecond.format(date);
            }
        }catch (Exception e){
            throw new IllegalArgumentException("转换失败，时间参数格式错误");
        }

        return result;
    }

    public static String toString(long second,int patten){
        Date date = new Date(second);
        return toString(date,patten);
    }

    /**
     * 判断时间是否在时间段内
     * @param nowTime
     * @param beginTime
     * @param endTime
     * @return 1：早于给定的时间段 2晚于给定的时间段  3在时间范围内
     */
    public static int belongCalendar(Date nowTime, Date beginTime, Date endTime) {
        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(beginTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.before(begin)) return 1;
        if (date.after(end)) return 2;
        return 3;
    }

    /**
     * int 类型秒数转换为hh:mm:ss格式时间
     * @param sec
     * @return
     * @Author huangk
     */
    public static String dateConvert(int sec){
        String date = String.format("%02d:%02d:%02d", sec / 60 / 60 % 60 ,sec / 60 % 60, sec % 60);
        return date;
    }
}
