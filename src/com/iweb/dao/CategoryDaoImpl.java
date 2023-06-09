package com.iweb.dao;

import com.iweb.pojo.Category;
import com.iweb.test.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Guan
 * @date 2023/6/1 13:31
 */
public class CategoryDaoImpl implements CategoryDao {
    @Override
    public void insert(Category category) {
        String sql = "insert into category(name) values(?)";
        try (
                Connection c = DBUtil.getConnection();
                //用于在后续获取插入数据对应的自增长主键
                PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {
            ps.setString(1, category.getName());
            ps.execute();
            //在执行完成之后 mysql会为新插入的数据提供自增长主键
            //如果你希望在数据插入之后 获取这个自增长的主键 需要执行特定方法
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                //从结果集中 获取数据库返回的自增长主键 再将这个自增长主键存入到参数category中
                category.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Category category) {
        String sql = "update category set name = ? where id = ?";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, category.getName());
            ps.setInt(2, category.getId());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Category category) {
        if (category.getId() != 0 & category.getName() == null) {
            String sql = "delete from category where id = ?";
            try (Connection c = DBUtil.getConnection();
                 PreparedStatement ps = c.prepareStatement(sql)) {
                ps.setInt(1, category.getId());
                ps.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (category.getId() == 0 & category.getName() != null) {
            String sql = "delete from category where name = ?";
            try (Connection c = DBUtil.getConnection();
                 PreparedStatement ps = c.prepareStatement(sql)) {
                ps.setString(1, category.getName());
                ps.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            try {
                throw new Exception("参数有误!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Category get(int id) {
        Category category = null;
        String sql = "select * from category where id = ?";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                category = new Category();
                String name = rs.getString("name");
                category.setId(id);
                category.setName(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return category;
    }

    @Override
    public List<Category> listAll() {
        return listByPage(0, Integer.MAX_VALUE);
    }

    @Override
    public List<Category> listByPage(int start, int count) {
        List<Category> categoryList = new ArrayList<>();
        String sql = "select * from category limit ?,?";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, start);
            ps.setInt(2, count);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Category category = new Category(id, name);
                categoryList.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categoryList.size() == 0 ? null : categoryList;
    }

    @Override
    public List<Category> listByNameLike(String key) {
        List<Category> categoryList = new ArrayList<>();
        String sql = "select * from category where name like concat('%',?,'%')";
        try (
                Connection c = DBUtil.getConnection();
                PreparedStatement ps = c.prepareStatement(sql);
                ) {
                ps.setString(1,key);
                ResultSet rs = ps.executeQuery();
                while (rs.next()){
                    Category category = new Category(rs.getInt("id")
                            ,rs.getString("name"));
                    categoryList.add(category);
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categoryList.size() == 0 ? null : categoryList;
    }
}
