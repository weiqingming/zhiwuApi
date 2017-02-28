package com.zhiwu.serviceImpl;

import com.zhiwu.cache.MyRedisCache;
import com.zhiwu.model.UsersModel;
import com.zhiwu.service.IRedisTestService;
import com.zhiwu.utils.MainUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by weiqingming on 2017/2/27.
 * 测试Redis缓存接口实现类
 */

@Service("redisTestService")
public class RedisTestImpl implements IRedisTestService {

    //使用Spring注解的方式获取到 MyRedisCache 对象
    @Resource(name = "redisCache")
    private MyRedisCache redisCache;

    @Override
    public void setData() {

        if (redisCache != null) {

            String date = MainUtils.getUtils().getSystemTime("yyyy-MM-dd HH:mm:ss");
            //测试添加一个字符串
            boolean bl1 = redisCache.putCache("string", "aaa "+date);

            //测试添加一个实体类
            UsersModel model = new UsersModel();
            model.setName("测试添加一个实体类 "+date);
            boolean bl2 = redisCache.putCache("model", model);

            //测试添加一个list
            List<UsersModel> list = new ArrayList<UsersModel>();
            UsersModel model1 = new UsersModel();
            model1.setName("测试添加一个List "+date);
            list.add(model);
            list.add(model1);
            boolean bl3 = redisCache.putListCache("list", list);

            //测试添加一个带定义缓存时间的字符串
            boolean bl4 = redisCache.putOverdueTime("stringb","bbb "+date,MyRedisCache.CAHCEHOUR);

            System.out.println("ok");
        }
    }

    @Override
    public void getData() {

        if (redisCache != null) {

            //读取添加的字符串
            String str = redisCache.getCache("string");

            //读取添加的实体类
            UsersModel model = redisCache.getCache("model");

            //读取添加的list
            List<UsersModel> list = redisCache.getCache("list");

            //读取带定义缓存时间的字符串
            String strb = redisCache.getCache("stringb");

            System.out.println("ok");
        }
    }

    @Override
    public void delData() {

        if (redisCache != null) {

            //根据key删除对应的缓存数据
            redisCache.remove("string");

            //根据keys(数组)删除对应的缓存数据
            String[] strs = {"string ","model"};
            redisCache.removes(strs);

            //使用模糊条件删除缓存数据
            redisCache.removeVague("*st");

            //使用模糊条件删除缓存数据
            redisCache.removeVague("st*");

            System.out.println("ok");
        }
    }
}
