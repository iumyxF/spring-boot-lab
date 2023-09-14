package com.example.plus.utils;

import com.example.plus.model.vo.UserVo;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author iumyxF
 * @description: 实体类生成mybatis sql
 * @date 2023/5/9 14:42
 */
public class ResultMapGenerator {

    public static void main(String[] args) {
        String resultMap = ResultMapGenerator.generate(UserVo.class);
        System.out.println(resultMap);
    }

    /**
     * 匹配大写字母的正则
     */
    private static final Pattern humpPattern = Pattern.compile("[A-Z]");

    /**
     * java类型和jdbc类型的对应关系
     */
    private static final Map<String, String> TYPE_MAP = new HashMap<>();

    static {
        //以下对应规则可根据自己的实际情况做修改
        TYPE_MAP.put("string", "VARCHAR");
        TYPE_MAP.put("boolean", "BIT");
        TYPE_MAP.put("byte", "TINYINT");
        TYPE_MAP.put("short", "SMALLINT");
        TYPE_MAP.put("integer", "INTEGER");
        TYPE_MAP.put("long", "BIGINT");
        TYPE_MAP.put("float", "REAL");
        TYPE_MAP.put("double", "DOUBLE");
        //TIMESTAMP,DATETIME
        TYPE_MAP.put("date", "DATE");
        TYPE_MAP.put("timestamp", "TIMESTAMP");
        TYPE_MAP.put("time", "TIME");
        //TYPE_MAP.put("bigdecimal", "DECIMAL");
    }

    /**
     * 生成ResultMap
     *
     * @param clazz 实体类的Class
     * @return String
     */
    public static String generate(Class<?> clazz) {
        String pkgName = clazz.getName();
        String clazzName = clazz.getSimpleName();
        String resultMapId = Character.toLowerCase(clazzName.charAt(0)) + clazzName.substring(1) + "Map";

        StringBuilder resultMap = new StringBuilder();
        resultMap.append("<resultMap id=\"");
        resultMap.append(resultMapId);
        resultMap.append("\" type=\"");
        resultMap.append(pkgName);
        resultMap.append("\">\n");

        Field[] fields = clazz.getDeclaredFields();
        for (Field f : fields) {
            String property = f.getName();
            String javaType = f.getType().getSimpleName();
            if ("serialVersionUID".equals(property)) {
                continue;//忽略掉这个属性
            }
            resultMap.append("    <result column=\"");
            resultMap.append(property2Column(property));
            resultMap.append("\" property=\"");
            resultMap.append(property);
            resultMap.append("\" jdbcType=\"");
            resultMap.append(javaType2jdbcType(javaType.toLowerCase()));
            resultMap.append("\" />\n");
        }
        resultMap.append("</resultMap>");
        return resultMap.toString();
    }

    /**
     * 驼峰转下划线命名
     */
    private static String property2Column(String property) {
        Matcher matcher = humpPattern.matcher(property);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 通过java类型获取jdbc类型
     */
    private static String javaType2jdbcType(String javaType) {
        String jdbcType = TYPE_MAP.get(javaType);
        return jdbcType == null ? "UNKNOWN" : jdbcType;
    }
}
