package com.vienna.jaray.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.vienna.jaray.hystric.OrderFeignHystric;

@Component
@FeignClient(value = "jaray-order-producer", fallback = OrderFeignHystric.class)
public interface OrderFeignService {
	
	@GetMapping("/hello/say/{name}")
	public String sayHello(@PathVariable(value="name") String name);

}
