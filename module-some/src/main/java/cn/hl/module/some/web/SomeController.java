package cn.hl.module.some.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import cn.hl.module.some.remote.PersonService;

/**
 * @author HULIN
 */
@RestController
@RefreshScope	//使用该注解的类，会在接到SpringCloud配置中心配置刷新的时候，自动将新的配置更新到该类对应的字段中。
public class SomeController {

	private static final Logger logger = LoggerFactory.getLogger(SomeController.class);
	
	@Autowired
	PersonService ps;
	
	@Value("${config.name}")
	String config;
	
	/**
	 * 测试服务调用
	 * @param name
	 * @return
	 */
	@RequestMapping(value="/hello/{name}",method=RequestMethod.GET)
	public String hello(@PathVariable("name")String name) {
		return ps.hello(name);
	}
	
	/**
	 * 测试全局配置
	 * @return
	 */
	@RequestMapping(value="/config",method=RequestMethod.GET)
	public String config() {
		return this.config;
	}
	/**
	 * 测试自动重试
	 * @param name
	 * @return
	 */
	@RequestMapping(value="/retry/{name}",method=RequestMethod.GET)
	public String retry(@PathVariable("name")String name) {
		logger.info("request retry with "+name);
		try {
			Thread.sleep(1000000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "hello,"+name;
	}
}
