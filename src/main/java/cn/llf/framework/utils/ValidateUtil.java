package cn.llf.framework.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 创建者：   linlf
 * 创建时间： 2018/2/24
 * 描述：
 * 正则表达式基本语法：
 *                  *  ：零次或多次匹配前面的字符或子表达式
 *                  +  ：一次或多次匹配前面的字符或子表达式
 *                  ?  ：零次或一次匹配前面的字符或子表达式
 *                  ^  ：匹配输入字符串开始的位置
 *                  $  ：匹配输入字符串结尾的位置
 *                  \w ：匹配输入字符串结尾的位置
 *                  \W ：与任何非单词字符匹配。与"[^A-Za-z0-9_]"等效
 *                  \s ：匹配任何空白字符，包括空格、制表符、换页符等。与 [ \f\n\r\t\v] 等效。
 *                  \S ：匹配任何非空白字符。与 [^ \f\n\r\t\v] 等效。。
 *                  \d ：数字字符匹配。等效于 [0-9]。
 *                  \D ：非数字字符匹配。等效于 [^0-9]。
 *                  \n ：换行符匹配。等效于 \x0a 和 \cJ。
 *                  \r ：匹配一个回车符。等效于 \x0d 和 \cM。
 *                  {n,m} ：M 和 n 是非负整数，其中 n <= m。匹配至少 n 次，至多 m 次
 *                  x|y ：匹配 x 或 y。例如，'z|food' 匹配"z"或"food"。'(z|f)ood' 匹配"zood"或"food"。
 *
 */
public class ValidateUtil {
    /**
     * 验证是否是两位数的double数据，
     * 应用场景：校验成绩
     * @param num
     * @return
     */
    public static boolean isDouble(String num){
        String regex = "(^\\d*[.]?\\d{0,2})";
        //编译正则表达式
        Pattern pattern =  Pattern.compile(regex);
        //获取 matcher 对象
        Matcher matcher = pattern.matcher(num);
        //获取结果
        return matcher.matches();
    }

}
