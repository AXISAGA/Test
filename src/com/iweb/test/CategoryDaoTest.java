package com.iweb.test;

import com.iweb.dao.CategoryDao;
import com.iweb.dao.CategoryDaoImpl;
import com.iweb.pojo.Category;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.Reader;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

/**
 * @author Guan
 * @date 2023/6/1 14:16
 */
public class CategoryDaoTest {
    CategoryDao dao;

    //会在所有@Test声明的方法运行之前运行 一般用于做初始化工作
    @Before
    public void init() {
        dao = new CategoryDaoImpl();
    }

    @After
    public void destroy() {
        //被该注解所修饰的方法用于进行收尾工作或者是资源释放工作
    }

    //标记该注解的方法可以不依赖于main方法直接运行
    @Test
    public void test1() {
        List<Category> categoryList = dao.listAll();
        System.out.println(categoryList);
        //Assert类的相关方法可以用来判断测试的结果和你预期的结果是否一致
//        Assert.assertNotNull(categoryList);
    }

    @Test
    public void test2() {
//        List<Category> categoryList = dao.listByNameLike("达");
//        System.out.println(categoryList);
//        Assert.assertNotNull(dao.listByNameLike("达"));
//        List<Category> categoryList = dao.listByPage(0,5);
//        System.out.println(categoryList);
//        Category c = dao.get(6);
//        Assert.assertNotNull(c);

        //insert
//        Category c = new Category();
//        c.setName("零度可乐");
//        dao.insert(c);
//        test1();
//        System.out.println(c);

        //delete
//        Category c = new Category();
//        c.setId(9);
//        c.setName("营养快线");
//        dao.delete(c);
//        test1();
        new Hashtable<>().put(null,null);


    }


}
