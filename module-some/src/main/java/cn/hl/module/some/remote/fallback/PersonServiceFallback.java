package cn.hl.module.some.remote.fallback;

import org.springframework.stereotype.Component;
import cn.hl.module.some.remote.PersonService;

/**
 * 熔断时调用fallback默认实现
 * @author HULIN
 */
@Component
public class PersonServiceFallback implements PersonService{

	@Override
	public String hello(String name) {
		return "hello,"+name+":this service is unavailable";
	}

}
