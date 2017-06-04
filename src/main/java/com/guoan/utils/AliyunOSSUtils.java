package com.guoan.utils;

import com.aliyun.openservices.oss.OSSClient;
import com.aliyun.openservices.oss.model.*;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AliyunOSSUtils {

	public static final String BUCKET_NAME = "guoanshequ";
	public static final String ACCESS_ID = "Nw6mJXzklWOfJfAH";
	public static final String ACCESS_KEY = "aAPCjVoPFDF915DgsmCh89lwuE3Pwk";
	public static final String OSS_ENDPOINT = "http://oss-cn-beijing.aliyuncs.com";
	public static final String GUOAN_CDN = "http://imgcdn.guoanshequ.com/";

	/**
	 *
	 * @Description : 上传文件
	 * @创建人：赵彤
	 * @创建时间：2014年7月15日 下午5:39:34
	 * @param
	 * @return void
	 * @throws
	 */
	public static void upload_pic(String fileName, String filePath) {
		OSSClient client = new OSSClient(OSS_ENDPOINT, ACCESS_ID, ACCESS_KEY);
		// 获取指定文件的输入流
		File file = new File(filePath);
		try {
//			System.out.println(file.length());
			InputStream input = new FileInputStream(file);

			// 创建上传Object的Metadata
			ObjectMetadata metadata = new ObjectMetadata();
			metadata.setContentLength(file.length());

			// 上传Object.
			PutObjectResult result = client.putObject(AliyunOSSUtils.BUCKET_NAME, fileName, input, metadata);

			System.out.println("上传至阿里云 返回的tag -> " + result.getETag());
			// 删除本地文件
			try {
				FileUtils.forceDelete(new File(filePath));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static String createFoler() {

		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");

		String formatDate = simpleDateFormat.format(date);
		String objectName = formatDate+"/";
		OSSClient client = new OSSClient(OSS_ENDPOINT, ACCESS_ID, ACCESS_KEY);
		ObjectMetadata objectMeta = new ObjectMetadata();
		byte[] buffer = new byte[0];
		ByteArrayInputStream in = new ByteArrayInputStream(buffer);
		objectMeta.setContentLength(0);
		try {
			client.putObject(AliyunOSSUtils.BUCKET_NAME, objectName, in, objectMeta);
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return objectName;
	}

	/**
	 * @Description : 获得指定命名空间的所有文件
	 * @创建人：赵彤
	 * @创建时间：2014年7月15日 下午6:05:25
	 * @param @param bucketName
	 * @return void
	 * @throws
	 */
	public void listObjects(String bucketName, OSSClient client) {

		// 获取指定bucket下的所有Object信息
		ObjectListing listing = client.listObjects(bucketName);

		// 遍历所有Object
		for (OSSObjectSummary objectSummary : listing.getObjectSummaries()) {
//			System.out.println(objectSummary.getKey());
		}
	}

	/**
	 *
	 * @Description : 新建Bucket(就是命名空间)
	 *              Bucket是OSS上的命名空间，相当于数据的容器，可以存储若干数据实体（Object）。
	 * @创建人：赵彤
	 * @创建时间：2014年7月15日 下午5:18:57
	 * @param
	 * @return void
	 * @throws
	 */
	public void createBucket(OSSClient client, String bucketName) {
		// 新建一个Bucket
		Bucket bucket = client.createBucket(bucketName);
	}

	/**
	 * @Description : 获得第几个周
	 * @创建人：赵彤
	 * @创建时间：2015年2月10日 上午11:58:30
	 * @param @return
	 * @return int
	 * @throws
	 */
	public static int getWeekNumber() {
		Calendar calendar = Calendar.getInstance();
		calendar.setFirstDayOfWeek(Calendar.MONDAY);

		calendar.setTime(new Date());
		return calendar.get(Calendar.WEEK_OF_YEAR);

	}

	public static void upload_pic_test(String fileName, String filePath, OSSClient client) {
		// 获取指定文件的输入流
		File file = new File(filePath);
		try {
//			System.out.println(file.length());
			InputStream input = new FileInputStream(file);

			// 创建上传Object的Metadata
			ObjectMetadata metadata = new ObjectMetadata();
			metadata.setContentLength(file.length());

			// 上传Object.
			PutObjectResult result = client.putObject(AliyunOSSUtils.BUCKET_NAME, fileName, input, metadata);

//			System.out.println(result.getETag());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
