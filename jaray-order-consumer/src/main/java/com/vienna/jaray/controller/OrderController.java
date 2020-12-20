package com.vienna.jaray.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.vienna.jaray.entity.Consumer;
import com.vienna.jaray.feign.OrderFeignService;
import com.vienna.jaray.mapper.consumer.ConsumerMapper;

@RestController
public class OrderController {
	
	@Autowired
	private ConsumerMapper consumerMapper;
	
	@Autowired
	private OrderFeignService orderFeignService;
	
	@GetMapping("/hello/say/{name}")
	public String sayHello(@PathVariable(value="name") String name) {
		Consumer consumer = new Consumer();
		consumer.setCname(name);
		consumerMapper.add(consumer);
		return orderFeignService.sayHello(name);
	}

}
