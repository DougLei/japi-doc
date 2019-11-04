package com.douglei.api.doc.js.variable.entity.index;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.douglei.api.doc.metadata.ApiCatalogMetadata;
import com.douglei.api.doc.metadata.ApiMetadata;

/**
 * 
 * @author DougLei
 */
public class Index {
	private String title;
	private String version;
	private List<ApiCatalogs> apiCatalogs;
	
	public Index(String title, String version, short apiCatalogCount) {
		this.title = title;
		this.version = version;
		apiCatalogs = new ArrayList<ApiCatalogs>(apiCatalogCount);
	}

	public void addApiCatalog(ApiCatalogMetadata apiCatalog) {
		apiCatalogs.add(new ApiCatalogs(apiCatalog.getName(), apiCatalog.apiCount(), apiCatalog.getClassName()));
	}
	public String addApi(ApiMetadata api) {
		return apiCatalogs.get(apiCatalogs.size()-1).addApi(api.getName(), api.getMethodName());
	}
	
	public String getTitle() {
		return title;
	}
	public String getVersion() {
		return version;
	}
	public List<ApiCatalogs> getTreeData() {
		return apiCatalogs;
	}
	public String toJScript() {
		return "var index="+JSONObject.toJSONString(this) +"\n";
	}
}
