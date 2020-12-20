package com.vienna.jaray.controller.vod;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vienna.jaray.annotation.LogAnnotation;
import com.vienna.jaray.common.ResponseResult;
import com.vienna.jaray.entity.vod.Video;
import com.vienna.jaray.model.system.CommonParamsModel;
import com.vienna.jaray.service.vod.VideoService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Jaray
 * @date 2020年12月12日 20:59
 * @description: 视频控制器
 */
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/education/video")
public class VideoController {
	
	@Autowired
	private VideoService videoService;
	
	@LogAnnotation(value = "查询视频列表")
	@PostMapping("/find/all")
	//@PreAuthorize("hasAuthority('sys:video:view')")
    public ResponseResult findAll(CommonParamsModel commonParams) {
        ResponseResult resultMsg = null;
        try {
            resultMsg = videoService.findAll(commonParams);
        } catch (Exception e) {
            log.error("findAll Method Exception", e);
        }
        return resultMsg;
    }

    @LogAnnotation(value = "添加视频")
    @PostMapping("/add")
    //@PreAuthorize("hasAuthority('sys:video:add')")
    public ResponseResult add(Video video) {
        ResponseResult resultMsg = null;
        try {
            resultMsg = videoService.add(video);
        } catch (Exception e) {
            log.error("add Method Exception", e);
        }
        return resultMsg;
    }

    @LogAnnotation(value = "删除视频")
    @PostMapping("/deleteByIds")
    //@PreAuthorize("hasAuthority('sys:video:delete')")
    public ResponseResult deleteByIds(String ids) {
        ResponseResult resultMsg = null;
        try {
            resultMsg = videoService.deleteByIds(ids.split(","));
        } catch (Exception e) {
            log.error("deleteByIds Method Exception", e);
        }
        return resultMsg;
    }

    @LogAnnotation(value = "更新视频")
    @PostMapping("/updateById")
    //@PreAuthorize("hasAuthority('sys:video:edit')")
    public ResponseResult updateById(Video video) {
        ResponseResult resultMsg = null;
        try {
            resultMsg = videoService.updateById(video);
        } catch (Exception e) {
            log.error("updateById Method Exception", e);
        }
        return resultMsg;
    }


    @LogAnnotation(value = "查询视频")
    @PostMapping("/findById")
    //@PreAuthorize("hasAuthority('sys:video:view')")
    public ResponseResult findById(String id) {
        ResponseResult resultMsg = null;
        try {
            resultMsg = videoService.findById(id);
        } catch (Exception e) {
            log.error("findById Method Exception", e);
        }
        return resultMsg;
    }

}
