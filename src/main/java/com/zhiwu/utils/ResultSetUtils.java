package com.zhiwu.utils;

import com.sun.org.apache.bcel.internal.generic.NEW;
import org.hibernate.cfg.annotations.reflection.XMLContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 韦庆明 on 2016/9/26.
 * ResultSet转换类型工具类
 */
public class ResultSetUtils {

    public static ResultSetUtils getUtils() {
        return new ResultSetUtils();
    }


    /**
     * 将resultSet转化为实体类（实体字段全为String类型）
     *
     * @param rs  数据集
     * @param dto 需要转换成的类型
     */
    public <T> T bindDataToModel(ResultSet rs, T dto) {
        return bindDataToDto(rs, dto, 1);
    }


    /**
     * 将resultSet转化为List<T>（实体字段全为String类型）
     *
     * @param rs  数据集
     * @param dto 需要转换成的类型
     */
    public <T> List<T> bindDataToList(ResultSet rs, T dto) {
        return (List<T>) bindDataToDto(rs, dto, 2);
    }


    /**
     * 将resultSet转化为实体类（实体字段全为String类型）
     *
     * @param rs         数据集
     * @param dto        需要转换成的类型
     * @param returnType 1 返回实体类，2 返回List<T>
     */
    public <T> T bindDataToDto(ResultSet rs, T dto, int returnType) {
        //取得Method方法
        Method[] methods = dto.getClass().getMethods();

        List<T> list = new ArrayList<T>();

        //取得ResultSet的列名
        ResultSetMetaData rsmd = null;
        try {
            rsmd = rs.getMetaData();
            int columnsCount = rsmd.getColumnCount();
            String[] columnNames = new String[columnsCount];
            for (int i = 0; i < columnsCount; i++) {
                columnNames[i] = rsmd.getColumnLabel(i + 1);
            }

            //遍历ResultSet
            while (rs.next()) {
                //反射, 从ResultSet绑定到JavaBean
                for (int i = 0; i < columnNames.length; i++) {
                    //取得Set方法
                    String setMethodName = "set" + columnNames[i];
                    //遍历Method
                    for (int j = 0; j < methods.length; j++) {
                        if (methods[j].getName().equalsIgnoreCase(setMethodName)) {
                            setMethodName = methods[j].getName();
                            Object value = rs.getObject(columnNames[i]);

                            //实行Set方法
                            try {
                                //JavaBean内部属性和ResultSet中一致时候
                                if (value != null) {
                                    Method setMethod = dto.getClass().getMethod(setMethodName, value.getClass());
                                    setMethod.invoke(dto, value);
                                }
                            } catch (Exception e) {
                                //JavaBean内部属性和ResultSet中不一致时候，使用String来输入值。
                                Method setMethod = null;
                                try {

                                    setMethod = dto.getClass().getMethod(setMethodName, String.class);

                                    setMethod.invoke(dto, value.toString());

                                } catch (NoSuchMethodException e1) {
                                    e1.printStackTrace();
                                } catch (InvocationTargetException e1) {
                                    e1.printStackTrace();
                                } catch (IllegalAccessException e1) {
                                    e1.printStackTrace();
                                }
                            }
                        }
                    }
                }

                //将赋值成功的实体类添加到List列表中
                list.add(dto);

                //每次List列表add完后，都需要重新实例化一遍实体类
                //利用反射机制，实例化未知的实体类对象
                Class cls = dto.getClass();
                dto = (T) cls.newInstance();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        //判断需要返回的数据类型
        if (returnType == 1) {
            return dto;
        } else {
            return (T) list;
        }
    }

    /**
     * 将resultSet转化为JSON数组
     *
     * @param rs
     * @return
     * @throws SQLException
     * @throws JSONException
     */
    public JSONArray resultSetToJsonArry(ResultSet rs) {
        // json数组
        JSONArray array = new JSONArray();

        // 获取列数
        ResultSetMetaData metaData = null;
        try {
            metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            // 遍历ResultSet中的每条数据
            while (rs.next()) {
                JSONObject jsonObj = new JSONObject();

                // 遍历每一列
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnLabel(i);
                    String value = rs.getString(columnName);
                    jsonObj.put(columnName, value);
                }
                array.put(jsonObj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return array;
    }

    /**
     * 将resultSet转化为JSONObject
     *
     * @param rs
     * @return
     * @throws SQLException
     * @throws JSONException
     */
    public JSONObject resultSetToJsonObject(ResultSet rs) {
        // json对象
        JSONObject jsonObj = new JSONObject();
        // 获取列数
        ResultSetMetaData metaData = null;
        try {
            metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            // 遍历ResultSet中的每条数据
            if (rs.next()) {
                // 遍历每一列
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnLabel(i);
                    String value = rs.getString(columnName);
                    jsonObj.put(columnName, value);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObj;
    }

    /**
     * 利用反射机制将resultSet转化为Map
     */
    public <T> Map<String, T> resultSetToMap(ResultSet rs) {
        Map<String, T> map = new HashMap<String, T>();

        //获取列数据
        ResultSetMetaData metaData = null;
        try {

            metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            int row = rs.getRow();
            while (rs.next()) {
                //列名的获取都是从1开始的，所有循环的初始值设置为1
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    T value = (T) rs.getObject(columnName);
                    map.put(columnName, value);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return map;
    }

}
