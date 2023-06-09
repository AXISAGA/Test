package com.iweb.test;

import java.sql.*;

/**
 * jdbc
 * java database connection
 * Connection
 * Statement/PreparedStatement
 * Driver
 * ResultSet
 * 接口化设计的好处 jdk开发组只需要定义
 * jdbc所应该实现的方法和接口
 * 实现类由数据库开发者 自行实现
 * 并且统一了jdbc的方法调用规范
 * 数据库开发者为jdbc所编写的代码 最终以jar包的形式出现
 *
 * @author Guan
 * @date 2023/6/1 8:36
 */
public class Test {


    public static void testInsert(String name) {
        String sql = "insert into category(name) values('" + name + "')";
        try (Connection c = DBUtil.getConnection();
             Statement s = c.createStatement();) {
            s.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void testDelete(int id) {
        String sql = "delete from category where id =" + id;
        try (Connection c = DBUtil.getConnection();
             Statement s = c.createStatement();) {
            s.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void testUpdate(String name, int id) {
        //所有字符串参数值 需要使用 ''进行修饰 数字类型的值 不需要单引号进行修饰
//        String sql = "update category set name = '"+name+"' where id = "+id;
        String sql = String.format("update category set name = '%s' where id = %d", name, id);
        try (Connection c = DBUtil.getConnection();
             Statement s = c.createStatement();) {
            s.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //ResultSet 结果集
    public static void testSelect() {
        String sql = "select * from category";
        try (Connection c = DBUtil.getConnection();
             Statement s = c.createStatement();) {
            //java中 所有增删改方法使用execute执行
            //查询语句需要使用 executeQuery方法执行
            //并且需要准备一个ResultSet 结果集类型的变量去接受从数据库查询的结果
            ResultSet rs = s.executeQuery(sql);
            //结果集的遍历和集合的迭代器类似
            while (rs.next()) {
                //每次循环 是从查询结果的一行读取记录
                //读取数据的时候 需要按照字段的数据类型进行读取
                //遍历结果的时候 使用什么样的get方法 取决于字段的数据类型为什么类型
                //get方法的参数 可以使用字段的名称作为参数
                //也可以使用 数字标号 1 2  来表示 你获取的字段是查询结果中的第几个字段
                int id = rs.getInt(1);
                String name = rs.getString("name");
                System.out.println(id + "-" + name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        //驱动加载
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            System.out.println("驱动加载成功!");
//            //连接创建 需要提供数据库的用户名 密码 以及url
//            Connection connection = DriverManager.
//                    getConnection("jdbc:mysql://localhost:3306/gls?characterEncoding=utf-8","root","a12345");
//            System.out.println("连接创建成功,连接对象为:"+connection);
//            //提供sql语句
//            String sql = "insert into category(name) values ('可乐')";
//            //创建编译语句对象Statement
//            Statement s = connection.createStatement();
//            //借助编译语句执行sql语句
//            s.execute(sql);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        testInsert("雷碧");
        //请你仿照我们的插入 快速完成 testDelete 和 testUpdate 方法
        //要求 testDelete方法 根据id进行数据删除
        //testUpdate方法 根据id修改分类的name
        //请你完成这两个方法 并且进行测试
//        testUpdate("雪碧",2);
//        testDelete(2);
//          testSelect();
//        testSelectByNameLike("达");
//        testSelectPage(3,3);
    // ORM  object relation model  对象映射模型

    }

    //分页查询
    public static void testSelectPage(int start, int count) {
        String sql = "select * from category limit ?,?";
        try (Connection c = DBUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1,start);
            ps.setInt(2,count);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                System.out.println(id + "-" + name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //模糊查询和分页查询
    //使用PreparedStatement预编译语句 代替 Statement
    public static void testSelectByNameLike(String key) {
        //使用预编译语句代替编译语句
        //Statement 是先传参 再编译 性能差
        //PreparedStatement 是先编译 再传参 性能较好
        //1.PreparedStatement 性能更好
        //2.PreparedStatement使用更方便
        //3.PreparedStatement 可以有效避免sql注入攻击的问题
        //如果使用的是预编译传参 参数本身的单引号 会由预编译语句自行提供
        //但是 我们自己提供的字符串 还是需要单引号进行修饰
        String sql = "select * from category where name like concat('%',?,'%')";
        try (
                Connection c = DBUtil.getConnection();
//                预编译 需要在创建预编译语句的时候 就将sql语句提供 方便进行编译
                PreparedStatement ps = c.prepareStatement(sql);
        ) {
            //使用预编译语句的情况下 sql语句中的每一个?代表的是一个待传的参数
            //将sql语句中的第一个？替换为key的值
            ps.setString(1, key);
            //执行语句并获取执行结果集
            ResultSet rs = ps.executeQuery();
            //遍历结果集
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                System.out.println(id + "-" + name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
