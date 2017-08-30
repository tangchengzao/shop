package com.taotao.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.taotao.common.utils.FtpUtil;
import com.taotao.common.utils.IDUtils;
import com.taotao.result.PictureResult;
import com.taotao.service.PictureService;

@Service
public class PictureServiceImpl implements PictureService {

	@Value("${FTP_ADDRESS}")
	private String FTP_ADDRESS;
	
	@Value("${FTP_PORT}")
	private Integer FTP_PORT;
	
	@Value("${FTP_USERNAME}")
	private String FTP_USERNAME;
	
	@Value("${FTP_PASSWORD}")
	private String FTP_PASSWORD;
	
	@Value("${FTP_BASE_PATH}")
	private String FTP_BASE_PATH;
	
	@Value("${IMAGE_BASE_URL}")
	private String IMAGE_BASE_URL;
	
	@Override
	public PictureResult uploadPicture(MultipartFile uploadFile) {
		// TODO Auto-generated method stub
		String path = savePicture(uploadFile);
		PictureResult result = new PictureResult(0, IMAGE_BASE_URL + path);
		return result;
	}
	
	public String savePicture(MultipartFile uploadFile) {
		String result = null;
		try{
			//上传文件功能实现
			//判断文件是否为空
			if (uploadFile.isEmpty()) {
				return null;
			}
			//上传文件以日期为单位分开存放,可以提高图片的查询速度
			String filePath = "/" + new SimpleDateFormat("yyyy").format(new Date()) + "/"
					+ new SimpleDateFormat("MM").format(new Date()) + "/"
					+ new SimpleDateFormat("dd").format(new Date());
			//接收上传文件
			String originalFilename = uploadFile.getOriginalFilename();
			//新文件名
			String newFileName = IDUtils.genImageName() + originalFilename.substring(originalFilename.lastIndexOf("."));
			//转存文件，上传到ftp服务器
			boolean isOK = FtpUtil.uploadFile(FTP_ADDRESS, FTP_PORT, FTP_USERNAME, FTP_PASSWORD, FTP_BASE_PATH, filePath, newFileName, uploadFile.getInputStream());
			if (isOK) {
				result = filePath + "/" + newFileName;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public boolean removePicture(String srcFile) {
		// TODO Auto-generated method stub
		boolean isOK = FtpUtil.removeFile(FTP_ADDRESS, FTP_PORT, FTP_USERNAME, FTP_PASSWORD, srcFile);
		return isOK;
	}

}
