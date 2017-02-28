package com.zhiwu.global;


import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

/**
 * Created by 韦庆明 on 2016/12/13.
 */
public class MyBatisSession {

    /**
     * 获取数据库访问Session
     **/
    public static SqlSession getSqlSession()
    {
        SqlSession sqlSession = null;
        String path = "";
        try {
            //使用MyBatis提供的Resources类加载mybatis的配置文件
            Reader stream = Resources.getResourceAsReader("mybatis-config.xml");

            //还可以根据配置的相应环境读取相应的数据库环境
            // SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(
            // stream, "development");

            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(stream);
            sqlSession = factory.openSession();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sqlSession;
    }

}
