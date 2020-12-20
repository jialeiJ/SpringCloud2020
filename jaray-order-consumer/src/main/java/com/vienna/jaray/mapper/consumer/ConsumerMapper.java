package com.vienna.jaray.mapper.consumer;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.vienna.jaray.entity.Consumer;

@Mapper
@Repository
public interface ConsumerMapper {
	
	/**
	 * 添加信息
	 * @param complaint 对象
	 * @return 添加结果
	 */
	int add(@Param("consumer") Consumer consumer);

}
