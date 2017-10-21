package cn.llf.mongodb.core;

/**
 * Author：calvin
 * Date:  2017/10/21 0021
 * 描述：默认的集合名为类名首字母小写，所以当不传集合名字时，则类名必须和数据库集合名一致
 */
public interface IMongo {
    void create(Object entity);

    Object findById(Object id, Class entityClass);
}
