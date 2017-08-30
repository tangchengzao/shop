package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.pojo.TbItem;
import com.taotao.result.EasyUIResult;
import com.taotao.result.TaotaoResult;
import com.taotao.service.ItemService;

@Controller
@RequestMapping("/item")
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/list")
	@ResponseBody
	public EasyUIResult list(@RequestParam(defaultValue="1")Integer page, @RequestParam(defaultValue="30")Integer rows) {
//		return null;
		return itemService.getItemList(page, rows);
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public TaotaoResult save(TbItem item, String desc, String itemParams) throws Exception {
		//添加商品信息
		itemService.saveItem(item, desc, itemParams);
		return TaotaoResult.ok();
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public TaotaoResult update(TbItem item, String desc, String itemParams) {
		return itemService.updateItem(item, desc, itemParams);
	}
	
	@RequestMapping("/desc/{itemId}")
	@ResponseBody
	public TaotaoResult desc(@PathVariable Long itemId) {
		return itemService.getDescById(itemId);
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public TaotaoResult delete(Long ids) {
		return itemService.deleteById(ids);
	}
}
