package com.zhiwu.global;

/**
 * Created by 韦庆明 on 2016/12/1.
 * 返回业务状态码
 */
public class ResultState
{
    /* 成功状态 */
    public final static int SUCCESS = 200;

    /* 失败/错误状态 */
    public final static int FAILURE = 201;

    /* 没有数据 */
    public final static int NULLDATA = 202;

    /* 用户未登录 */
    public final static int NOLOGIN = 203;

    /* 数量已达上限 */
    public final static int LIMIT = 204;

    /* 验签失败 */
    public final static int VERSIGN = 205;
}
