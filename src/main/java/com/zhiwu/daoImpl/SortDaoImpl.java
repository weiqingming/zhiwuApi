package com.zhiwu.daoImpl;

import com.zhiwu.dao.ISortDao;
import com.zhiwu.model.SortModel;
import com.zhiwu.utils.MainUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 韦庆明 on 2016/12/4.
 * 一级分类数据库层操作接口实现类
 */
public class SortDaoImpl extends BaseDaoImplDB implements ISortDao {

    /**
     * 添加一级分类数据
     * 回ResultSet中固定包含 returnId,returnMsg 字段
     * */
    public <T> ResultSet insert(T data) {

        SortModel sortModel = (SortModel) data;
        ResultSet result = null;

        try {

            StringBuffer sb = new StringBuffer();
            sb.append("CALL InsertSort (?,?) ");

            PreparedStatement param = mySqlDB().prepareStatement(sb.toString(),
                    ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);

            param.setString(1,sortModel.getSortName());
            param.setString(2,sortModel.getSortImgUrl());

            result = param.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }


    /**
     * 修改一级分类数据，可修改sortName，sortImgUrl两个字段的数据
     * 回ResultSet中固定包含 returnId,returnMsg 字段
     * */
    public <T> ResultSet update(T data) {

        SortModel sortModel = (SortModel) data;
        ResultSet result = null;

        try {

            StringBuffer sb = new StringBuffer();

            sb.append("CALL UpdateSort (?,?,?) ");

            PreparedStatement param = mySqlDB().prepareStatement(sb.toString(),
                    ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);

            param.setString(1,sortModel.getSortName());
            param.setString(2,sortModel.getSortImgUrl());
            param.setInt(3,sortModel.getId());

            result = param.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }


    /**
     * 查询一级分类数据，根据id,sortName 字段查询，为空或null则不作为条件
     * 返回ResultSet字段参考实体类
     * */
    public <T> ResultSet select(T data) {
        SortModel sortModel = (SortModel)data;
        ResultSet result = null;

        try {

            int i = 0;
            StringBuffer sb = new StringBuffer();

            //装载查询条件
            Map<Integer,String> params = new HashMap<Integer,String>();

            sb.append("SELECT id,sortName,sortImgUrl FROM Sort WHERE 1 = 1 ");

            //判断是否需要添加id条件
            if (!MainUtils.getUtils().isIntEmpty(sortModel.getId()))
            {
                i++;
                sb.append("AND id = ? ");
                params.put(i,String.valueOf(sortModel.getId()));
            }

            //判断是否需要添加sortName条件
            if (!MainUtils.getUtils().isStringEmpty(sortModel.getSortName()))
            {
                i++;
                sb.append("AND sortName = ? ");
                params.put(i,sortModel.getSortName());
            }

            PreparedStatement param = mySqlDB().prepareStatement(sb.toString(),
                    ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);

            //遍历条件并添加
            for (Integer key:params.keySet()){
                param.setString(key,params.get(key));
            }

            result = param.executeQuery();

        }catch (SQLException e){
            e.printStackTrace();
        }

        return result;
    }
}
