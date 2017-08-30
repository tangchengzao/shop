package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.result.TaotaoResult;
import com.taotao.service.ContentCategoryService;

@Controller
@RequestMapping("/content/category")
public class ContentCategoryController {

	@Autowired
	private ContentCategoryService contentCategoryService;
	
	@RequestMapping("/list")
	@ResponseBody
	public List<EUTreeNode> list(@RequestParam(value = "id",defaultValue = "0")Long parentId) {
		return contentCategoryService.getContentCategoryList(parentId);
	}
	
	@RequestMapping("/create")
	@ResponseBody
	public TaotaoResult create(Long parentId, String name) {
		return contentCategoryService.insertContentCategory(parentId, name);
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public TaotaoResult update(Long id, String name) {
		return contentCategoryService.updateContentCategory(id, name);
	}
	
}
