package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.mapper.TbContentDao;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentQuery;
import com.taotao.result.EasyUIResult;
import com.taotao.result.TaotaoResult;
import com.taotao.service.ContentService;

@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	private TbContentDao tbContentDao;
	
	@Override
	public EasyUIResult getContentList(long categoryId, int page, int rows) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, rows);
		TbContentQuery tbContentQuery = new TbContentQuery();
		TbContentQuery.Criteria criteria = tbContentQuery.createCriteria();
		criteria.andCategoryIdEqualTo(categoryId);
		List<TbContent> list = tbContentDao.selectByExampleWithBLOBs(tbContentQuery);
		PageInfo<TbContent> pageInfo = new PageInfo<>(list);
		EasyUIResult result = new EasyUIResult(pageInfo.getTotal(), list);
		return result;
	}

	@Override
	public TaotaoResult insertContent(TbContent tbContent) {
		// TODO Auto-generated method stub
		Date date = new Date();
		tbContent.setCreated(date);
		tbContent.setUpdated(date);
		tbContentDao.insert(tbContent);
		return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult deleteContent(List<Long> ids) {
		// TODO Auto-generated method stub
		TbContentQuery example = new TbContentQuery();
		TbContentQuery.Criteria criteria = example.createCriteria();
		criteria.andIdIn(ids);
		tbContentDao.deleteByExample(example);
		return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult updateContent(TbContent tbContent) {
		// TODO Auto-generated method stub
		tbContent.setUpdated(new Date());
		tbContentDao.updateByPrimaryKeySelective(tbContent);
		return TaotaoResult.ok();
	}

}
