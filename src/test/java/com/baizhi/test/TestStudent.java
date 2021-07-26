package com.baizhi.test;

import com.baizhi.dao.StudentDAO;
import com.baizhi.entity.Student;
import com.baizhi.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class TestStudent {

    @Test
    public void TestQueryById() {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        StudentDAO studentDAO = sqlSession.getMapper(StudentDAO.class);
        Student student = new Student();
        student = studentDAO.queryById(11);
        System.out.println("学生信息："+student);
        student.getCourses().forEach(course -> {
            System.out.println("------课程信息："+ course);
        });
        MybatisUtil.close(sqlSession);

    }
}
