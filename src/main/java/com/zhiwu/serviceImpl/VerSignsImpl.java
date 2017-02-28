package com.zhiwu.serviceImpl;

import com.zhiwu.global.ConfigKeySecret;
import com.zhiwu.global.ResultState;
import com.zhiwu.model.SignPackModel;
import com.zhiwu.utils.JsonUtils;
import com.zhiwu.utils.MainUtils;
import com.zhiwu.utils.SetResultModelUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by 韦庆明 on 2016/12/5.
 * 统一验签入口，验签通过后使用反射机制调用相应逻辑接口
 */
public class VerSignsImpl {


    public static VerSignsImpl getVerSigns() {
        return new VerSignsImpl();
    }


    /**
     * 对于验签数据包含在一个数据包里的处理
     *
     * @param service  = 已经实例化的接口类
     * @param function = 需要执行的逻辑方法名称
     * @param data     = 参数包+验签包
     */
    public <T> Object serviceEntryway(T service, String function, T data) {

        Object result = null;

        //验证签名
        if (!verSigns(data)) {
            result = SetResultModelUtils.getResult().setResult(ResultState.FAILURE, "验证签名失败！");
            return JsonUtils.getUtils().toJson(result);
        }

        //签名验证无误，执行相应的方法
        result = setServiceEntryway(service, function, data);

        return result;
    }


    /**
     * 对于验签数据包含在一个数据包和验签包分开情况下的处理
     *
     * @param service  = 已经实例化的接口类
     * @param function = 需要执行的逻辑方法名称
     * @param data     = 参数包
     * @param data     = 验签包
     */
    public <T> Object serviceEntryway(T service, String function, T data, T signs) {

        Object result = null;

        //验证签名
        if (!verSigns(signs)) {
            result = SetResultModelUtils.getResult().setResult(ResultState.FAILURE, "验证签名失败！");
            return JsonUtils.getUtils().toJson(result);
        }

        //签名验证无误，执行相应的方法
        result = setServiceEntryway(service, function, data);

        return result;
    }


    /**
     * 验证签名
     */
    private <T> boolean verSigns(T data) {

        boolean vb = false;

        try {
            String datas = (String) data;
            //将待验签的json数据转换成实体类
            SignPackModel sModel = JsonUtils.getUtils().fromJson(datas, SignPackModel.class);

            //验证签名
            vb = verSigns(sModel);
        } catch (Exception e) {
            vb = false;
        }


        return vb;
    }

    /**
     * 利用反射调用相应的方法/接口
     * @param service  = 已经实例化的接口类
     * @param function = 需要执行的逻辑方法名称
     * @param data     = 参数包
     */
    private <T> Object setServiceEntryway(T service, String function, T data) {

        Object result = JsonUtils.getUtils().toJson(
                SetResultModelUtils.getResult().setResult(ResultState.FAILURE, "请求异常！"));

        try {

            //获取相应class
            Class cls = service.getClass();

            //利用反射方式获取相应的方法（非静态）
            Method method = cls.getMethod(function, Object.class);

            //执行获取到的方法
            result = method.invoke(service, new Object[]{data});

        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        return result;
    }


    /**
     * 统一接口请求验签操作方法
     * 验签方式：KEY+SECRET+时间戳
     * times与服务端时间差不能超过10分钟
     * signs = MD5(EY+SECRET+时间戳)，times = 时间戳，appId = 客户端ID
     */
    public boolean verSigns(SignPackModel model) {
        try {
            String dateA = MainUtils.getUtils().getSystemTime("yyyy-MM-dd HH:mm:ss");
            model.setTimes(MainUtils.getUtils().dateToStamp(dateA));
            String dateB = MainUtils.getUtils().stampToDate(model.getTimes());
            long diff = MainUtils.getUtils().getDayTwoTime(dateA, dateB, "minutes");

            //判断服务器时间与客户端发送的时间差不能大于10分钟
            if (diff > 10) {
                return false;
            }

            //待重签的签名变量
            String sign = "";

            //判断APPID并重签
            if (model.getAppId() == 1) {
                sign = MainUtils.getUtils().md5(ConfigKeySecret.HOUTAI_KEY + ConfigKeySecret.HOUTAI_SECRET + model.getTimes());
            } else if (model.getAppId() == 2) {
                sign = MainUtils.getUtils().md5(ConfigKeySecret.ANDROID_KEY + ConfigKeySecret.ANDROID_SECRET + model.getTimes());
            }

            //测试用，实际操作后将其注释
            sign = model.getSigns();

            //判断签名是否正确
            if (!sign.equals(model.getSigns())) {
                return false;
            }

        } catch (Exception e) {
            return false;
        }

        return true;
    }

}
