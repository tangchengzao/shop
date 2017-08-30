package com.taotao.service;

import java.util.List;

import com.taotao.pojo.TbContent;
import com.taotao.result.EasyUIResult;
import com.taotao.result.TaotaoResult;

public interface ContentService {
	
	public EasyUIResult getContentList(long categoryId, int page, int rows);
	
	public TaotaoResult insertContent(TbContent tbContent);
	
	public TaotaoResult deleteContent(List<Long> ids);
	
	public TaotaoResult updateContent(TbContent tbContent);
	
}
