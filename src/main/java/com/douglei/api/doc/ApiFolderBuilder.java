package com.douglei.api.doc;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.douglei.tools.ExceptionUtil;

/**
 * 构建api文件数据到文件夹
 * @author DougLei
 */
public class ApiFolderBuilder extends ApiDocBuilder{
	private static final Logger logger = LoggerFactory.getLogger(ApiFolderBuilder.class);
	
	@Override
	protected void build_() throws Exception {
		File folder = new File(path + fileName + "-" + version);
		if(!folder.exists()) 
			folder.mkdirs();
		
		ZipEntry entryIn;
		byte[] b = new byte[1024];
		short len;
		try(ZipInputStream zipIn = new ZipInputStream(getClass().getClassLoader().getResourceAsStream(apiDocTemplateFileName))){
			while((entryIn=zipIn.getNextEntry()) != null) {
				if(!entryIn.isDirectory()) {
					try(OutputStream out = new FileOutputStream(getFile(folder.getAbsolutePath() + File.separatorChar + entryIn.getName()))){
						while((len=(short) zipIn.read(b)) != -1) {
							out.write(b, 0, len);
						}
					}catch(IOException e){
						logger.error(">> 在输出[{}]文件时, 出现异常: {}", folder.getAbsolutePath() + File.separatorChar + entryIn.getName(), ExceptionUtil.getStackTrace(e));
					}
				}
				zipIn.closeEntry();
			}
		}
		
		try(OutputStream out = new FileOutputStream(getFile(path + fileName + "-" + version + File.separatorChar + "js" + File.separatorChar + "api.data.js"))){
			writeApiData(out);
		}catch(Exception e){
			throw e;
		}
	}
	
	// 获取文件对象
	private File getFile(String path) {
		File file = new File(path);
		File parentFolder = file.getParentFile();
		if(!parentFolder.exists()) 
			parentFolder.mkdirs();
		return file;
	}
}
