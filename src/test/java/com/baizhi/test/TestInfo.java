package com.baizhi.test;

import com.baizhi.dao.InfoDAO;
import com.baizhi.entity.Info;
import com.baizhi.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class TestInfo {

    //测试保存
    @Test
    public void testSaveInfo() {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        InfoDAO infoDAO = sqlSession.getMapper(InfoDAO.class);
        Info info = new Info();
        try {
            info.setCardno("123456789012345679");
            info.setAddress("这是第二个地址");
            infoDAO.save(info);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            MybatisUtil.close(sqlSession);
        }
    }
}
