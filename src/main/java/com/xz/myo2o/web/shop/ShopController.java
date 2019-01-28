package com.xz.myo2o.web.shop;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author 作者
 * @version 创建时间：2018年12月6日 下午5:03:43 类说明
 * 
 */
@Controller
@RequestMapping("/shop")
public class ShopController {
	@RequestMapping("/index")
	public String index() {
		return "index";
	}

	@RequestMapping("/json")
	@ResponseBody
	public String json(Model model, String name) {
		model.addAttribute(name, "sss");
		System.out.println(name);
		return "index";

	}

	@RequestMapping("/test")
	@ResponseBody
	public String test() {
		String resultString = "{\"result\":true}"; // 注意一定是双引号 "{\"result\":\"success\"}"
		return resultString;
	}

	@RequestMapping("/uploadtest")
	@ResponseBody
	public String uploadTest(HttpServletRequest request, MultipartFile file, HttpSession session) {
		System.out.println("upload");
		/**
		 * 1.获得客户机信息
		 */
		String requestUrl = request.getRequestURL().toString();// 得到请求的URL地址
		String requestUri = request.getRequestURI();// 得到请求的资源
		String queryString = request.getQueryString();// 得到请求的URL地址中附带的参数
		String remoteAddr = request.getRemoteAddr();// 得到来访者的IP地址
		String remoteHost = request.getRemoteHost();
		int remotePort = request.getRemotePort();
		String remoteUser = request.getRemoteUser();
		String method = request.getMethod();// 得到请求URL地址时使用的方法
		String pathInfo = request.getPathInfo();
		String localAddr = request.getLocalAddr();// 获取WEB服务器的IP地址
		String localName = request.getLocalName();// 获取WEB服务器的主机名
		// response.setCharacterEncoding("UTF-8");//设置将字符以"UTF-8"编码输出到客户端浏览器
		// 通过设置响应头控制浏览器以UTF-8的编码显示数据，如果不加这句话，那么浏览器显示的将是乱码
		// response.setHeader("content-type", "text/html;charset=UTF-8");
		// PrintWriter out = response.getWriter();
		System.out.println("获取到的客户机信息如下：");
		System.out.println("<hr/>");
		System.out.println("请求的URL地址：" + requestUrl);
		System.out.println("<br/>");
		System.out.println("请求的资源：" + requestUri);
		System.out.println("<br/>");
		System.out.println("请求的URL地址中附带的参数：" + queryString);
		System.out.println("<br/>");
		System.out.println("来访者的IP地址：" + remoteAddr);
		System.out.println("<br/>");
		System.out.println("来访者的主机名：" + remoteHost);
		System.out.println("<br/>");
		System.out.println("使用的端口号：" + remotePort);
		System.out.println("<br/>");
		System.out.println("remoteUser：" + remoteUser);
		System.out.println("<br/>");
		System.out.println("请求使用的方法：" + method);
		System.out.println("<br/>");
		System.out.println("pathInfo：" + pathInfo);
		System.out.println("<br/>");
		System.out.println("localAddr：" + localAddr);
		System.out.println("<br/>");
		System.out.println("localName：" + localName);

		// 文件不为空
		if (!file.isEmpty()) {
			// 文件存放路径
			String path = request.getServletContext().getRealPath("/");
			System.out.println(path);
			// 文件名称
			String name = String.valueOf(new Date().getTime() + "_" + file.getOriginalFilename());
			System.out.println(name);
			File destFile = new File("E:\\", name);
			// 转存文件
			try {
				file.transferTo(destFile);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
			// 访问的url
			String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
					+ request.getContextPath() + "/" + name;
			System.out.println(url);
		}
		ModelAndView mv = new ModelAndView();
		mv.setViewName("uploadSuccess");
		return "index";

	}

}
