package com.ric.util;

import freemarker.cache.FileTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


public class WordGeneratorWithFreemarker {
    private static Logger logger = LoggerFactory.getLogger(WordGeneratorWithFreemarker.class);
    private static Configuration configuration = null;  
    private static Map<String, Template> allTemplates = null;

    static {

    }  
	private WordGeneratorWithFreemarker(String savePath1) {
    }
  
    public static void createDoc(Map<String, Object> dataMap, String templateName,OutputStream out) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException {
    	Template t = allTemplates.get(templateName);
		
    	WordHtmlGeneratorHelper.handleAllObject(dataMap);
    	
        try {  
            // 这个地方不能使用FileWriter因为需要指定编码类型否则生成的Word文档会因为有无法识别的编码而无法打开  
            Writer w = new OutputStreamWriter(out);  
            t.process(dataMap, w);  
            w.close();  
        } catch (Exception ex) {  
            ex.printStackTrace();  
            throw new RuntimeException(ex);  
        }  
        return ;  
    }  
     public static void changeWord(String path,Map<String, Object> dataMap, String templateName,OutputStream out) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException {
	     TemplateLoader templateLoader=null;
	     //使用FileTemplateLoader
//        path="\\src\\main\\java\\brief22.ftl";
//        path=File.separator+"src"+File.separator+"main"+File.separator+"java"+File.separator+"wfxwtzs.ftl";
	     configuration = new Configuration(Configuration.VERSION_2_3_23);
	     configuration.setDefaultEncoding("utf-8");
	     configuration.setClassicCompatible(true);
	     allTemplates = new HashMap<String,Template>();
	     try {
			 templateLoader=new FileTemplateLoader(new File("E:\\WorkSpace\\freemarker2word"));
		     configuration.setTemplateLoader(templateLoader);
		     allTemplates.put("brief",configuration.getTemplate(path,"UTF-8") );
		     createDoc(dataMap,templateName,out);
	     } catch (IOException e) {
		     e.printStackTrace();
		     logger.error("导出简报错误日志---->>>"+e);
		     throw new RuntimeException(e);
	     }
     }
    
    public static void main(String[] args) {
		HashMap<String,Object> data=new HashMap<String, Object>();
		data.put("now", LocalDateTime.now().toString());
		String docFilePath = "d:\\temp" + ".doc";
		File f = new File(docFilePath);
		OutputStream out;
		try {
			out = new FileOutputStream(f);
			String path=File.separator+"src"+File.separator+"main"+File.separator+"java"+File.separator+"word.ftl";
			WordGeneratorWithFreemarker.changeWord(path,data,"brief",out);

		} catch (FileNotFoundException e) {
            e.printStackTrace();
		} catch (MalformedTemplateNameException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
    
}  