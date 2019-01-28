package com.xz.myo2o;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
* @author 作者
* @version 创建时间：2018年12月6日 上午11:24:26
* 类说明配置spring和junit整合，junit启动时加载springIOC容器 spring-test,junit
*/

@RunWith(SpringJUnit4ClassRunner.class)
// 告诉junit spring配置文件
@ContextConfiguration({ "classpath:spring/spring-dao.xml", "classpath:spring/spring-service.xml" })
public class BaseTest {

}
