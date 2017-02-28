package com.zhiwu.serviceImpl;

import com.zhiwu.global.ResultState;
import com.zhiwu.model.BaseResultModel;
import com.zhiwu.model.UsersModel;
import com.zhiwu.model.receiveModel.LoginModel;
import com.zhiwu.service.IUsersService;
import com.zhiwu.utils.JsonUtils;
import com.zhiwu.utils.MainUtils;
import com.zhiwu.utils.ResultSetUtils;
import com.zhiwu.utils.SetResultModelUtils;

import java.sql.ResultSet;
import java.util.Map;

/**
 * Created by 韦庆明 on 2016/11/30.
 * 用户信息相关操作逻辑处理
 */
public class UsersServiceImpl extends BaseServiceImpl implements IUsersService {

    /**
     * 注册用户
     */
    public <T> Object registerUser(T data) {
        return 0;
    }

    /**
     * 用户登录
     */
    public <T> Object loginUser(T data) {

        BaseResultModel<UsersModel> result = null;
        LoginModel model = new LoginModel();
        ResultSet ru;

        try {

            String datas = (String)data;

            //解析接收到的数据
            model = JsonUtils.getUtils().fromJson(datas, LoginModel.class);

            //验证账号密码并返回uid
            ru = iUsersDao().loginUser(model.getUserName(), model.getPassWord());

            //将获取的数据集转换为map
            Map<String, Integer> uidMap = ResultSetUtils.getUtils().resultSetToMap(ru);

            //判断uid没有获取成功，返回“账号或密码有误”
            if (uidMap.size() == 0) {
                result = SetResultModelUtils.getResult().setResult(ResultState.FAILURE, "账号或密码有误！");
                return JsonUtils.getUtils().toJson(result);
            }

            //每次登录，都初始化一次TOKEN
            result = resultUsersModel(uidMap.get("uid"), MainUtils.getUtils().getUUID());

        } catch (Exception e) {
            result = SetResultModelUtils.getResult().setResult(ResultState.FAILURE, "请求异常！");
            return JsonUtils.getUtils().toJson(result);
        }


        return JsonUtils.getUtils().toJson(result);
    }

    /**
     * 根据token获取用户基础信息
     */
    public <T> String getUsersInit(T data) {

        BaseResultModel<UsersModel> result = null;
        UsersModel model = new UsersModel();
        ResultSet rs;

        try {

            String token = (String)data;

            //验证token，并返回uid数据集
            rs = iUsersDao().verUsersToken(token);

            //将数据集转换成map
            Map<String, Integer> uidMap = ResultSetUtils.getUtils().resultSetToMap(rs);

            //判断没有获取到uid数据，返回未登录状态提示
            if (uidMap.size() == 0) {
                result = SetResultModelUtils.getResult().setResult(ResultState.NOLOGIN, "用户标识已失效，请重新登录！");
                return JsonUtils.getUtils().toJson(result);
            }

            result = resultUsersModel(uidMap.get("uid"), token);

        } catch (Exception e) {
            result = SetResultModelUtils.getResult().setResult(ResultState.FAILURE, "请求异常！");
            return JsonUtils.getUtils().toJson(result);
        }

        return JsonUtils.getUtils().toJson(result);
    }

    /**
     * 初始化token并返回获取用户信息结果
     */
    private BaseResultModel<UsersModel> resultUsersModel(int uid, String token) {
        BaseResultModel<UsersModel> result = null;
        UsersModel model = new UsersModel();
        ResultSet ru;

        //每次获取个人信息，都初始化一次TOKEN
        ru = iUsersDao().updateToken(uid, token);

        //将获取的数据集转换为map
        Map<String, String> tokenMap = ResultSetUtils.getUtils().resultSetToMap(ru);

        //判断token是否初始化成功
        if (!String.valueOf(tokenMap.get("returnId")).equals("1")) {
            result = SetResultModelUtils.getResult().setResult(ResultState.FAILURE, tokenMap.get("returnMsg"));
            return result;
        }

        //获取用户基本信息
        ru = iUsersDao().getUsersInit(uid);

        //将获取的数据集转换为map
        ResultSetUtils.getUtils().bindDataToModel(ru, model);


        //判断是否获取成功
        if (model.getUserName() != null) {
            //设置token
            model.setToken(tokenMap.get("token"));
            result = SetResultModelUtils.getResult().setResult(ResultState.SUCCESS);
            result.setBody(model);
        } else {
            result = SetResultModelUtils.getResult().setResult(ResultState.FAILURE, "初始化数据失败，请重试！");
        }

        return result;
    }
}
