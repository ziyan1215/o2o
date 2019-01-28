package com.xz.myo2o.util;

import static org.junit.Assert.*;

import org.junit.Test;

/**
* @author 作者
* @version 创建时间：2019年1月14日 下午3:49:06
* 类说明
*/
public class FileUtilTest {
	
	public static FileUtil fn=new FileUtil();
	
	@Test
	public void test() {
		System.out.println(fn.getImgBasePath());
		System.out.println("生成随机文件名："+fn.getRandomFileName());
	}

}
