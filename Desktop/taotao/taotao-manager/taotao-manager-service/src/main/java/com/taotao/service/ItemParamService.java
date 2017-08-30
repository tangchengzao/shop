package com.taotao.service;

import com.taotao.result.EasyUIResult;
import com.taotao.result.TaotaoResult;

public interface ItemParamService {
	
	public TaotaoResult getItemParamBycid(long cid);
	
	public EasyUIResult getItemParamList(int page, int rows);
	
	public TaotaoResult getItemParamByItemId(long itemId);
	
}
