package com.douglei.api.doc.types.entity;

import com.douglei.api.doc.ApiDocBuilderContext;

/**
 * 
 * @author DougLei
 */
public class DataTypeMatchEntity {
	private String class_;
	private String dataType;
	
	public Class<? extends Object> getClass_() throws ClassNotFoundException {
		return ApiDocBuilderContext.getClassLoader().loadClass(class_);
	}
	public String getDataType() {
		return dataType;
	}
}
