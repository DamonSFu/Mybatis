package com.baizhi.test;

import com.baizhi.dao.DeptDAO;
import com.baizhi.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class TestDept {

    //测试查询所有
    @Test
    public void testQueryAll() {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        DeptDAO deptDAO = sqlSession.getMapper(DeptDAO.class);
        deptDAO.queryAll().forEach(dept -> {
            System.out.println("部门信息："+ dept);
            dept.getEmps().forEach(emp -> {
                System.out.println("-----员工信息：" + emp);
            });
            System.out.println("-------------------------------------------");
        });
        MybatisUtil.close(sqlSession);
    }
}
