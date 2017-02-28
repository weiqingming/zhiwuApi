package com.zhiwu.serviceImpl;

import com.zhiwu.dao.IMyTestDao;
import com.zhiwu.model.SortModel;
import com.zhiwu.service.IMyTestService;
import com.zhiwu.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 韦庆明 on 2016/12/14.
 * 测试MyBatis业务逻辑实现类
 * 注入到Spring容器中 ：@Service("myTestImpl")
 */

@Service("myTestImpl")
public class MyTestImpl implements IMyTestService {

    //使用@Autowired注释从Spring容器中获取相应的对象
    @Autowired(required = false)
    private IMyTestDao myTestdao;

    public Object selectSort(int id) {

        SortModel model = null;
        List<SortModel> list = null;
        List<SortModel> listb = null;
        try {

            //根据ID获取Sort表的数据
            model = myTestdao.selectSort(id);

            //获取Sort表所有的数据
            list = myTestdao.allSort();

            //根据多条件条件获取Sort表的数据
            SortModel qmodel = new SortModel();
            qmodel.setId(1);
            listb = myTestdao.querySort(qmodel);

        }catch (Exception e)
        {
            e.printStackTrace();
        }

        //返回Sort表所有的数据，并转换成json字符串
        return JsonUtils.getUtils().toJson(list);
    }
}
