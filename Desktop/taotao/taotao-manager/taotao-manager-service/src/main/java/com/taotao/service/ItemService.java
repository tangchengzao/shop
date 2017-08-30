package com.taotao.service;

import com.taotao.pojo.TbItem;
import com.taotao.result.EasyUIResult;
import com.taotao.result.TaotaoResult;

public interface ItemService {

	
	public EasyUIResult getItemList(int page, int rows);
	
	public void saveItem(TbItem item, String desc, String itemParams) throws Exception;
	
	public TaotaoResult getDescById(Long itemId);
	
	public TaotaoResult deleteById(Long itemId);
	
	public TaotaoResult updateItem(TbItem item, String desc, String itemParams);
}
