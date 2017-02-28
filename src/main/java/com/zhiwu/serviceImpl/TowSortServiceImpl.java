package com.zhiwu.serviceImpl;

import com.zhiwu.global.ResultState;
import com.zhiwu.model.BaseResultModel;
import com.zhiwu.model.TowSortModel;
import com.zhiwu.service.ITowSortService;
import com.zhiwu.utils.JsonUtils;
import com.zhiwu.utils.MainUtils;
import com.zhiwu.utils.ResultSetUtils;
import com.zhiwu.utils.SetResultModelUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 韦庆明 on 2016/12/8.
 */
public class TowSortServiceImpl extends BaseServiceImpl implements ITowSortService{


    /**
     * 添加二级分类数据
     * @param data = json字符串，格式参考TowSortModel
     * */
    public <T> Object insert(T data) {

        BaseResultModel<String> result = null;
        String jsons = (String)data;
        TowSortModel model = JsonUtils.getUtils().fromJson(jsons, TowSortModel.class);


        //验证数据是否符合要求
        Map<String,String> vmap = verParam(model,1);
        if (vmap.size() > 0)
        {
            result = SetResultModelUtils.getResult().setResult(ResultState.SUCCESS,vmap.get("msg"));
            return result;
        }

        //提交到数据层处理
        ResultSet rs = iTowSortDao().update(model);
        Map<String,String> map = ResultSetUtils.getUtils().resultSetToMap(rs);

        //判断处理状态
        if (String.valueOf(map.get("returnId")).equals("1"))
        {
            result = SetResultModelUtils.getResult().setResult(ResultState.SUCCESS,map.get("returnMsg"));
        }else {
            result = SetResultModelUtils.getResult().setResult(ResultState.FAILURE,map.get("returnMsg"));
        }

        return JsonUtils.getUtils().toJson(result);
    }

    /**
     * 修改二级分类数据
     * @param data = json字符串，格式参考TowSortModel
     * */
    public <T> Object update(T data) {

        BaseResultModel<String> result = null;
        String jsons = (String)data;
        TowSortModel model = JsonUtils.getUtils().fromJson(jsons, TowSortModel.class);

        //验证数据是否符合要求
        Map<String,String> vmap = verParam(model,2);
        if (vmap.size() > 0)
        {
            result = SetResultModelUtils.getResult().setResult(ResultState.SUCCESS,vmap.get("msg"));
            return result;
        }

        //提交到数据层处理
        ResultSet rs = iTowSortDao().update(model);
        Map<String,String> map = ResultSetUtils.getUtils().resultSetToMap(rs);

        //判断处理状态
        if (String.valueOf(map.get("returnId")).equals("1"))
        {
            result = SetResultModelUtils.getResult().setResult(ResultState.SUCCESS,map.get("returnMsg"));
        }else {
            result = SetResultModelUtils.getResult().setResult(ResultState.FAILURE,map.get("returnMsg"));
        }

        return JsonUtils.getUtils().toJson(result);
    }

    /**
     * 获取二级分类数据
     * @param data = json字符串，格式参考TowSortModel
     * */
    public <T> Object select(T data) {

        BaseResultModel<List<TowSortModel>> result = null;
        String jsons = (String)data;
        TowSortModel model = JsonUtils.getUtils().fromJson(jsons, TowSortModel.class);

        try {

            //获取数据
            ResultSet rs = iTowSortDao().select(model);

            //将坐标移动到底部，用于获取行数
            rs.last();

            //判断目前坐标的位置是否大于0
            if (rs.getRow() > 0)
            {
                //将坐标移回第一行
                rs.beforeFirst();

                result = SetResultModelUtils.getResult().setResult(ResultState.SUCCESS);
                result.setBody(ResultSetUtils.getUtils().bindDataToList(rs,new TowSortModel()));

            }else {
                result = SetResultModelUtils.getResult().setResult(ResultState.NULLDATA,"没有找到数据哦");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return JsonUtils.getUtils().toJson(result);
    }


    /**
     * 判断参数是否符合要求
     * @param model 数据实体类
     * @param iden 业务类型1添加，2修改
     * */
    private Map<String,String> verParam(TowSortModel model,int iden)
    {
        Map<String,String> map = new HashMap<String, String>();

        if (iden == 2) {
            //判断SortId参数是否符合要求
            if (MainUtils.getUtils().isIntEmpty(model.getId())) {
                map.put("msg", "id参数有误");
                return map;
            }
        }

        //判断SortId参数是否符合要求
        if (MainUtils.getUtils().isIntEmpty(model.getSortId()))
        {
            map.put("msg","SortId参数有误");
            return map;
        }

        //判断TowSortName参数是否符合要求
        if (MainUtils.getUtils().isStringEmpty(model.getTowSortName()))
        {
            map.put("msg","TowSortName参数有误");
            return map;
        }

        //判断TowSortName参数是否符合要求
        if (MainUtils.getUtils().isStringEmpty(model.getTowSortImgUrl()))
        {
            map.put("msg","TowSortImgUrl参数有误");
            return map;
        }

        return map;
    }
}
