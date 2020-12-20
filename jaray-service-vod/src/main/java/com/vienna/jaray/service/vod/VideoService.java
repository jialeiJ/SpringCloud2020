package com.vienna.jaray.service.vod;

import com.vienna.jaray.common.ResponseResult;
import com.vienna.jaray.entity.vod.Video;
import com.vienna.jaray.model.system.CommonParamsModel;

public interface VideoService {
	/**
     * 	查询视频列表
     *
     * @param commonParams 表格数据通用参数对象
     * @return 视频列表
     */
    ResponseResult findAll(CommonParamsModel commonParams);

    /**
     * 	查询视频
     * @param id id
     * @return 视频信息
     */
    ResponseResult findById(String id);

    /**
     *	 查询视频
     * @param name 视频名称
     * @return 视频信息
     */
    ResponseResult findByName(String name);

    /**
     * 	添加用户
     * @param video 系统用户对象
     * @return 添加结果
     */
    ResponseResult add(Video video);

    /**
     * 	删除用户
     * @param ids id数组
     * @return 删除结果
     */
    ResponseResult deleteByIds(String[] ids);

    /**
     * 	更新视频
     * @param video 视频对象
     * @return 更新结果
     */
    ResponseResult updateById(Video video);
}
