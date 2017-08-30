package com.taotao.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.pojo.TbContent;
import com.taotao.result.EasyUIResult;
import com.taotao.result.TaotaoResult;
import com.taotao.service.ContentService;


@Controller
@RequestMapping("/content")
public class ContentController {
	
	@Autowired
	private ContentService contentService;
	
	@RequestMapping("/query/list")
	@ResponseBody
	public EasyUIResult list(Long categoryId, Integer page, Integer rows) {
		return contentService.getContentList(categoryId, page, rows);
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public TaotaoResult save(TbContent tbContent) {
		return contentService.insertContent(tbContent);
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public TaotaoResult delete(Long[] ids) {
		List<Long> list = new ArrayList<>(Arrays.asList(ids));
		return contentService.deleteContent(list);
	}
	
	@RequestMapping("/edit")
	@ResponseBody
	public TaotaoResult edit(TbContent tbContent) {
		return contentService.updateContent(tbContent);
	}
}
