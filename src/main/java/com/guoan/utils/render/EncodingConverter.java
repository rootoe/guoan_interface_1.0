package com.guoan.utils.render;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
/**
* @Title: EncodingConverter.java
* @Package com.guoan.utils.render
* @Description: 字符转换
* @author 赵彤 
* @date 2014年10月5日 下午12:17:56
* @version V1.0
 */
public class EncodingConverter {

	public static final String UTF_8 = "UTF-8";

	public static InputStream converter(InputStream is, String srcEnc, String tarEnc) throws IOException {

		// builder a reader using source encoding
		BufferedReader reader = new BufferedReader(new InputStreamReader(is, srcEnc));
		StringBuilder sb = new StringBuilder();

		// read content
		int c;
		while ((c = reader.read()) != -1) {
			sb.append((char) c);
		}
		reader.close();

		// get byte array
		InputStream tarIs = new ByteArrayInputStream(sb.toString().getBytes(tarEnc));
		return tarIs;
	}

	public static InputStream converterToUTF(InputStream is, String srcEnc) throws IOException {

		// builder a reader using source encoding
		Reader reader = new InputStreamReader(is, srcEnc);
		StringBuilder sb = new StringBuilder();

		// read content
		int c;
		while ((c = reader.read()) != -1) {
			sb.append((char) c);
		}
		reader.close();

		// get byte array
		InputStream tarIs = new ByteArrayInputStream(sb.toString().getBytes("utf-8"));
		return tarIs;
	}

	public static byte[] converterToByteArray(InputStream is, String srcEnc, String tarEnc) throws IOException {

		// builder a reader using source encoding
		Reader reader = new InputStreamReader(is, srcEnc);
		StringBuilder sb = new StringBuilder();

		// read content
		int c;
		while ((c = reader.read()) != -1) {
			sb.append((char) c);
		}
		reader.close();

		// get byte array
		return sb.toString().getBytes(tarEnc);
	}

	public static byte[] converterToUTFByteArray(InputStream is, String srcEnc) throws IOException {

		// builder a reader using source encoding
		Reader reader = new InputStreamReader(is, srcEnc);
		StringBuilder sb = new StringBuilder();

		// read content
		int c;
		while ((c = reader.read()) != -1) {
			sb.append((char) c);
		}
		reader.close();

		// get byte array
		return sb.toString().getBytes("utf-8");
	}

	public static String converterToString(InputStream is, String srcEnc, String tarEnc) throws IOException {

		// builder a reader using source encoding
		Reader reader = new InputStreamReader(is, srcEnc);
		StringBuilder sb = new StringBuilder();

		// read content
		int c;
		while ((c = reader.read()) != -1) {
			sb.append((char) c);
		}
		reader.close();

		return sb.toString();
	}

	public static String converterToUTFString(InputStream is, String srcEnc) throws IOException {

		// builder a reader using source encoding
//		BufferedReader reader = new BufferedReader(new InputStreamReader(is, srcEnc));
//		StringBuilder sb = new StringBuilder();

		// read content
//		int c;
//		while ((c = reader.read()) != -1) {
//			sb.append((char) c);
//		}
		// reader.close();
		// is.close();
		// return sb.toString();
		
		if(null == is){
			return null;
		}
		
		byte[] bytes = new byte[1024 * 1024];

		int nRead = 1;
		int nTotalRead = 0;
		while (nRead > 0) {
			nRead = is.read(bytes, nTotalRead, bytes.length - nTotalRead);
			if (nRead > 0)
				nTotalRead = nTotalRead + nRead;
		}
		String str = new String(bytes, 0, nTotalRead, srcEnc);
		System.out.println("JsonUtil 调用 EncodingConverter 的方法 converterToUTFString 解析的request请求 -> " + str);
		
		is.close();
		
		return str;
		
	}

	public static InputStream converter(byte[] bytes, String srcEnc, String tarEnc) throws IOException {

		// builder a reader using source encoding
		InputStream is = new ByteArrayInputStream(bytes);
		Reader reader = new InputStreamReader(is, srcEnc);
		StringBuilder sb = new StringBuilder();

		// read content
		int c;
		while ((c = reader.read()) != -1) {
			sb.append((char) c);
		}
		reader.close();

		// get byte array
		InputStream tarIs = new ByteArrayInputStream(sb.toString().getBytes(tarEnc));
		return tarIs;
	}

	public static InputStream converterToUTF(byte[] bytes, String srcEnc) throws IOException {

		// builder a reader using source encoding
		InputStream is = new ByteArrayInputStream(bytes);
		Reader reader = new InputStreamReader(is, srcEnc);
		StringBuilder sb = new StringBuilder();

		// read content
		int c;
		while ((c = reader.read()) != -1) {
			sb.append((char) c);
		}
		reader.close();

		// get byte array
		InputStream tarIs = new ByteArrayInputStream(sb.toString().getBytes("utf-8"));
		return tarIs;
	}

	public static byte[] converterToByteArray(byte[] bytes, String srcEnc, String tarEnc) throws IOException {

		// builder a reader using source encoding
		InputStream is = new ByteArrayInputStream(bytes);
		Reader reader = new InputStreamReader(is, srcEnc);
		StringBuilder sb = new StringBuilder();

		// read content
		int c;
		while ((c = reader.read()) != -1) {
			sb.append((char) c);
		}
		reader.close();

		// get byte array
		return sb.toString().getBytes(tarEnc);
	}

	public static byte[] converterToUTFByteArray(byte[] bytes, String srcEnc) throws IOException {

		// builder a reader using source encoding
		InputStream is = new ByteArrayInputStream(bytes);
		Reader reader = new InputStreamReader(is, srcEnc);
		StringBuilder sb = new StringBuilder();

		// read content
		int c;
		while ((c = reader.read()) != -1) {
			sb.append((char) c);
		}
		reader.close();

		// get byte array
		return sb.toString().getBytes("utf-8");
	}

	public static String converterToString(byte[] bytes, String srcEnc, String tarEnc) throws IOException {

		// builder a reader using source encoding
		InputStream is = new ByteArrayInputStream(bytes);
		Reader reader = new InputStreamReader(is, srcEnc);
		StringBuilder sb = new StringBuilder();

		// read content
		int c;
		while ((c = reader.read()) != -1) {
			sb.append((char) c);
		}
		reader.close();

		return sb.toString();
	}

	public static String converterToUTFString(byte[] bytes, String srcEnc) throws IOException {

		// builder a reader using source encoding
		InputStream is = new ByteArrayInputStream(bytes);
		Reader reader = new InputStreamReader(is, srcEnc);
		StringBuilder sb = new StringBuilder();

		// read content
		int c;
		while ((c = reader.read()) != -1) {
			sb.append((char) c);
		}
		reader.close();

		return sb.toString();
	}

	public static void main(String[] args) throws IOException {
		InputStream is = new FileInputStream("G:/out.txt");
		is = converter(is, "utf-8", "gbk");
		Reader isr = new InputStreamReader(is, "gbk");
		StringBuilder sb = new StringBuilder();
		int c;
		while ((c = isr.read()) != -1) {
			sb.append((char) c);
		}
		is.close();
		System.out.println(sb.toString());
		
		EncodingConverter.converterToUTFString(is, "UTF-8");
		
		
		
		
		
	}
}
