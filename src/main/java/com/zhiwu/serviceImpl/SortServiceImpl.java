package com.zhiwu.serviceImpl;

import com.zhiwu.dao.IMyTestDao;
import com.zhiwu.global.MyBatisSession;
import com.zhiwu.global.ResultState;
import com.zhiwu.model.BaseResultModel;
import com.zhiwu.model.SortModel;
import com.zhiwu.service.ISortService;
import com.zhiwu.utils.JsonUtils;
import com.zhiwu.utils.MainUtils;
import com.zhiwu.utils.ResultSetUtils;
import com.zhiwu.utils.SetResultModelUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by 韦庆明 on 2016/12/5.
 * 一级分类逻辑层操作接口实现类
 */
@Service("sortServiceImpl")
public class SortServiceImpl extends BaseServiceImpl implements ISortService {


    /**
     * 添加一级分类数据
     *
     * @param data = SortModel实体类json
     */
    public <T> Object insert(T data) {

        BaseResultModel<String> result = null;

        String datas = (String) data;
        //将发送的json数据转换成实体类
        SortModel rModel = JsonUtils.getUtils().fromJson(datas, SortModel.class);


        //判断一级分类名称是否为空
        if (MainUtils.getUtils().isStringEmpty(rModel.getSortName())) {
            result = SetResultModelUtils.getResult().setResult(ResultState.FAILURE, "一级分类名称不能为空！");
            return JsonUtils.getUtils().toJson(result);
        }

        //判断一级分类图标地址是否为空
        if (MainUtils.getUtils().isStringEmpty(rModel.getSortImgUrl())) {
            result = SetResultModelUtils.getResult().setResult(ResultState.FAILURE, "一级分类图标地址不能为空！");
            return JsonUtils.getUtils().toJson(result);
        }

        //以上筛选没有问题，提交数据库

        ResultSet rs = iSortDao().insert(rModel);
        //将ResultSet转换成实体类
        Map<String, String> rMap = ResultSetUtils.getUtils().resultSetToMap(rs);
        if (String.valueOf(rMap.get("returnId")).equals("1")) {
            result = SetResultModelUtils.getResult().setResult(ResultState.SUCCESS, rMap.get("returnMsg"));
        } else {
            result = SetResultModelUtils.getResult().setResult(ResultState.FAILURE, rMap.get("returnMsg"));
        }

        return JsonUtils.getUtils().toJson(result);
    }


    /**
     * 根据id修改一级分类数据，可修改sortName，sortImgUrl两个字段的数据
     *
     * @param data = SortModel实体类json
     */
    public <T> Object update(T data) {

        BaseResultModel<String> result = null;

        String datas = (String) data;
        //将发送的json数据转换成实体类
        SortModel rModel = JsonUtils.getUtils().fromJson(datas, SortModel.class);

        //判断一级分类ID是否为空或0
        if (MainUtils.getUtils().isIntEmpty(rModel.getId())) {
            result = SetResultModelUtils.getResult().setResult(ResultState.FAILURE, "一级分类id无效");
            return JsonUtils.getUtils().toJson(result);
        }


        //判断一级分类名称是否为空
        if (MainUtils.getUtils().isStringEmpty(rModel.getSortName())) {
            result = SetResultModelUtils.getResult().setResult(ResultState.FAILURE, "一级分类名称不能为空！");
            return JsonUtils.getUtils().toJson(result);
        }

        //判断一级分类图标地址是否为空
        if (MainUtils.getUtils().isStringEmpty(rModel.getSortImgUrl())) {
            result = SetResultModelUtils.getResult().setResult(ResultState.FAILURE, "一级分类图标地址不能为空！");
            return JsonUtils.getUtils().toJson(result);
        }

        //以上筛选没有问题，提交数据库
        ResultSet rs = iSortDao().update(rModel);

        //将ResultSet转换成实体类
        Map<String, String> rMap = ResultSetUtils.getUtils().resultSetToMap(rs);
        if (String.valueOf(rMap.get("returnId")).equals("1")) {
            result = SetResultModelUtils.getResult().setResult(ResultState.SUCCESS, rMap.get("returnMsg"));
        } else {
            result = SetResultModelUtils.getResult().setResult(ResultState.FAILURE, rMap.get("returnMsg"));
        }

        return JsonUtils.getUtils().toJson(result);
    }

    /**
     * 查询一级分类数据，根据id,sortName 字段查询，为空或null则不作为条件
     *
     * @param data = SortModel实体类json
     *             返回ResultSet字段参考实体类

    public <T> Object select(T data) {

        BaseResultModel<List<SortModel>> result = new BaseResultModel<List<SortModel>>();

        String datas = (String) data;
        //将发送的json数据转换成实体类
        SortModel rModel = JsonUtils.getUtils().fromJson(datas, SortModel.class);

        try {

            //获取一级分类数据
            ResultSet rs = iSortDao().select(rModel);

            //将坐标滚动到底部
            rs.last();

            //判断目前坐标的位置是否大于0
            if (rs.getRow() > 0) {

                //将坐标点移回第一行
                rs.beforeFirst();

                result = SetResultModelUtils.getResult().setResult(ResultState.SUCCESS, "获取数据成功！");
                result.setBody(ResultSetUtils.getUtils().bindDataToList(rs, new SortModel()));

            } else {
                result = SetResultModelUtils.getResult().setResult(ResultState.NULLDATA, "没有找到数据哦！");
            }

        } catch (Exception e) {
            result = SetResultModelUtils.getResult().setResult(ResultState.FAILURE, "请求异常！");
            return JsonUtils.getUtils().toJson(result);
        }

        return JsonUtils.getUtils().toJson(result);
    }*/

    /**
     * 查询一级分类数据，根据id,sortName 字段查询，为空或null则不作为条件
     *
     * @param data = SortModel实体类json
     *             返回ResultSet字段参考实体类
     */
    public <T> Object select(T data) {

        BaseResultModel<List<SortModel>> result = new BaseResultModel<List<SortModel>>();

        String datas = (String) data;
        //将发送的json数据转换成实体类
        SortModel rModel = JsonUtils.getUtils().fromJson(datas, SortModel.class);


        //获取数据库连接session
        SqlSession session = MyBatisSession.getSqlSession();

        try {
            //实例化接口
            IMyTestDao isortDao = session.getMapper(IMyTestDao.class);

            //查询数据
            SortModel model = isortDao.selectSort(1);
            List<SortModel> list = new ArrayList<SortModel>();
            list.add(model);
            result.setBody(list);

        }catch (Exception e)
        {
            e.printStackTrace();
        }

        return JsonUtils.getUtils().toJson(result);
    }

}
