package com.ric.util;
import com.documents4j.api.DocumentType;
import com.documents4j.api.IConverter;
import com.documents4j.job.LocalConverter;

import java.io.*;

/**
 * 导出文书word
 *
 */
public class ExportWordUtil {

//	@Value("${filemanage.savepath}")
//	private String savePath;
//
//	@Value("${filemanage.urlpath}")
//	private String urlpath;
//
//	@Value("${filemanage.remoteurlpath}")
//	private String remoteurlpath;


	public static String wfxwtzsJdsExp() throws Exception {
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
		File docfile = new File("d:\\temp" + ".doc");
		String docfileName = docfile.getName();
//		FileOutputStream fos = new FileOutputStream(docfile);
//		fos.write(getData);
//		if (fos != null) {
//			fos.close();
//		}
//		if (inputStream != null) {
//			inputStream.close();
//		}
		String extName = docfileName.substring(0,docfileName.lastIndexOf("."));
//		File inputWord = new File(docFilePath);
		File outputFile = new File("d:\\" + extName + "x.pdf");
		InputStream docxInputStream = new FileInputStream(docfile);
		OutputStream outputStream = new FileOutputStream(outputFile);
		IConverter converter = LocalConverter.builder().build();
		converter.convert(docxInputStream).as(DocumentType.DOC).to(outputStream).as(DocumentType.PDF).execute();
		docxInputStream.close();
		outputStream.close();
		converter.shutDown();
		return extName + ".pdf";

	}

	/**
	 * 从输入流中获取字节数组
	 *
	 * @param inputStream
	 * @return
	 * @throws IOException
	 */
	public static byte[] readInputStream(InputStream inputStream) throws IOException {
		byte[] buffer = new byte[1024];
		int len = 0;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		while ((len = inputStream.read(buffer)) != -1) {
			bos.write(buffer, 0, len);
		}
		bos.close();
		return bos.toByteArray();
	}

	public static void main(String[] args) {
		try {
			String s = wfxwtzsJdsExp();
		}catch (Exception e){
			e.printStackTrace();
		}

	}
}
