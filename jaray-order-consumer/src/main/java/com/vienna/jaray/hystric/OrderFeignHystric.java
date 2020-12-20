package com.vienna.jaray.hystric;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.vienna.jaray.feign.OrderFeignService;

@Component
public class OrderFeignHystric implements OrderFeignService {

	@Override
	@GetMapping(value = "/hello/say/{name}")
	public String sayHello(@PathVariable(value="name") String name) {
		return "Hello " +name+", This messge send failed ";
	}

}
