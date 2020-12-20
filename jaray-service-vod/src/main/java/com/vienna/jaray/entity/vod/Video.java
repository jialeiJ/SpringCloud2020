package com.vienna.jaray.entity.vod;

import lombok.Data;

/**
 * @author Jaray
 * @date 2020年12月12日 20:43
 * @description: 视频实体类
 */
@Data
public class Video {
	private int id;
	private String name;
	private String category;
	private String videoId;
	private String videoAddr;
}
