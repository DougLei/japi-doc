package com.douglei.api.doc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.douglei.tools.instances.file.resources.reader.ResourcesReader;

public class Reader {
	public static void main(String[] args) throws FileNotFoundException {
		Map<String, String> map = new HashMap<String, String>();
		
		File[] htmls = new File("D:\\softwares\\developments\\workspaces\\japi-doc\\src\\main\\resources\\template\\js\\").listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".html");
			}
		});
		for (File file : htmls) {
			map.put(file.getName().substring(0, file.getName().lastIndexOf(".")), new ResourcesReader(new FileInputStream(file)).readAll().toString());
		}
		
		
		
		System.out.println(JSONObject.toJSONString(map));
	}
}
