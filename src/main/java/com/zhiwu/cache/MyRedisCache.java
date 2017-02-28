package com.zhiwu.cache;

import com.zhiwu.utils.SerializableUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * Created by weiqingming on 2017/2/20.
 * Redis缓存公共调用类
 */

@Component("redisCache")
public class MyRedisCache {

    public final static String CAHCENAME = "qipaicache";// 缓存名
    public final static int CAHCETIME = 60;// 默认缓存时间 60S
    public final static int CAHCEHOUR = 60 * 60;// 默认缓存时间 1hr
    public final static int CAHCEDAY = 60 * 60 * 24;// 默认缓存时间 1Day
    public final static int CAHCEWEEK = 60 * 60 * 24 * 7;// 默认缓存时间 1week
    public final static int CAHCEMONTH = 60 * 60 * 24 * 7 * 30;// 默认缓存时间 1month

    @Resource(name = "redisTemplate")
    private RedisTemplate<Serializable, Object> redisTemplate;

    /**
     * 插入一个普通对象，如String，实体类等
     * 如果key已经存在，则添加缓存失败
     */
    public <T> boolean putCache(String key, T value) {

        //调用统一方法
        return execute(key, value, 0);
    }

    /**
     * 插入一个定义缓存时间的普通对象，如String，实体类等
     * 如果key已经存在，则覆盖这个key的value
     */
    public <T> boolean putOverdueTime(String key, T value, final long overdueTime) {

        //调用统一方法
        return execute(key, value, overdueTime);
    }


    /**
     * 插入一个List对象
     * 如果key已经存在，则添加缓存失败
     */
    public <T> boolean putListCache(String key, List<T> value) {

        //调用统一方法
        return execute(key, value, 0);

    }


    /**
     * 插入一个定义缓存时间的List对象
     * 如果key已经存在，则覆盖这个key的value
     */
    public <T> boolean putListOverdueTime(String key, List<T> value, final long overdueTime) {

        //调用统一方法
        return execute(key, value, overdueTime);

    }


    /**
     * 获取缓存
     */
    public <T> T getCache(final String key) {
        byte[] result = redisTemplate.execute(new RedisCallback<byte[]>() {
            @Override
            public byte[] doInRedis(RedisConnection redisConnection) throws DataAccessException {
                return redisConnection.get(key.getBytes());
            }
        });

        if (result == null) {
            return null;
        }

        return SerializableUtils.getUtils().deSerialize(result);
    }


    /**
     * 根据key删除对应的缓存
     */
    public void remove(final String key) {
        if (exists(key)) {
            redisTemplate.delete(key);
        }
    }


    /**
     * 根据key批量删除对应的缓存
     */
    public void removes(final String... keys) {
        for (String key : keys) {
            remove(key);
        }
    }


    /**
     * 根据模糊条件删除对应的缓存
     * 如需删除如key为 test 开头的数据，则vague = test*
     * 如需删除如key为 test 结尾的数据，则vague = *test
     */
    public void removeVague(final String vague) {
        Set<Serializable> keys = redisTemplate.keys(vague);
        if (keys.size() > 0)
            redisTemplate.delete(keys);
    }


    /**
     * 判断key在缓存中是否有数据
     */
    public boolean exists(final String key) {
        return redisTemplate.hasKey(key);
    }


    /**
     * 统一插入缓存方法，根据 overdueTime > 0 判断是否需要定义过期时间
     * 需要注意的是，Redis添加缓存数据时候，如果该key已经存在，那么就会添加失败
     *
     * @param key         = 键
     * @param value       = 值
     * @param overdueTime = 缓存周期
     * @return boolean
     */
    private <T> boolean execute(final String key, final T value, final long overdueTime) {

        //提交前需要给键、值进行字节流转化，value还需要进行Serializable序列化
        final byte[] bkey = key.getBytes();
        final byte[] bvalue = SerializableUtils.getUtils().serialize(value);
        boolean result;
        try {

            result = redisTemplate.execute(new RedisCallback<Boolean>() {
                @Override
                public Boolean doInRedis(RedisConnection connection) throws DataAccessException {

                    //判断是否添加带过期时间的缓存数据
                    if (overdueTime == 0) {
                        return connection.setNX(bkey, bvalue);
                    } else {
                        connection.setEx(bkey, overdueTime, bvalue);

                        //setEx的方法没有返回值，所以添加之后，还得查询它是否成功
                        T valueb = getCache(key);
                        if (value.equals(valueb)){
                            return true;
                        }
                    }
                    return false;
                }
            });

        } catch (Exception e) {
            e.getMessage();
            return false;
        }

        return result;
    }


}
