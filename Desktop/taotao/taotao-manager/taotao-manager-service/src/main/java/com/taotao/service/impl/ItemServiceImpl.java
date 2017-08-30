package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.utils.IDUtils;
import com.taotao.mapper.TbItemDao;
import com.taotao.mapper.TbItemDescDao;
import com.taotao.mapper.TbItemParamDao;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemDescQuery;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamQuery;
import com.taotao.pojo.TbItemQuery;
import com.taotao.result.EasyUIResult;
import com.taotao.result.TaotaoResult;
import com.taotao.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemDao tbItemDao;
	
	@Autowired
	private TbItemDescDao tbItemDescDao;
	
	@Autowired
	private TbItemParamDao tbItemParamDao;
	
	@Override
	public EasyUIResult getItemList(int page, int rows) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, rows);
		TbItemQuery example = new TbItemQuery();
		List<TbItem> list = tbItemDao.selectByExample(example);
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		long total = pageInfo.getTotal();
		EasyUIResult result = new EasyUIResult(total, list);
		return result;
	}

	@Override
	public void saveItem(TbItem item, String desc, String itemParams) throws Exception {
		// TODO Auto-generated method stub
		Date date = new Date();
		//获得商品id
		long id = IDUtils.genItemId();
		item.setId(id);
		//商品状态,1-正常,2-下架,3-删除
		item.setStatus((byte)1);
		item.setCreated(date);
		item.setUpdated(date);
		tbItemDao.insert(item);
		//添加商品描述
		//创建TbItemDesc对象
		TbItemDesc itemDesc = new TbItemDesc();
		//获得一个商品id
		itemDesc.setItemId(id);
		itemDesc.setItemDesc(desc);
		itemDesc.setCreated(date);
		itemDesc.setUpdated(date);
		//插入数据
		tbItemDescDao.insert(itemDesc);
		//创建TbItemParam对象
		TbItemParam itemParam = new TbItemParam();
		//设置属性
		itemParam.setId(item.getId());
		itemParam.setItemCatId(item.getCid());
		itemParam.setCreated(date);
		itemParam.setUpdated(date);
		itemParam.setParamData(itemParams);
		//插入数据
		tbItemParamDao.insert(itemParam);
	}

	@Override
	public TaotaoResult getDescById(Long itemId) {
		// TODO Auto-generated method stub
		TbItemDesc tbItem = tbItemDescDao.selectByPrimaryKey(itemId);
		return TaotaoResult.ok(tbItem);
	}

	@Override
	public TaotaoResult deleteById(Long itemId) {
		// TODO Auto-generated method stub
		tbItemDao.deleteByPrimaryKey(itemId);
		tbItemDescDao.deleteByPrimaryKey(itemId);
		tbItemParamDao.deleteByPrimaryKey(itemId);
		return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult updateItem(TbItem item, String desc, String itemParams) {
		// TODO Auto-generated method stub
		//设置更新日期
		Date date = new Date();
		item.setUpdated(date);
		//执行更新操作
		tbItemDao.updateByPrimaryKeySelective(item);
		//更新商品描述
		TbItemDesc itemDesc = new TbItemDesc();
		itemDesc.setItemDesc(desc);
		itemDesc.setUpdated(date);
		TbItemDescQuery example = new TbItemDescQuery();
		TbItemDescQuery.Criteria criteria = example.createCriteria();
		criteria.andItemIdEqualTo(item.getId());
		tbItemDescDao.updateByExampleSelective(itemDesc, example);
		//更新商品描述规格参数
		TbItemParam itemParam = new TbItemParam();
		itemParam.setUpdated(date);
		itemParam.setParamData(itemParams);
		TbItemParamQuery paramExample = new TbItemParamQuery();
		TbItemParamQuery.Criteria paramCriteria = paramExample.createCriteria();
		paramCriteria.andIdEqualTo(item.getId());
		tbItemParamDao.updateByExampleSelective(itemParam, paramExample);
		return TaotaoResult.ok();
	}

}
