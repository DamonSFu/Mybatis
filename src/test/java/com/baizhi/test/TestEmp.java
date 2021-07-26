package com.baizhi.test;

import com.baizhi.dao.EmpDAO;
import com.baizhi.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class TestEmp {

    //测试查询所有员工信息
    @Test
    public void testQueryAll() {

        SqlSession sqlSession = MybatisUtil.getSqlSession();
        EmpDAO empDAO = sqlSession.getMapper(EmpDAO.class);
        empDAO.queryAll().forEach(emp -> {
            System.out.println("当前员工信息：" + emp + "当前部门：" + emp.getDept());
        });
        MybatisUtil.close(sqlSession);
    }
}
