package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.result.EasyUIResult;
import com.taotao.result.TaotaoResult;
import com.taotao.service.ItemParamService;

@Controller
@RequestMapping("/item/param")
public class ItemParamController {
	
	@Autowired
	private ItemParamService itemParamService;
	
	@RequestMapping("/query/itemcatid/{itemCatId}")
	@ResponseBody
	public TaotaoResult getItemParamByCid(@PathVariable Long itemCatId) {
		System.out.println(itemCatId);
		TaotaoResult result = itemParamService.getItemParamBycid(itemCatId);
		return result;
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public EasyUIResult list(Integer page, Integer rows) {
		return itemParamService.getItemParamList(page, rows);
	}
	
	@RequestMapping("/item/query/{itemId}")
	@ResponseBody
	public TaotaoResult getItemParamByItemId(@PathVariable Long itemId) {
		return itemParamService.getItemParamByItemId(itemId);
	}
	
}
