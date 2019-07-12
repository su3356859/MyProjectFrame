package com.demo.android.utils;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by NXN on 2016/7/20.
 */
public class NUtils {
    /**
     * 把Double价钱小数点后截取掉
     *
     */
    public static String getPrice(Double price) {
        String s = String.valueOf(price);
        return s.substring(0,s.indexOf("."));
    }

    /**
     * 格式化
     */
    private static DecimalFormat dfs = null;

    public static DecimalFormat format(String pattern) {
        if (dfs == null) {
            dfs = new DecimalFormat();
        }
        dfs.setRoundingMode(RoundingMode.FLOOR);
        dfs.applyPattern(pattern);
        return dfs;
    }

    public static String getTime(String beginTime, String endTime) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat df2 = new SimpleDateFormat("HH:mm:ss");

        java.util.Date now = null;
        java.util.Date date = null;
        try {
            now = df.parse(endTime);
            date = df.parse(beginTime);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        long l = now.getTime() - date.getTime();
        long day=l/(24*60*60*1000);
        long hour=(l/(60*60*1000)-day*24);
        long min=((l/(60*1000))-day*24*60-hour*60);
        long s=(l/1000-day*24*60*60-hour*60*60-min*60);
        String hours,mins,ss;
       if(hour/10 == 0){
           hours = "0"+ hour;
       }else{
           hours = ""+hour;
       }
        if(min/10 == 0){
            mins = "0"+ min;
        }else{
            mins = ""+min;
        }
        if(s/10 == 0){
            ss = "0"+ s;
        }else{
            ss = ""+s;
        }
        String str = hours+":"+mins+":"+ss;
       // System.out.println(""+day+"天"+hour+"小时"+min+"分"+s+"秒");
        return str;
    }
}
