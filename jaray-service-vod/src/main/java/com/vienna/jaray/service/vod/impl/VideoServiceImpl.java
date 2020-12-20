package com.vienna.jaray.service.vod.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vienna.jaray.common.ResponseResult;
import com.vienna.jaray.entity.vod.Video;
import com.vienna.jaray.mapper.vod.VideoMapper;
import com.vienna.jaray.model.system.CommonParamsModel;
import com.vienna.jaray.service.vod.VideoService;

@Service
public class VideoServiceImpl implements VideoService {
	
	@Autowired
	private VideoMapper videoMapper;

	@Override
	public ResponseResult findAll(CommonParamsModel commonParams) {
		//设置分页信息(第几页，每页数量)
        PageHelper.startPage(commonParams.getPageNum(), commonParams.getPageSize());
        List<Video> videoList = videoMapper.findAll(commonParams);;

        //取记录总条数
        PageInfo<?> pageInfo = new PageInfo<>(videoList);
        return ResponseResult.success().add("videoList", pageInfo);
	}

	@Override
	public ResponseResult findById(String id) {
		Video video = videoMapper.findById(id);
        return ResponseResult.success().add("video", video);
	}

	@Override
	public ResponseResult findByName(String name) {
		Video video = videoMapper.findByName(name);
        return ResponseResult.success().add("video", video);
	}

	@Override
	public ResponseResult add(Video video) {
		ResponseResult responseResult = ResponseResult.fail();
        int result = videoMapper.add(video);
        if(result > 0){
            responseResult = ResponseResult.success().add("video", video);
        }
        return responseResult;
	}

	@Override
	public ResponseResult deleteByIds(String[] ids) {
		ResponseResult responseResult = ResponseResult.fail();
        int result = videoMapper.deleteByIds(ids);
        if(result > 0){
            responseResult = ResponseResult.success().add("result", result);
        }
        return responseResult;
	}

	@Override
	public ResponseResult updateById(Video video) {
		ResponseResult responseResult = ResponseResult.fail();
        int result = videoMapper.updateById(video);
        if(result > 0){
            responseResult = ResponseResult.success().add("result", result);
        }
        return responseResult;
	}

}
