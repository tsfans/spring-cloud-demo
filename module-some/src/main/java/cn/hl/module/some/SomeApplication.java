package cn.hl.module.some;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;


@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class SomeApplication {

	
    public static void main( String[] args ) {
    	SpringApplication.run(SomeApplication.class, args);
    }
}
