package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.mapper.TbItemCatDao;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatQuery;
import com.taotao.service.ItemCatService;

@Service
public class ItemCatServiceImple implements ItemCatService {

	@Autowired
	private TbItemCatDao tbItemCatDao;
	
	@Override
	public List<EUTreeNode> getCatList(Long parentId) {
		// TODO Auto-generated method stub
		TbItemCatQuery example = new TbItemCatQuery();
		TbItemCatQuery.Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbItemCat> list = tbItemCatDao.selectByExample(example);
		List<EUTreeNode> resultList = new ArrayList<>();
		
		for (TbItemCat tbItemCat : list) {
			EUTreeNode node = new EUTreeNode();
			node.setId(tbItemCat.getId());
			node.setText(tbItemCat.getName());
			node.setState(tbItemCat.getIsParent()?"closed":"open");
			resultList.add(node);
		}
		return resultList;
	}

}
