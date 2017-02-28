package com.zhiwu.daoImpl;

import com.zhiwu.dao.IUsersDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by 韦庆明 on 2016/11/30.
 * 用户信息相关操作处理逻辑层实现类
 */
public class UsersDaoImpl extends BaseDaoImplDB implements IUsersDao {

    /* 注册用户 */
    public ResultSet registerUser(String userName, String passWord, String phone) {
        return null;
    }

    /* 用户登录 */
    public  ResultSet loginUser(String userName, String passWord)
    {
        ResultSet result = null;
        try {

        StringBuffer sb = new StringBuffer();
        sb.append("SELECT uid FROM Users ");
        sb.append("WHERE userName = ? ");
        sb.append("AND passWord = ? ");

            PreparedStatement param = mySqlDB().prepareStatement(sb.toString(),
                                      ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            param.setString(1,userName);
            param.setString(2,passWord);

            result = param.executeQuery();


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /* 根据uid更新token，并原路返回token */
    public ResultSet updateToken(int uid,String token) {

        ResultSet result = null;
        try {

            StringBuffer sb = new StringBuffer();
            sb.append("CALL SelectToken (?,?) ");

            PreparedStatement param = mySqlDB().prepareStatement(sb.toString(),
                    ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            param.setInt(1,uid);
            param.setString(2,token);

            result = param.executeQuery();


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /* 根据token获取用户基础信息 */
    public ResultSet getUsersInit(int uid) {

        ResultSet result = null;
        try {

            StringBuffer sb = new StringBuffer();
            sb.append("SELECT uid,userName,phone,name,gender,age,headPortraitUrl FROM Users ");
            sb.append("WHERE uid = ? ");

            PreparedStatement param = mySqlDB().prepareStatement(sb.toString(),
                    ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            param.setInt(1,uid);

            result = param.executeQuery();


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /* 验证token并返回用户id */
    public ResultSet verUsersToken(String token) {

        ResultSet result = null;
        try {

            StringBuffer sb = new StringBuffer();
            sb.append("SELECT uid FROM UsersToken ");
            sb.append("WHERE token = ? ");
            sb.append("AND DATE(endDate) >= DATE(SYSDATE()) ");

            PreparedStatement param = mySqlDB().prepareStatement(sb.toString(),
                    ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            param.setString(1,token);

            result = param.executeQuery();


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

}
