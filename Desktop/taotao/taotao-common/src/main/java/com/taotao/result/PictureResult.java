package com.taotao.result;

/**
 * 上传图片返回值
 * 
 * @author
 *
 */
public class PictureResult {
	/**
	 * 上传图片返回值，成功：0 失败：1
	 */
	private Integer error;
	/**
	 * 回显图片使用的url
	 */
	private String url;
	/**
	 * 错误时的错误消息
	 */
	private String message;
	
	public PictureResult(Integer error, String url, String message) {
		this.error = error;
		this.url = url;
		this.message = message;
	}
	
	public PictureResult(Integer error, String url) {
		this.error = error;
		this.url = url;
		this.message = null;
	}

	public Integer getError() {
		return error;
	}

	public void setError(Integer error) {
		this.error = error;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
