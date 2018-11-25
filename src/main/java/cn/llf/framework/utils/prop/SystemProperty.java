package cn.llf.framework.utils.prop;

/**
 * @author eleven
 * @date 2018/11/25
 * @description
 */
public interface SystemProperty {
    /**
     * 获取指定key的value
     * @param key
     * @return
     */
    String getProperty(String key);
}
