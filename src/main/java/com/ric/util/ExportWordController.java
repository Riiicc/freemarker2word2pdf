package com.ric.util;//package com.ric.util;
//import com.documents4j.api.DocumentType;
//import com.documents4j.api.IConverter;
//import com.documents4j.job.LocalConverter;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.*;
//import java.net.HttpURLConnection;
//import java.net.URL;
//
///**
// * 导出文书word
// *
// */
//@RestController
//@RequestMapping("/exchangeMt")
//public class ExportWordController {
//
//	@Value("${filemanage.savepath}")
//	private String savePath;
//
//	@Value("${filemanage.urlpath}")
//	private String urlpath;
//
//	@Value("${filemanage.remoteurlpath}")
//	private String remoteurlpath;
//
//
//	@RequestMapping(value = "/wfxwtzsJdsExp", method = RequestMethod.GET)
//	public String wfxwtzsJdsExp(@RequestParam String fileName, HttpServletResponse response, HttpServletRequest request) throws Exception {
//		String remoteHost1 = request.getRemoteAddr();
//		String newremote = "http://"+remoteHost1+"/zhzf/jianbao/";
//		String fileUrl = newremote + fileName;
//
//		URL url = new URL(fileUrl);
//		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//		conn.setConnectTimeout(3 * 1000);
//		conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
//
//		InputStream inputStream = conn.getInputStream();
//		byte[] getData = readInputStream(inputStream);
//		String savep = savePath + File.separator;
//		File saveDir = new File(savep);
//		if (!saveDir.exists()) {
//			saveDir.mkdirs();
//		}
//		String docFilePath = saveDir + File.separator + fileName;
//		File docfile = new File(docFilePath);
//		String docfileName = docfile.getName();
//		FileOutputStream fos = new FileOutputStream(docfile);
//		fos.write(getData);
//		if (fos != null) {
//			fos.close();
//		}
//		if (inputStream != null) {
//			inputStream.close();
//		}
//		String extName = docfileName.substring(0,docfileName.lastIndexOf("."));
//		File inputWord = new File(docFilePath);
//		File outputFile = new File(savep + extName + ".pdf");
//		InputStream docxInputStream = new FileInputStream(inputWord);
//		OutputStream outputStream = new FileOutputStream(outputFile);
//		IConverter converter = LocalConverter.builder().build();
//		converter.convert(docxInputStream).as(DocumentType.DOC).to(outputStream).as(DocumentType.PDF).execute();
//		docxInputStream.close();
//		outputStream.close();
//		converter.shutDown();
//		return urlpath + extName + ".pdf";
//
//	}
//
//	/**
//	 * 从输入流中获取字节数组
//	 *
//	 * @param inputStream
//	 * @return
//	 * @throws IOException
//	 */
//	public static byte[] readInputStream(InputStream inputStream) throws IOException {
//		byte[] buffer = new byte[1024];
//		int len = 0;
//		ByteArrayOutputStream bos = new ByteArrayOutputStream();
//		while ((len = inputStream.read(buffer)) != -1) {
//			bos.write(buffer, 0, len);
//		}
//		bos.close();
//		return bos.toByteArray();
//	}
//
//}
