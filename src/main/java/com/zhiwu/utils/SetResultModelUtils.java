package com.zhiwu.utils;

import com.zhiwu.global.ResultState;
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
            case ResultState.SUCCESS:
                msg = "请求成功";
                break;

            case ResultState.FAILURE:
                msg = "请求失败";
                break;

            case ResultState.NULLDATA:
                msg = "没有数据";
                break;

            case ResultState.NOLOGIN:
                msg = "用户未登录";
                break;

            case ResultState.LIMIT:
                msg = "数量已达上限";
                break;

            case ResultState.VERSIGN:
                msg = "验签失败";
                break;

            default:
                msg = "请求异常";
        }
        result.setMsg(msg);
        return result;
    }
}
