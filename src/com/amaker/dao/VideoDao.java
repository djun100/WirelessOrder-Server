package com.amaker.dao;

import java.util.List;

import com.amaker.bean.Video;


public interface VideoDao {
	
	/**
	 * ¢‘
	 */
	int addVideo(Video video);

	/**
	¡¨
	 * @return
	 */
	List<Video> getAllVideo();

	/**
	 * 
	 * @param id
	 */
	int overdueVideo(String id);

	/**
	 *
	 * @param video
	 */
	int updateVideo(Video video);

	/**
	 * O
	 * @param videoId
	 * @return
	 */
	Video getVideoById(String videoId);

}
