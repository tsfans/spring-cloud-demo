package cn.hl.module.some.remote;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import cn.hl.module.some.remote.fallback.PersonServiceFallback;

/**
 * 远程调用接口
 * @author HULIN
 */
@FeignClient(name="service-person",fallback=PersonServiceFallback.class)
public interface PersonService {

	@RequestMapping(value="/hello",method=RequestMethod.GET)
	public String hello(@RequestParam("name")String name);
}
