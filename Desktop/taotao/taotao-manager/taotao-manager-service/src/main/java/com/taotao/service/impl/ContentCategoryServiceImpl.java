package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.mapper.TbContentCategoryDao;
import com.taotao.pojo.TbContentCategory;
import com.taotao.pojo.TbContentCategoryQuery;
import com.taotao.result.TaotaoResult;
import com.taotao.service.ContentCategoryService;

@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

	@Autowired
	private TbContentCategoryDao tbContentCategoryDao;
	
	@Override
	public List<EUTreeNode> getContentCategoryList(Long parentId) {
		// TODO Auto-generated method stub
		TbContentCategoryQuery example = new TbContentCategoryQuery();
		TbContentCategoryQuery.Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbContentCategory> list = tbContentCategoryDao.selectByExample(example);
		List<EUTreeNode> resultlist = new ArrayList<>();
		
		for (TbContentCategory item : list) {
			EUTreeNode node = new EUTreeNode();
			node.setId(item.getId());
			node.setText(item.getName());
			node.setState(item.getIsParent()?"closed":"open");
			resultlist.add(node);
		}
		return resultlist;
	}

	@Override
	public TaotaoResult insertContentCategory(long parentId, String name) {
		// TODO Auto-generated method stub
		Date date = new Date();
		TbContentCategory tbContentCategory = new TbContentCategory();
		tbContentCategory.setParentId(parentId);
		tbContentCategory.setCreated(date);
		tbContentCategory.setUpdated(date);
		tbContentCategory.setIsParent(false);
		tbContentCategory.setStatus(1);
		tbContentCategory.setSortOrder(1);
		tbContentCategory.setName(name);
		TbContentCategory parent = tbContentCategoryDao.selectByPrimaryKey(parentId);
		if (!parent.getIsParent()) {
			parent.setIsParent(true);
			tbContentCategoryDao.updateByPrimaryKey(parent);
		}
		long id = tbContentCategoryDao.insert(tbContentCategory);
		return TaotaoResult.ok(id);
	}

	@Override
	public TaotaoResult updateContentCategory(long id, String name) {
		// TODO Auto-generated method stub
		TbContentCategory tbContentCategory = new TbContentCategory();
		tbContentCategory.setUpdated(new Date());
		tbContentCategory.setId(id);
		tbContentCategory.setName(name);
		tbContentCategoryDao.updateByPrimaryKeySelective(tbContentCategory);
		return TaotaoResult.ok();
	}

}
