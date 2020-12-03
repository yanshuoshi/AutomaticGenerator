package com.yss.utils;

import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.NullCacheStorage;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

/**
 * @author:Shuoshi.Yan
 * @date: 2020/12/3 14:34
 */
public class FreemarkerUtils {

	private FreemarkerUtils() {
	}

	private static final Configuration CONFIGURATION = new Configuration(Configuration.VERSION_2_3_22);
	 
	public static final String DEFAULT_GENERATE_FILE_PATH =System.getProperty("user.dir")+"/code";
	static {
		//这里比较重要，用来指定加载模板所在的路径
		try {
			TemplateLoader templateLoader = new ClassTemplateLoader(FreemarkerUtils.class, "/");
			CONFIGURATION.setTemplateLoader(templateLoader);
			CONFIGURATION.setDefaultEncoding("UTF-8");
			CONFIGURATION.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
			CONFIGURATION.setCacheStorage(NullCacheStorage.INSTANCE);
		} catch (Exception e) {

		}

	}

	public static Template getTemplate(String templateName) throws IOException {
		try {
			return CONFIGURATION.getTemplate(templateName);
		} catch (IOException e) {
			throw e;
		}
	}

	public static void clearCache() {
		CONFIGURATION.clearTemplateCache();
	}

	public static void fprint(String name, Map<String, Object> rootMap, String path, String outFile) {
		FileWriter out = null;
		try {
			 
			File fileDir = new File(DEFAULT_GENERATE_FILE_PATH +"/"+ path + "/");
			if (!(fileDir.exists())) {
				fileDir.mkdirs();
			}
			File file = new File(DEFAULT_GENERATE_FILE_PATH +"/"+ path + "/" + outFile);
			out = new FileWriter(file);
			Template template = getTemplate(name);
			template.process(rootMap, out);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
		}
	}

}
