package com.yangkg.ssm.common.utils;


import org.apache.commons.codec.binary.Base64;
import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;

public class Base64Util {
	private static final Logger LOGGER = LoggerFactory.getLogger(Base64Util.class.getName());
	private static Base64 base = null;
	static{
		LOGGER.info("开始初始化协议转换对象");
		base = new Base64();
		LOGGER.info("初始化协议转换对象完成");
	}
	public static String base64Encode(String source) throws UnsupportedEncodingException{
		DateTime begin = DateTime.now();
		String str = new String(base.encode(source.getBytes("UTF-8")));
		long l = new Duration(begin,DateTime.now()).getMillis();
		LOGGER.info("base编码String耗时:{}",l);
		return str;
	}
	
	public static String base64Decode(String source) throws UnsupportedEncodingException{
		DateTime begin = DateTime.now();
		String str = new String(base.decode(source.getBytes()),"UTF-8");
		long l = new Duration(begin,DateTime.now()).getMillis();
		LOGGER.info("base解码String耗时:{}",l);
		return str;
	}
	
	public static byte[] base64Encode(byte[] source){
		DateTime begin = DateTime.now();
		byte[] res = base.encode(source);
		long l = new Duration(begin,DateTime.now()).getMillis();
		LOGGER.info("base编码byte耗时:{}",l);
		return res;
	}
	
	public static byte[] base64Decode(byte[] source){
		DateTime begin = DateTime.now();
		byte[] res =base.decode(source);
		long l = new Duration(begin,DateTime.now()).getMillis();
		LOGGER.info("base解码byte耗时:{}",l);
		return res;
	}
}
