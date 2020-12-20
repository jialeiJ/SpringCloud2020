package com.vienna.jaray.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.vienna.jaray.config.PropConfig;
import com.vienna.jaray.entity.Producer;
import com.vienna.jaray.mapper.producer.ProducerMapper;

@RestController
public class OrderProducerController {
	
	@Value("${server.port}")
	private String port;
	
	@Autowired
	private ProducerMapper producerMapper;
	
	@Autowired
	private PropConfig propConfig;
	
	@GetMapping("/hello/say/{name}")
	public String sayHello(@PathVariable(value="name") String name) {
		Producer producer = new Producer();
		producer.setPname(name);
		producerMapper.add(producer);
		return "Hello "+ name + " " + port + " " + propConfig.getName();
	}

}
