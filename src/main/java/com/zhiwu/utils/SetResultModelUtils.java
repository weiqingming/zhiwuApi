package com.zhiwu.utils;

import com.zhiwu.model.BaseResultModel;

/**
 * Created by 韦庆明 on 2016/12/1.
 * 返回固定数据模型工具类
 */
public class SetResultModelUtils
{

    /**
     * 调用固定返回数据类
     **/
    public static SetResultModelUtils getResult()
    {
        return new SetResultModelUtils();
    }

    /**
     * 自定义msg情况下调用
     * */
    public <T> BaseResultModel<T> setResult(int state,String msg)
    {
        BaseResultModel<T> result = new BaseResultModel<T>();
        result.setState(state);
        result.setMsg(msg);
        return result;
    }

    /**
     * 使用固定msg情况下调用
     * */
    public <T> BaseResultModel<T> setResult(int state)
    {
        String msg = "";
        BaseResultModel<T> result = new BaseResultModel<T>();
        result.setState(state);
        switch (state)
        {
            case 200:
                msg = "请求成功";
                break;

            case 201:
                msg = "请求失败";
                break;

            case 202:
                msg = "没有数据";
                break;

            case 203:
                msg = "用户未登录";
                break;

            case 204:
                msg = "数量已达上限";
                break;

            default:
                msg = "请求异常";
        }
        result.setMsg(msg);
        return result;
    }
}
