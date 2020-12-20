package com.vienna.jaray.mapper.producer;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.vienna.jaray.entity.Producer;

@Mapper
@Repository
public interface ProducerMapper {
	
	/**
	 * 添加信息
	 * @param complaint 对象
	 * @return 添加结果
	 */
	int add(@Param("producer") Producer producer);

}
