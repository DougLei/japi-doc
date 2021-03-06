package com.douglei.api.doc.metadata;

import org.springframework.web.bind.annotation.RequestMapping;

import com.douglei.tools.StringUtil;

/**
 * 
 * @author DougLei
 */
class Utils {
	
	/**
	 * 获取url, 如果url中存在\, 则替换成/
	 * @param url
	 * @param requestMapping
	 */
	public static String getUrl(String url, RequestMapping requestMapping) {
		if(StringUtil.isEmpty(url)) {
			if(requestMapping != null && requestMapping.value().length > 0) 
				url = requestMapping.value()[0];
		}
		if(url.indexOf('\\') != -1) 
			return url.replace('\\', '/');
		return url;
	}
}
