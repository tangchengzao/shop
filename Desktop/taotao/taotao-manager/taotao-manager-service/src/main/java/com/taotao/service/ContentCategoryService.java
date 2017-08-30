package com.taotao.service;

import java.util.List;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.result.TaotaoResult;

public interface ContentCategoryService {

	public List<EUTreeNode> getContentCategoryList(Long parentId);

	public TaotaoResult insertContentCategory(long parentId, String name);
	
	public TaotaoResult updateContentCategory(long id, String name);
}
