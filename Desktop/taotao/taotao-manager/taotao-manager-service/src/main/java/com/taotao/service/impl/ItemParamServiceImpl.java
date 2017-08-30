package com.taotao.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.mapper.TbItemParamDao;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamQuery;
import com.taotao.result.EasyUIResult;
import com.taotao.result.TaotaoResult;
import com.taotao.service.ItemParamService;

@Service
public class ItemParamServiceImpl implements ItemParamService {

	@Autowired
	private TbItemParamDao tbItemParamDao;
	
	@Override
	public TaotaoResult getItemParamBycid(long cid) {
		// TODO Auto-generated method stub
		TbItemParamQuery example = new TbItemParamQuery();
		TbItemParamQuery.Criteria criteria = example.createCriteria();
		criteria.andItemCatIdEqualTo(cid);
		List<TbItemParam> list = tbItemParamDao.selectByExampleWithBLOBs(example);
		if (list != null && list.size() > 0) {
			System.out.println(list.get(0));
			return TaotaoResult.ok(list.get(0));
		}
		
		return TaotaoResult.ok();
	}

	@Override
	public EasyUIResult getItemParamList(int page, int rows) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, rows);
		TbItemParamQuery example = new TbItemParamQuery();
		List<TbItemParam> list = tbItemParamDao.selectByExampleWithBLOBs(example);
		PageInfo<TbItemParam> pageInfo = new PageInfo<>(list);
		long total = pageInfo.getTotal();
		EasyUIResult result = new EasyUIResult(total, list);
		return result;
	}

	@Override
	public TaotaoResult getItemParamByItemId(long itemId) {
		// TODO Auto-generated method stub
		TbItemParamQuery example = new TbItemParamQuery();
		TbItemParamQuery.Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(itemId);
		TbItemParam itemParam = tbItemParamDao.selectByExampleWithBLOBs(example).get(0);
		return TaotaoResult.ok(itemParam);
	}

}
