package cn.hl.module.zuul.filter;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

/**
 * 检验请求是否带有认证token信息,实现安全认证
 * @author HULIN
 */
public class TokenFilter extends ZuulFilter{

	/**
	 * 具体执行内容
	 */
	@Override
	public Object run() {
		RequestContext context = RequestContext.getCurrentContext();
		HttpServletRequest req = context.getRequest();
		String token = req.getParameter("token");
		if(!StringUtils.isEmpty(token)) {
			if("123456".equals(token)) {
				context = success(context);
			}else {
				context = fail(context);
				context.setResponseBody("invalid token!");
			}
		}else {
			context = fail(context);
			context.setResponseBody("token can not be empty!");
		}
		return null;
	}

	private RequestContext success(RequestContext context) {
		context.setSendZuulResponse(true);
		context.setResponseStatusCode(200);
		context.set("isOk", true);
		return context;
	}
	
	private RequestContext fail(RequestContext context) {
		context.setSendZuulResponse(false);
		context.setResponseStatusCode(400);
		context.set("isOk", false);
		return context;
	}
	/**
	 * 是否执行filter
	 */
	@Override
	public boolean shouldFilter() {
		return true;
	}

	/**
	 * 优先级
	 */
	@Override
	public int filterOrder() {
		return 0;
	}

	/**
	 * filter类型
	 */
	@Override
	public String filterType() {
		return "pre";
	}

}
