package com.zhiwu.utils;

import com.thoughtworks.xstream.mapper.Mapper;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.UUID;

/**
 * Created by 韦庆明 on 2016/12/1.
 * 通用工具类
 */
public class MainUtils {

    /**
     * 获取MainUtils的实例化
     * */
    public static MainUtils getUtils()
    {
        return new MainUtils();
    }


    /**
     * 获取当前时间
     * @param format 时间格式：例如yyyy-MM-dd HH:mm:ss
     * @return 格式化后的时间
     */
    public String getSystemTime(String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(System.currentTimeMillis());
    }

    /*
     * 将时间戳转换为时间
     */
    public String stampToDate(String s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }

    /*
    * 将时间转换为时间戳
    */
    public String dateToStamp(String s) throws ParseException {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        res = String.valueOf(ts);
        return res;
    }

    /**
     * 对比两个时间相差
     * begin = 时间A，end = 时间B
     * returnType = 返回的时间差类型（day，hours，minutes，seconds）
     */
    public long getDayTwoTime(String begin, String end,String returnType)
    {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long result = 0;
        try {
            Date d1 = df.parse(begin);
            Date d2 = df.parse(end);
            long diff = d1.getTime()-d2.getTime();

            long day = diff/(24*60*60*1000);
            long hours = diff/(60*60*1000)-day*24;
            long minutes = (diff/(60*1000))-day*24*60-hours*60;
            long seconds = diff/1000-day*24*60*60-hours*60*60-minutes*60;

            if (returnType.equals("day")) {
                result = day;
            }else if (returnType.equals("hours")) {
                result = hours;
            }else if (returnType.equals("minutes")) {
                result = minutes;
            }else{
                result = seconds;
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 将字符串的编码改为UTF-8
     */
    public String encodeToUTF8(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将字符串的编码改为GBK
     */
    public String encodeToGBK(String str) {
        try {
            return URLEncoder.encode(str, "GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取uuid
     *
     * @return
     */
    public String getUUID() {
        return UUID.randomUUID().toString();
    }


    /**
     * MD5加密
     */
    public String md5(final String s) {
        try {
            // Create MD5 Hash
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(s.getBytes());
            // size: 16
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String a =  Integer.toHexString(0xff & aMessageDigest);
                String b =  Integer.toHexString(aMessageDigest);

                // When value is negative, Integer.toHexString(value) can be "ffffff..",
                // for example, decimal -71 equivalent to hex ffffffb9.
                // But we only need the least significant byte.
                // So we need to "0xff & value"
                String h = Integer.toHexString(0xff & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 对字符串进行URLDecoder.encode(strEncoding)编码
     * @param src 要进行编码的字符串
     * @return String 进行编码后的字符串
     */
    public String getURLEncode(String src)
    {
        String requestValue="";
        try{
            requestValue = URLEncoder.encode(src);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return requestValue;
    }

    /**
     * 对字符串进行URLDecoder.decode(strEncoding)解码
     * @param src 要进行解码的字符串
     * @return String 进行解码后的字符串
     */
    public String getURLDecoderdecode(String src)
    {
        String requestValue="";
        try{
            requestValue = URLDecoder.decode(src);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return requestValue;
    }

    /**
     * 获取日期时间
     * @return
     * @author SHANHY
     * @date   2015年11月27日
     */
    public String getWebsiteDatetime(){
        try {
            String webUrl = "http://www.taobao.com";
            URL url = new URL(webUrl);// 取得资源对象
            URLConnection uc = url.openConnection();// 生成连接对象
            uc.connect();// 发出连接
            long ld = uc.getDate();// 读取网站日期时间
            Date date = new Date(ld);// 转换为标准时间对象
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);// 输出北京时间
            return sdf.format(date);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 返回字符串类型的数据是否为空值或null
     * */
    public boolean isStringEmpty(String str)
    {
        if (str == null)
        {
            return true;
        }

        if (str.length() == 0)
        {
            return true;
        }

        return  false;
    }


    /**
     * 返回字符串类型的数据是否为空值或等于0
     * */
    public boolean isIntEmpty(Integer str)
    {
        if (null == str)
        {
            return true;
        }

        if (str == 0)
        {
            return true;
        }

        return  false;
    }
}
