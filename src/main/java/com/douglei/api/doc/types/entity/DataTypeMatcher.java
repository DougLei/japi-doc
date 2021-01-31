package com.douglei.api.doc.types.entity;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.douglei.api.doc.ApiDocBuilderContext;
import com.douglei.api.doc.types.DataType;
import com.douglei.tools.file.reader.PropertiesReader;

/**
 * 
 * @author DougLei
 */
public class DataTypeMatcher {
	private static final Map<String, DataType> dataTypeMap = new HashMap<String, DataType>(8);
	private static final Map<Class<? extends Object>, DataType> cache = new HashMap<Class<? extends Object>, DataType>(32);
	static {
		dataTypeMap.put(DataType.INTEGER.name(), DataType.INTEGER);
		dataTypeMap.put(DataType.DOUBLE.name(), DataType.DOUBLE);
		dataTypeMap.put(DataType.STRING.name(), DataType.STRING);
		dataTypeMap.put(DataType.DATE.name(), DataType.DATE);
		dataTypeMap.put(DataType.BOOLEAN.name(), DataType.BOOLEAN);
		dataTypeMap.put(DataType.OBJECT.name(), DataType.OBJECT);
		
		cache.put(byte.class, DataType.INTEGER);
		cache.put(short.class, DataType.INTEGER);
		cache.put(int.class, DataType.INTEGER);
		cache.put(long.class, DataType.INTEGER);
		cache.put(Byte.class, DataType.INTEGER);
		cache.put(Short.class, DataType.INTEGER);
		cache.put(Integer.class, DataType.INTEGER);
		cache.put(Long.class, DataType.INTEGER);
		
		cache.put(float.class, DataType.DOUBLE);
		cache.put(double.class, DataType.DOUBLE);
		cache.put(Float.class, DataType.DOUBLE);
		cache.put(Double.class, DataType.DOUBLE);
		
		cache.put(char.class, DataType.STRING);
		cache.put(Character.class, DataType.STRING);
		cache.put(String.class, DataType.STRING);
		
		cache.put(Date.class, DataType.DATE);
		
		cache.put(boolean.class, DataType.BOOLEAN);
		cache.put(Boolean.class, DataType.BOOLEAN);
		
		cache.put(Object.class, DataType.OBJECT);
		cache.put(List.class, DataType.OBJECT);
		cache.put(Set.class, DataType.OBJECT);
		cache.put(Map.class, DataType.OBJECT);
		
		PropertiesReader reader = new PropertiesReader("japi.doc.datatype.match.config.properties");
		if(reader.ready()) {
			reader.entrySet().forEach(entry -> {
				try {
					cache.put(ApiDocBuilderContext.getClassLoader().loadClass(entry.getValue().toString()), toValue(entry.getKey().toString()));
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			});
		}
	}
	
	private static DataType toValue(String type) {
		DataType dt = dataTypeMap.get(type.toUpperCase());
		if(dt == null) {
			throw new IllegalAccessError("目前不支持DataType="+type);
		}
		return dt;
	}
	
	/**
	 * 根据传入的类型, 决定是哪种数据类型
	 * @param type
	 * @return
	 */
	public static DataType match(Class<?> type) {
		if(type.isArray()) return DataType.OBJECT;
		if(type.isEnum()) return DataType.STRING;
		
		DataType dataType = cache.get(type);
		if(dataType == null) {
			throw new IllegalAccessError("目前不支持属性class=["+type+"]的类型");
		}
		return dataType;
	}
	
	/**
	 * 添加数据类型的匹配实体
	 * @param entities
	 * @throws ClassNotFoundException 
	 */
	public static void addDataTypeMatchEntities(DataTypeMatchEntity... entities) {
		for (DataTypeMatchEntity entity : entities) {
			try {
				cache.put(entity.getClass_(), toValue(entity.getDataType()));
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
}
