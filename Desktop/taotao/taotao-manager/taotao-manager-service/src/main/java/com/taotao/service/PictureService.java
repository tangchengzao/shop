package com.taotao.service;


import org.springframework.web.multipart.MultipartFile;

import com.taotao.result.PictureResult;

public interface PictureService {
	public PictureResult uploadPicture(MultipartFile uploadFile);
	
	public boolean removePicture(String srcFile);
}
