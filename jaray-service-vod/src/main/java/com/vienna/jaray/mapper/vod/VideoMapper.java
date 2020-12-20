package com.vienna.jaray.mapper.vod;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vienna.jaray.entity.vod.Video;
import com.vienna.jaray.model.system.CommonParamsModel;

/**
 * @author Jaray
 * @date 2020年12月12日 22:08
 * @Description: 视频Mapper
 */
@Mapper
@Repository
public interface VideoMapper extends BaseMapper<Video> {
	
	/**
     *	 查询视频列表
     * @param commonParams 表格数据通用参数对象
     * @return 视频列表
     */
    List<Video> findAll(@Param("common")CommonParamsModel commonParams);

    /**
     * 	查询视频
     * @param id id
     * @return 视频信息
     */
    Video findById(@Param("id")String id);

    /**
     * 	查询视频
     * @param name 视频名称
     * @return 视频信息
     */
    Video findByName(@Param("name")String name);

    /**
     * 	添加视频
     * @param video 视频对象
     * @return 添加结果
     */
    int add(@Param("entity") Video video);
    /**
     * 	删除用户
     * @param ids id数组
     * @return 删除结果
     */
    int deleteByIds(@Param("ids")String[] ids);

    /**
     *	 更新用户
     * @param video 视频对象
     * @return 更新结果
     */
    int updateById(@Param("entity") Video video);

}
