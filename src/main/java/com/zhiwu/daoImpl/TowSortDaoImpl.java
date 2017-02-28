package com.zhiwu.daoImpl;

import com.zhiwu.dao.ITowSortDao;
import com.zhiwu.model.TowSortModel;
import com.zhiwu.utils.MainUtils;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 韦庆明 on 2016/12/8.
 * 二级分类数据库层操作接口实现类
 */
public class TowSortDaoImpl extends BaseDaoImplDB implements ITowSortDao {


    /**
     * 添加二级分类数据
     * 回ResultSet中固定包含 returnId,returnMsg 字段
     * */
    public <T> ResultSet insert(T data) {

        TowSortModel model = (TowSortModel)data;
        ResultSet result = null;

        try {

            StringBuffer sb = new StringBuffer();

            //创建SQL语句
            sb.append("CALL InsertTowSort ?,?,? ");

            //连接数据库
            PreparedStatement param = mySqlDB().prepareStatement(sb.toString(),
                    ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);

            //写入参数
            param.setInt(1,model.getSortId());
            param.setString(2,model.getTowSortName());
            param.setString(3,model.getTowSortImgUrl());

            //获取查询结果
            result = param.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 修改二级分类数据
     * 回ResultSet中固定包含 returnId,returnMsg 字段
     * */
    public <T> ResultSet update(T data) {
        TowSortModel model = (TowSortModel)data;
        ResultSet result = null;

        try {

            StringBuffer sb = new StringBuffer();

            //编写SQL语句
            sb.append("CALL UpdateTowSort ?,?,?,? ");

            //连接数据库
            PreparedStatement param = mySqlDB().prepareStatement(sb.toString(),
                    ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);

            //写入参数
            param.setInt(1, model.getId());
            param.setInt(2, model.getSortId());
            param.setString(3,model.getTowSortName());
            param.setString(4,model.getTowSortImgUrl());

            //获取查询结果
            result = param.executeQuery();

        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 查询二级分类数据，根据id,sortName 字段查询，为空或null则不作为条件
     * 返回ResultSet字段参考TowSortModel实体类
     * */
    public <T> ResultSet select(T data) {
        TowSortModel model = (TowSortModel)data;
        ResultSet result = null;
        Map<Integer,String> map = new HashMap<Integer, String>();

        try {
            StringBuffer sb = new StringBuffer();
            int i = 0;

            sb.append("SELECT id,sortId,towSortName,towSortImgUrl TowSort WHERE 1 = 1 ");

            //判断是否需要添加id条件
            if (!MainUtils.getUtils().isIntEmpty(model.getId()))
            {
                i++;
                map.put(i,String.valueOf(model.getId()));
                sb.append("AND id = ? ");
            }

            //判断是否需要添加sortId条件
            if (!MainUtils.getUtils().isIntEmpty(model.getSortId()))
            {
                i++;
                map.put(i,String.valueOf(model.getSortId()));
                sb.append("AND sortId = ? ");
            }

            //判断是否需要添加TowSortName条件
            if (!MainUtils.getUtils().isStringEmpty(model.getTowSortName()))
            {
                i++;
                map.put(i,model.getTowSortName());
                sb.append("AND TowSortName = ? ");
            }

            //连接数据库
            PreparedStatement param = mySqlDB().prepareStatement(sb.toString(),
                    ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);

            //循环添加条件
            for (int key : map.keySet())
            {
                param.setString(key,map.get(key));
            }

            result = param.executeQuery();


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
}
