package com.iweb.dao;

import com.iweb.pojo.Category;

import java.util.List;

/** 分类的Dao操作接口
 * 按照阿里的规范 如果你在接口方法上写好了注释 实现类的方法就不需要再写注释了
 * @author Guan
 * @date 2023/6/1 11:27
 */
public interface CategoryDao {
    /** 插入一条分类信息
     * @param category 包含了要添加的分类信息 其中id为空 因为id是自增长的 不需要开发者提供
     */
    void insert(Category category);

    /**  根据id修改分类的name属性值
     * @param category 提供需要被修改的name字段的值 已经根据哪一个id进行update的主键id
     *  update category set name = ? where id = ?
     */
    void update(Category category);

    /** 根据id或者name进行删除
     * @param category 如果提供的id不为空 则根据id删除 如果提供的name不为空 且id为空
     *                 则根据name进行删除 如果name id都为空 可以后续定义一个自定义异常进行抛出
     */
    void delete(Category category);

    /** 根据主键id获取指定的分类信息(单个)
     * @param id 要获取的分类的主键id
     * @return 存放了分类信息的Category对象
     */
    Category get(int id);

    /** 默认查询所有分类的数据
     * @return 所有分类的数据
     */
    List<Category> listAll();


    /** 根据你提供的limit分页参数 对分类数据进行分页查询
     * @param start limit的第一个参数 代表分页时候的起始位置
     * @param count limit的第二个参数 代表分页时候的截取行数
     * @return 分页查询的分类数据集合
     */
    List<Category> listByPage(int start,int count);

    /** 以key作为关键字 对分类进行全模糊的模糊查询 由于是全模糊 查询的时候无法通过索引查询
     * 只能在表数据较少的时候使用
     * @param key 模糊查询的关键字
     * @return 根据关键字进行模糊查询分类的返回结果
     */
    List<Category> listByNameLike(String key);
}
