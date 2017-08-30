package com.taotao.rest.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.utils.JsonUtils;
import com.taotao.mapper.TbItemCatDao;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatQuery;
import com.taotao.rest.dao.impl.JedisClientCluster;
import com.taotao.rest.pojo.ItemCat;
import com.taotao.rest.pojo.ItemCatResult;
import com.taotao.rest.service.ItemCatService;

@Service
public class ItemCatServiceImpl implements ItemCatService {

	@Autowired
	private TbItemCatDao tbItemCatDao;
	
	@Autowired
	private JedisClientCluster jedisClientCluster;
	
	@Value("${INDEX_ITEMCAT_REDIS_KEY}")
	private String INDEX_ITEMCAT_REDIS_KEY;
	
	@Override
	public ItemCatResult queryAllCategory() {
		// TODO Auto-generated method stub
		ItemCatResult result = new ItemCatResult();
		result.setData(getItemCatList(0));
		return result;
	}
	
	private List<?> getItemCatList(long parentId) {
		List<TbItemCat> list = null;
		
		//尝试从缓存中获取商品类目信息
		try {
			String result = jedisClientCluster.hget(INDEX_ITEMCAT_REDIS_KEY, parentId + "");
			if (!StringUtils.isBlank(result)) {
				//把字符串转化成list
				list = JsonUtils.jsonToList(result, TbItemCat.class);
				System.out.println("From cache");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (list == null) {
			System.out.println("From DBA");
			TbItemCatQuery example = new TbItemCatQuery();
			TbItemCatQuery.Criteria criteria = example.createCriteria();
			criteria.andParentIdEqualTo(parentId);
			list = tbItemCatDao.selectByExample(example);
			//将内容加入缓存中
			String cacheString = JsonUtils.objectToJson(list);
			jedisClientCluster.hset(INDEX_ITEMCAT_REDIS_KEY, parentId + "", cacheString);
		}
		List dataList = new ArrayList<>();
		int count = 0;
		for (TbItemCat tbItemCat : list) {
			if (tbItemCat.getIsParent()) {
				ItemCat itemCat = new ItemCat();
				if (parentId == 0) {
					itemCat.setName("<a href='/products/" + tbItemCat.getId() + ".html'>" +
							tbItemCat.getName() + "</a>");
				} else {
					itemCat.setName(tbItemCat.getName());
				}
				itemCat.setUrl("/category/" + tbItemCat.getId() + ".html");
				itemCat.setItem(getItemCatList(tbItemCat.getId()));
				dataList.add(itemCat);
				count ++;
				if (parentId == 0 && count >= 14) {
					break;
				}
			} else {
				dataList.add("/products/" + tbItemCat.getId() + ".html|" + tbItemCat.getName());
			}
		}
		return dataList;
	}

}
