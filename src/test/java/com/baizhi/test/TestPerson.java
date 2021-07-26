package com.baizhi.test;

import com.baizhi.dao.PersonDAO;
import com.baizhi.entity.Person;
import com.baizhi.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class TestPerson {

    //测试保存用户信息
    @Test
    public void testSavePerson() {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        PersonDAO personDAO = sqlSession.getMapper(PersonDAO.class);
        Person person = new Person();
        try {
            person.setName("第二个人");
            person.setAge(25);
            //外键信息
            person.setCardno("123456789012345679");
            personDAO.save(person);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            MybatisUtil.close(sqlSession);
        }
    }

    //查询用户信息
    @Test
    public void testQueryAll() {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        PersonDAO personDAO = sqlSession.getMapper(PersonDAO.class);
        personDAO.queryAll().forEach(person -> {
            System.out.println("当前用户信息："+person+",身份信息："+person.getInfo());
        });
//        personDAO.queryAll().forEach(person -> System.out.println(person));
        MybatisUtil.close(sqlSession);
    }
}
