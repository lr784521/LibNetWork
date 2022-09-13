package com.huayun.lib_network.util;



/**
 * 检查工具
 */
public class CheckUtil {
    /**
     * 判断是否为基本类型的默认值
     * @param className
     * @return true 是基础数据类型  false 不是基础数据类型
     */
    public static boolean isBaseDefaultValue(Class<?> className) {
         if(className.equals(java.lang.String.class)) {
            return true;
        }else if(className.equals(java.lang.Integer.class)) {
            return true;
        } else if(className.equals(java.lang.Byte.class)) {
            return true;
        } else if(className.equals(java.lang.Long.class)) {
            return true;
        } else if(className.equals(java.lang.Double.class)) {
            return true;
        } else if(className.equals(java.lang.Float.class)) {
            return true;
        } else if(className.equals(java.lang.Character.class)) {
            return true;
        } else if(className.equals(java.lang.Short.class)) {
            return true;
        } else if(className.equals(java.lang.Boolean.class)) {
            return true;
        }
        return false;
    }

}
