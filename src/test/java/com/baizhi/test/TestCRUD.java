package com.baizhi.test;

import com.baizhi.dao.UserDAO;
import com.baizhi.entity.User;
import com.baizhi.utils.MybatisUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

public class TestCRUD {

    //删除操作
    @Test
    public void delete() throws IOException {
        //创建MybatisUtil.java后替换以上代码
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        try {
            UserDAO userDAO = sqlSession.getMapper(UserDAO.class);
            int delete = userDAO.delete(5);
            System.out.println("删除的条数："+delete);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            //创建MybatisUtil.java后替换以上代码
            MybatisUtil.close(sqlSession);
        }
    }

    //更新操作
    @Test
    public void update() throws IOException {
        //读取配置文件
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        //创建sqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        //获取sqlSession执行sql语句
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            //获取DAO对象
            UserDAO userDAO = sqlSession.getMapper(UserDAO.class);
            // 更新数据
            // 更新：有值更新 不存在值保留原始的值 1.先查再改 2.动态sql
            User user = new User();
            user.setId(1);
            user.setName("小王吧");
            userDAO.update(user);
            int update = userDAO.update(user);
            System.out.println("修改的条数："+ update);
            sqlSession.commit(); //提交
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }

    //保存操作
    @Test
    public void save() throws IOException {
        //读取配置文件
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        //创建sqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        //获取sqlSession执行sql语句
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            //获取DAO对象
            UserDAO userDAO = sqlSession.getMapper(UserDAO.class);
            User user = new User();
            user.setName("小liu");
            user.setAge(24);
            user.setBir(new Date());
            userDAO.save(user);
            //提交事务
            sqlSession.commit();
        }catch (Exception e){
            e.printStackTrace();
            sqlSession.rollback();
        }finally {
            sqlSession.close();
        }
    }
}
