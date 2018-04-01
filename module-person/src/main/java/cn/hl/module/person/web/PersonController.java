package cn.hl.module.person.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author HULIN
 */
@RestController
@RefreshScope	//使用该注解的类，会在接到SpringCloud配置中心配置刷新的时候，自动将新的配置更新到该类对应的字段中。
public class PersonController {

	@Value("${config.name}")
	private String config;
	
	@RequestMapping(value="/hello",method=RequestMethod.GET)
	public String hello(@RequestParam("name")String name) {
		return "hello,"+name;
	}
	
	@RequestMapping(value="/config",method=RequestMethod.GET)
	public String config() {
		return this.config;
	}
}
