package cn.hl.module.zuul.fallback;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

/**
 * 路由熔断
 * 当路由的服务出现异常时返回默认信息
 * @author HULIN
 */
@Component
public class ServiceFalback implements FallbackProvider{
	
	private static final Logger logger = LoggerFactory.getLogger(ServiceFalback.class);

	@Override
	public ClientHttpResponse fallbackResponse() {
		return new ClientHttpResponse() {

			@Override
			public InputStream getBody() throws IOException {
				return new ByteArrayInputStream("this service is unavailable".getBytes());
			}

			@Override
			public HttpHeaders getHeaders() {
				HttpHeaders header = new HttpHeaders();
				header.setContentType(MediaType.TEXT_PLAIN);
				return header;
			}

			@Override
			public void close() {
				
			}

			@Override
			public int getRawStatusCode() throws IOException {
				return 200;
			}

			@Override
			public HttpStatus getStatusCode() throws IOException {
				return HttpStatus.OK;
			}

			@Override
			public String getStatusText() throws IOException {
				return "OK";
			}
			
		};
	}

	/**
	 * 指定要处理的服务
	 */
	@Override
	public String getRoute() {
		return "service-some";
	}

	@Override
	public ClientHttpResponse fallbackResponse(Throwable cause) {
		if(cause!=null && cause.getCause()!=null) {
			String reason = cause.getCause().getMessage();
			logger.info("Exception {}"+reason);
		}
		return fallbackResponse();
	}

}
