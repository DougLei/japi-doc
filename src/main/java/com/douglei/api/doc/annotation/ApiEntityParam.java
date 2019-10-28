package com.douglei.api.doc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.douglei.api.doc.types.DataType;
import com.douglei.api.doc.types.ParamStructType;

/**
 * api实体参数
 * @author DougLei
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD}) // 用到方法上, 方法最好只能是get/set方法, 或者是没有参数, 且有返回值, 或没有返回值, 且只有一个传入参数
public @interface ApiEntityParam {
	
	/**
	 * 参数名, 如果没有配置, 则使用属性名, 获取方法名, 如果是get/set方法, 则会去除get/set后, 将首字母小写, 作为属性名
	 * @return
	 */
	String name() default "";
	
	/**
	 * 数据类型, 如果没有配置, 则使用属性的类型, 方法的返回值类型, 或方法的参数类型
	 * @return
	 */
	DataType dataType() default DataType.NULL;
	
	/**
	 * 日期格式化
	 * @return
	 */
	String dateFormatPattern() default "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * 参数长度, -1表示没有限制
	 * @return
	 */
	short length() default -1;
	
	/**
	 * 参数精度, -1表示没有限制
	 * @return
	 */
	short precision() default -1;
	
	/**
	 * 是否必须
	 */
	boolean required() default false;
	
	/**
	 * 参数的默认值
	 */
	String defaultValue() default "-";
	
	/**
	 * 参数描述
	 * @return
	 */
	String description() default "-";
	
	/**
	 * 示例值
	 * @return
	 */
	String egValue() default "";
	
	/**
	 * 对应的参数实体class
	 * @return
	 */
	Class<?> entity() default Object.class;

	/**
	 * 对应参数的实体class结构, 如果没有配置, 则会根据属性的类型, 方法的参数类型, 或方法的返回值类型自动判断
	 * @return
	 */
	ParamStructType entityStruct() default ParamStructType.NULL;
}
