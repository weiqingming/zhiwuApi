package com.zhiwu.daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 韦庆明 on 2016/11/30.
 * 基础连接数据库类，dao相关实现类继承它
 */
public class BaseDaoImplDB {


    /**
     * 连接SQLServer数据库，返回Connection
     * */
    public Connection sqlServerDB() {

        return controllerDB(1);
    }

    /**
     * 连接MySql数据库，返回Connection
     * */
    public Connection mySqlDB() {

        return controllerDB(2);
    }

    /**
     * 连接数据库控制类
     * */
    private Connection controllerDB(int iden)
    {
        Map<String,String> connectDB = new HashMap<String,String>();

        //连接SQLserver数据库
        if (iden == 1)
        {
            connectDB.put("driverName","com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connectDB.put("dbURL","jdbc:sqlserver://rm.sqlserver.rds.aliyuncs.com:3433;DatabaseName=cx");
            connectDB.put("userName","test");
            connectDB.put("userPwd","test123");
        }

        //连接MySQL数据库
        else if (iden == 2)
        {
            connectDB.put("driverName","org.gjt.mm.mysql.Driver");
            connectDB.put("dbURL","jdbc:mysql://120.77.1.1:3306/zhiwu?useUnicode=true&characterEncoding=UTF-8");
            connectDB.put("userName","hehehe");
            connectDB.put("userPwd","heiheihei");
        }

        return InitializationConnection(connectDB);
    }


    /**
     * 连接SQL数据库，执行相应的sql语句，并返回数据
     * PreparedStatement sql语句
     * */
    private ResultSet sendSql(PreparedStatement pt) {

        ResultSet rs = null;
        try {

            rs = pt.executeQuery();

            System.out.println("连接数据库成功");

        } catch (Exception e) {

            e.printStackTrace();
            System.out.print("连接失败");
        }

        return rs;
    }


    /**
     * 连接数据库，握手成功后返回Connection
     * */
    private Connection InitializationConnection(Map<String,String> map)
    {

        Connection dbConn = null;

        try {

            Class.forName(map.get("driverName"));

            dbConn = DriverManager.getConnection(map.get("dbURL"), map.get("userName"), map.get("userPwd"));

        } catch (Exception e) {

            //Logs.WriteLog("处理","InitializationConnection-Exception e - "+e.toString() );
            e.printStackTrace();
        }

        return  dbConn;
    }
}
