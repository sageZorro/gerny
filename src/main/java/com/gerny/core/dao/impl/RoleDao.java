package com.gerny.core.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gerny.core.entity.RoleEntity;
import com.gerny.core.vo.PageResults;

@Repository
public class RoleDao extends BaseDao<RoleEntity, Integer>{

	public List<RoleEntity> findAll() {
		String hql = new String("from RoleEntity");
		return super.getListByHQL(hql);
	}

	public PageResults<RoleEntity> findPageByHql(Map<String, String> map) {
		StringBuffer hql = new StringBuffer("from RoleEntity where 1=1 ");
		StringBuffer countHql = new StringBuffer("select count(*) from RoleEntity where 1=1 ");
		StringBuffer condition = new StringBuffer();
		Integer start = Integer.valueOf(map.get("start"));
		Integer length = Integer.valueOf(map.get("length"));
		
		List<Object> list = new ArrayList<Object>();
		/*if(map.get("username")!=null ){
			condition.append("and username like ? ");
			list.add("%"+ map.get("username")+"%");
		}
		if(map.get("nickname")!=null ){
			condition.append("and nickname like ? ");
			list.add("%"+ map.get("nickname")+"%");
		}
		if(map.get("mobile")!=null ){
			condition.append("and mobile like ? ");
			list.add("%"+ map.get("mobile")+"%");
		}
		if(map.get("phone")!=null ){
			condition.append("and phone like ? ");
			list.add("%"+ map.get("phone")+"%");
		}*/
		hql.append(condition);
		countHql.append(condition);
		PageResults<RoleEntity> retValue = super.findPageByFetchedHql(hql.toString(), countHql.toString(), start, length, list.toArray());
		retValue.setDraw(Integer.valueOf(map.get("draw")));
		return retValue;
	}
}
